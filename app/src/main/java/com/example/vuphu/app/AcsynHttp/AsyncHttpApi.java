package com.example.vuphu.app.AcsynHttp;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;

public class AsyncHttpApi {
    private static final String BASE_URL = NetworkConst.network;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }
    public static void put(Context context, String url, RequestParams params, JsonHttpResponseHandler responseHandler) {
        client.put(context,url,params, responseHandler);
    }
    public static void delete(Context context, String url,Header[] headers, RequestParams params, JsonHttpResponseHandler responseHandler){
        client.delete(context,url,headers,responseHandler);
    }


    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
