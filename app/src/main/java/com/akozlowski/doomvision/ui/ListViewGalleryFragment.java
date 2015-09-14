package com.akozlowski.doomvision.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.manager.DataManager;
import com.akozlowski.doomvision.manager.RestManager;
import com.akozlowski.doomvision.pojo.Category;
import com.akozlowski.doomvision.pojo.Data;
import com.akozlowski.doomvision.pojo.Response;
import com.akozlowski.doomvision.service.InternetConnectivityReceiver;
import com.akozlowski.doomvision.ui.listView.CustomAdapter;
import com.akozlowski.doomvision.ui.listView.ImageItem;
import com.akozlowski.doomvision.ui.listView.Item;
import com.akozlowski.doomvision.util.DebugLog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import roboguice.RoboGuice;
import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A placeholder fragment containing a simple listView.
 */
public class ListViewGalleryFragment extends RoboFragment {
    private static final String TAG = ListViewGalleryFragment.class.getSimpleName();
    private RestManager restManager;
    @InjectView(R.id.response)
    private TextView responseTextView;
    @InjectView(R.id.search_field)
    private EditText searchEditText;
    @InjectView(R.id.search_btn)
    private ImageView searchBtn;
    @InjectView(R.id.listview)
    private ListView listView;
    private List<Item> items = new ArrayList<Item>();
    @InjectView(R.id.search_progress)
    private ProgressBar progressBar;


    public ListViewGalleryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_view_gallery_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RoboGuice.getInjector(getActivity()).injectViewMembers(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        restManager = new RestManager(getActivity());
        createListView();
        if (DataManager.getInstance().getResponse() != null) {
            createItems(DataManager.getInstance().getResponse());
        }
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              onStartSearch();
            }
        });
    }

    private void onStartSearch(){
        hideKeyboard();
        progressBar.setVisibility(View.VISIBLE);
        searchRx();
    }

    public ListView getListView() {
        return listView;
    }

    private void createListView() {
        CustomAdapter customAdapter = new CustomAdapter(getActivity(), R.layout.list_view_content, items);
        listView.setAdapter(customAdapter);
    }

    private void createItems(Response response) {
        List<Data> dataList = response.getData();
        items.clear();
        DataManager.getInstance().setResponse(response);

        int index = 0;
        for (Data data : dataList) {
            items.add(new ImageItem(getActivity(),data.getAssets().getPreview().getUrl(), index));
            printCategories(data.getCategories());
            index++;
        }
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetChanged();
    }

    private void printCategories(List<Category> categories) {
        for (Category category : categories) {
            DebugLog.d(TAG + " cat: " + category.getName());
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
        getView().invalidate();
    }




    /**
     * search via retrofit in background using RxAndroid
     */
    private void searchRx() {
        restManager.searchImage(searchEditText.getText().toString()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Response>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                if (e != null && e.getMessage() != null) {
                    DebugLog.d(TAG + " error: " + e.toString() + ", " + e.getMessage() + ", ");
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                searchBtn.setEnabled(false);
                progressBar.setVisibility(View.GONE);
                onNoInternetConnection();
            }

            @Override
            public void onNext(Response response) {
                DebugLog.d(TAG + " responce: " + response.toString());
                if (response != null && response.getData() != null && response.getData().size() > 0) {
                    ((MainActivity) getActivity()).changeRequestedOrientation(true);
                } else {
                    ((MainActivity) getActivity()).changeRequestedOrientation(false);
                }
                createItems(response);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * search using retrofit calls in background
     */
    private void search() {
        restManager.searchImage(searchEditText.getText().toString(), new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {
                DebugLog.d(TAG + " responce: " + response.toString());
                if (response != null && response.getData() != null && response.getData().size() > 0) {
                    ((MainActivity) getActivity()).changeRequestedOrientation(true);
                } else {
                    ((MainActivity) getActivity()).changeRequestedOrientation(false);
                }
                createItems(response);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error) {
                DebugLog.d(TAG + " error: " + error.toString() + ", " + error.getUrl() + ", ");
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    searchBtn.setEnabled(false);
                    progressBar.setVisibility(View.GONE);
                    onNoInternetConnection();
                }
            }
        });
    }
    private void onNoInternetConnection() {
        Toast.makeText(getActivity(), getActivity().getResources().getString(R.string.no_internet_msg), Toast.LENGTH_LONG);
        InternetConnectivityReceiver.getInstance().registerConnectivityReceiver(new InternetConnectivityReceiver.InternetConnectivityListener() {
            @Override
            public void onInternetConnectivityCallback() {
                searchBtn.setEnabled(true);
                onStartSearch();
            }
        });
    }
}
