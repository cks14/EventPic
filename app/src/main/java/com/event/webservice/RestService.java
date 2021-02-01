package com.event.webservice;

import com.event.webservice.model.ImageList;
import com.event.webservice.model.ImageUpload;
import com.event.webservice.model.Login;
import com.event.webservice.model.Register;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Appslure-PC on 8/15/2017.
 */

public interface RestService {

    //for the login detail

    @Multipart
    @POST("user/login")
    Call<Login> Login(
            @Part("email") RequestBody email,
            @Part("password") RequestBody password);

    //for the signup detail

    @Multipart
    @POST("user/register")
    Call <Register> Register(
            @Part("name") RequestBody name,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password,
            @Part("token") RequestBody token);

    //for the picture upload
//    @Headers("Token: 4Kha7HY2pbing3DCoy1B")
    @Multipart
    @POST("picture/upload")
    Call<ImageUpload> ImageUpload
    (@Part MultipartBody.Part image,@Part("tag") RequestBody tag);


    @GET("picture/listAll")
    Call<ImageList>doGetImageList(@Header("Token") String Token);



//    @GET("picture/listAll")
//    Call getMyThing1(@Query("mid") String Token);


//    @GET("picture/listAll")
//    Call<List<ImageList>> getPosts();


//    @GET("picture/listAll?")
//    static Call<ImageList> doGetUserList(@Query("token") String token) {
//    }

}


