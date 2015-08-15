package com.akozlowski.doomvision.manager;

import android.content.Context;
import android.util.Base64;

import com.akozlowski.doomvision.R;
import com.akozlowski.doomvision.pojo.Response;
import com.akozlowski.doomvision.service.SearchImageService;
import com.akozlowski.doomvision.service.ServiceGenerator;
import com.akozlowski.doomvision.service.TestService;
import com.akozlowski.doomvision.service.ValidateService;
import com.akozlowski.doomvision.util.DebugLog;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by akozlowski on 14/08/15.
 */
public class RestManager {

    private static final String TAG = RestManager.class.getSimpleName();
    //Resicle ListView
    private Context context;

    private RestAdapter restAdapter;
    private String clientID;
    private String clientSecret;

    //"https://${client_id}:${client_secret}@api.shutterstock.com/v2"
    //https://8f80cdd74ddbef680339:d1cc912295e23669be555fcadb02b4442b53be9e@api.shutterstock.com/v2/images/search?query=donkey
    public RestManager(Context context) {
        this.context = context;
        this.clientID = context.getString(R.string.client_id);
        this.clientSecret = context.getString(R.string.client_secret);
    }

    private void createRestAdapter() {
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(getEndPoint())
                .build();
    }

    private String getAuthBase64() {

        String source = clientID + ":" + clientSecret;
        String ret = "Basic " + Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);

        DebugLog.d(TAG + " Authorization: " + ret);
        return ret;
    }

    private String getEndPoint() {
        //https://api.shutterstock.com/v2/images/search?license=commercial&&&&&&&&&&query=dog&&sort=popular&view=minimal
        return "https://api.shutterstock.com/v2";
//        return "https://" + clientID + ":" + clientSecret + "@api.shutterstock.com/v2";
    }

    public void searchImage(String query, Callback<Response> cb) {
//        SearchImageService service = restAdapter.create(SearchImageService.class);
        SearchImageService service = ServiceGenerator.createService(SearchImageService.class, getEndPoint(), getAuthBase64());
        service.search(query, cb);
    }

    public void test(Callback<Response> cb) {
        TestService service = ServiceGenerator.createService(TestService.class, getEndPoint(), getAuthBase64());
        service.test(cb);
    }

    public void validate(String id, String tag1, String tag2, Callback<Response> cb) {
        ValidateService service = ServiceGenerator.createService(ValidateService.class, getEndPoint(), getAuthBase64());
        service.validate(id, tag1, tag2, cb);
    }
}
