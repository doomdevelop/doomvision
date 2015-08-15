package com.akozlowski.doomvision.ui;

import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.manager.RestManager;
import com.akozlowski.doomvision.pojo.Response;
import com.akozlowski.doomvision.util.DebugLog;

import retrofit.Callback;
import retrofit.RetrofitError;
import roboguice.RoboGuice;
import roboguice.fragment.provided.RoboFragment;
import roboguice.inject.InjectView;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends RoboFragment {
    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private RestManager restManager;
    @InjectView(R.id.response)
    private TextView responseTextView;
    @InjectView(R.id.search_field)
    private EditText searchEditText;
    @InjectView(R.id.search_btn)
    private Button searchBtn;

    private static boolean TEST = false;

    public MainActivityFragment() {
    }

    public void onViewsInjected() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectViewMembers(this);
        restManager = new RestManager(getActivity());

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                base64Test();
                if (TEST) {
                    validate();
                } else {
                    search();
                }
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void search() {
        if (searchEditText.getText() != null && searchEditText.getText().length() > 0) {
            restManager.searchImage("dog", new Callback<Response>() {
                @Override
                public void success(Response response, retrofit.client.Response response2) {
                    DebugLog.d(TAG + " responce: " + response.toString());
                }

                @Override
                public void failure(RetrofitError error) {
                    DebugLog.d(TAG + " error: " + error.toString() + ", " + error.getUrl() + ", ");
                }
            });
        }
    }

    private void test() {
        restManager.test(new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {
                responseTextView.setText("success: " + response2.getStatus() + "; " + response2.getReason().toString() + "; " + response2.getUrl());
            }

            @Override
            public void failure(RetrofitError error) {
                responseTextView.setText("error: " + error.getMessage() + ", " + error.getUrl() + "; " + error.getSuccessType().toString());
            }
        });
    }

    private void validate() {
        restManager.validate("1", "tag1", "tag2", new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {
                DebugLog.d(TAG + " responce: " + response.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                DebugLog.d(TAG + " error: " + error.toString() + ", " + error.getUrl() + ", " + error.getResponse().getReason());
            }
        });
    }

    //    MTIzNDo0MzIx
    //    MTIzNDQzMjE
    private void base64Test() {
        String clientID = "1234";
        String clientSecret = "4321";
        StringBuilder sb = new StringBuilder(clientID).append(clientSecret);

        String encodedID = Base64.encodeToString(sb.toString().getBytes(), Base64.DEFAULT);
        String encodedSecret = Base64.encodeToString(clientSecret.getBytes(), Base64.DEFAULT);

        String source = clientID + ":" + clientSecret;
        String ret = "Basic " + Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);

        DebugLog.d(TAG + " Authorization: " + ret);
    }
}
