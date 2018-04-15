package com.example.vuphu.app.RetrofitAPI;

import com.example.vuphu.app.AcsynHttp.NetworkConst;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = NetworkConst.network;
    public static Api getAPIService() {

        return RetrofitClient.getInstance(BASE_URL).create(Api.class);
    }
}
