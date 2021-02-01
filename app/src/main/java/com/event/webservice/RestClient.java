package com.event.webservice;

import com.event.webservice.model.ImageUpload;
import com.event.webservice.model.ImageList;
import com.event.webservice.model.Login;
import com.event.webservice.model.Register;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arvind on 02/10/2018.
 */

public class RestClient {

    private static RestClient mInstance = new RestClient();
    OkHttpClient client;
    Retrofit retrofit;
    private RestService mRestService;

    private RestClient() {
    }
    public static RestClient getInst() {
        return mInstance;
    }

    /**
     * Here is the setup call for http service for this app. setup is done once in MyApplication class.
     * Step 1 : Logging is added using HttpLoggingInterceptor. Logging is removed from retrofit2 so
     * it has to be added as a part of OkHttp interceptor.
     * Step 2 : Build an OkHttpClient with logging interceptor.
     * Step 3 : Retrofit is build with baseUrl, okhttp client, Gson Converter factory for easy JSON to POJO conversion.
     */
    public void setup() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .addNetworkInterceptor((Interceptor) new StethoInterceptor());

        // Should be used only in Debug Mode.
        if (true) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(true ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE); //// TODO: 21-07-2016
            builder.addInterceptor(interceptor);
        }

        client = builder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constant.IDECORAMA_HOST)
                .client(client)
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mRestService = retrofit.create(RestService.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

//----------------------------------------------
//        | MODULE 1 - LOGIN/SIGNUP/CONFIG/SUBSCRIPTION |
//            -----------------------------------------------

    //for the loing user


//    public Call<Login>Login(String email, String password) {
//        HashMap<String, String> hashMap = new HashMap<>();
//        hashMap.put("email", email);
//        hashMap.put("password", password);
//        return mRestService.Login(hashMap);
//    }


    public Call<Register>Register(String name, String email, String password, String token) {
        RequestBody name1 = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody email1 = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody password1 = RequestBody.create(MediaType.parse("text/plain"), password);
        RequestBody token1 = RequestBody.create(MediaType.parse("text/plain"), token);

        return mRestService.Register(name1,email1,password1,token1);
    }


    public Call<Login>Login(
            String email,
            String password){

        RequestBody email1 = RequestBody.create(MediaType.parse("text/plain"), email);
        RequestBody password1 = RequestBody.create(MediaType.parse("text/plain"), password);

        return mRestService.Login(email1,password1);

    }

    public Call<ImageUpload> ImageUpload(String tag,File file)
    {
        RequestBody tag1 = RequestBody.create(MediaType.parse("text/plain"),tag);
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Token", Token);

        MultipartBody.Part file1 = null;
        if (file!= null)
            file1 = MultipartBody.Part.createFormData("image",file.getName(),RequestBody.create(MediaType.parse("image/*"),file));

        return mRestService.ImageUpload(file1,tag1);

    }

//    public Call<ImageList> ImageList(String token) {
//        return mRestService.doGetUserList(token);
//    }


}

