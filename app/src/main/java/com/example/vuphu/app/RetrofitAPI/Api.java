package com.example.vuphu.app.RetrofitAPI;

import com.example.vuphu.app.object.ProductCallback;
import com.loopj.android.http.RequestParams;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    @Multipart
    @POST("/products/")
    @Headers("Content-Type: application/json")
    Call<Void> upLoadProduct (@Header("Authorization") String authorization,
                                         @Part MultipartBody.Part image,
                                         @Part("productImage") RequestBody name);

}