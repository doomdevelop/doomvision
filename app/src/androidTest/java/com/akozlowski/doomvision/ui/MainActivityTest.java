package com.akozlowski.doomvision.ui;

import org.hamcrest.Matcher;
import org.junit.Before;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;

import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.manager.DataManager;
import com.akozlowski.doomvision.ui.listView.ImageItem;
import com.akozlowski.doomvision.util.DataCreator;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by and on 14.09.15.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    MainActivity activity;

    @Before
    public void setUp() throws Exception {
        activity = mActivityRule.getActivity();
    }
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }


    @Test
    public void onSearchClickShouldShowProgressView(){
        activity = mActivityRule.getActivity();
        onView(withId(R.id.search_progress)).check(matches(not(isDisplayed())));
        onView(withId(R.id.search_field)).perform(typeText("go"), closeSoftKeyboard());
        onView(withId(R.id.search_btn)).perform(click());
        onView(withId(R.id.search_progress)).check(matches(isDisplayed()));
        onView(isRoot()).perform(waitId(R.id.image, 6000)).perform(click());
        assertTrue(DataManager.getInstance().getResponse() != null);
    }

}