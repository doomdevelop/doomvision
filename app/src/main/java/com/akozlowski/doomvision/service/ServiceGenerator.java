package com.akozlowski.doomvision.service;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Generate RestAdapter for giving url as endpoint and Authorization
 * Created by akozlowski on 14/08/15.
 *
 */
public class ServiceGenerator {
    public static <S> S createService(Class<S> serviceClass, String baseUrl, final String auth) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl).setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Authorization", auth);
                        request.addHeader("Content-Type", "application/json");
                        request.addHeader("Accept", "application/json");
                    }
                });
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}
