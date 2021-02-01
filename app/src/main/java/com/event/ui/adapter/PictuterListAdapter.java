package com.event.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.event.R;
import com.event.webservice.model.ImageList;

import java.util.List;

public class PictuterListAdapter extends RecyclerView.Adapter<PictuterListAdapter.ViewHolder> {
    Context mContext;
    OnItemClickListener mItemClickListener;
    List<ImageList.DataDTO> getCategory;
    private Dialog dialog;
    private ImageView banner;


    public PictuterListAdapter(Context context, List<ImageList.DataDTO> getCategory) {
        this.mContext = context;
        this.getCategory = getCategory;

    }


    // 3
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView categoryImage;
        private TextView categoryName;


        public ViewHolder(View itemView) {
            super(itemView);

            banner = (ImageView)itemView. findViewById(R.id.banner);
        }

        @Override
        public void onClick(View v) {


        }

        public void setData() {


            Glide
                    .with(mContext)
                    .load(getCategory.get(getAdapterPosition()).getFile_path())
                    .into(banner);



        }

        public void setAction() {
            banner.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public int getItemCount() {

        int i = 0;
        try {
            i = getCategory.size();


        } catch (Exception e) {
            i = 0;
            e.printStackTrace();
        }

        return i;
    }

    // 2
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_image_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    // 3
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        ((ViewHolder) holder).setData();
        ((ViewHolder) holder).setAction();

    }

}
