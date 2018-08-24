package com.example.thongds.idontknow.detailAction.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thongds.idontknow.R;
import com.example.thongds.idontknow.homeAction.model.FeedData;
import com.example.thongds.idontknow.network.GlideApp;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailVH> {

    Context mContext;
    FeedData mFeedData;
    public DetailAdapter(Context context,FeedData feedData){
        mContext = context;
        mFeedData = feedData;
    }
    @NonNull
    @Override
    public DetailVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        return new DetailVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailVH holder, int position) {
            if(mFeedData !=null){
                GlideApp.with(mContext).clear(holder.imageView);
                GlideApp.with(mContext).load(mFeedData.getFeedItems().get(position).getImageSrc()).error(R.drawable.error).into(holder.imageView);
                holder.textView.setText(mFeedData.getFeedItems().get(position).getText());
            }
    }
    public void setData(FeedData feedData) {
        mFeedData = feedData;
    }

    @Override
    public int getItemCount() {
        if (mFeedData != null)
            return mFeedData.getFeedItems().size();
        return 0;
    }

    public static class DetailVH extends RecyclerView.ViewHolder{
        ImageView imageView ;
        TextView textView;
        public DetailVH(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
