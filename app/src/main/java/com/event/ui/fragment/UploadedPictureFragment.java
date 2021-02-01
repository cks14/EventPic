package com.event.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.event.R;
import com.event.ui.adapter.PictuterListAdapter;
import com.event.utils.LocalConstant;
import com.event.webservice.ApiClient;
import com.event.webservice.HttpCallback;
import com.event.webservice.RestClient;
import com.event.webservice.RestService;
import com.event.webservice.model.ImageList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UploadedPictureFragment extends Fragment {
    String Token1;
    PictuterListAdapter pictuterListAdapter;
    List<ImageList.DataDTO> getCategory;
    private RecyclerView recyclerview;
    private ShimmerRecyclerView shimmerRecyclerView;
    int numberOfColumns =2;
    public UploadedPictureFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(com.event.R.layout.fragment_pictureuploaded, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        recyclerview = v.findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
//        final LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerview.setLayoutManager(llm);
        recyclerview.setNestedScrollingEnabled(false);
        shimmerRecyclerView = v.findViewById(R.id.shimmer_recycler_view);
        recyclerview.setAdapter(pictuterListAdapter);
        BankDetail1();

    }


    private void BankDetail1()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://genuinemark.org/piccollect/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestService jsonPlaceHolderApi = retrofit.create(RestService.class);
        Call<ImageList> call2 = jsonPlaceHolderApi.doGetImageList(LocalConstant.getInstance(getActivity()).getToken());
        call2.enqueue(new Callback<ImageList>() {
            @Override
            public void onResponse(Call<ImageList> call, Response<ImageList> response) {

                pictuterListAdapter = new PictuterListAdapter(getActivity(), response.body().getData());

                recyclerview.setAdapter(pictuterListAdapter);
                shimmerRecyclerView.setVisibility(View.GONE);
                //  Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<ImageList> call, Throwable t) {
                call.cancel();

            }
        });


    }
}