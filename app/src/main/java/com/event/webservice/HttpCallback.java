package com.event.webservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class HttpCallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccess(call, response);
        } else {
            //onFailure(call, new HttpException(response));
        }
        onFinish();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        //logNetworkFailure(t);
        onError(call, t);
        onFinish();
    }

    public abstract void onSuccess(Call<T> call, Response<T> response);

    public abstract void onError(Call<T> call, Throwable t);

    public void onFinish() {
    }
}
