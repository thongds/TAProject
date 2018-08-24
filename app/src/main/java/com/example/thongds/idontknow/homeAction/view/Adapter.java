package com.example.thongds.idontknow.homeAction.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thongds.idontknow.R;
import com.example.thongds.idontknow.detailAction.present.DetailActivity;
import com.example.thongds.idontknow.homeAction.model.FeedData;
import com.example.thongds.idontknow.network.GlideApp;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private FeedData mFeedData;
    private Context mContext;
    public Adapter(Context context,FeedData feedData){
        mFeedData = feedData;
        mContext = context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(mFeedData != null){
            holder.imageView.setOnClickListener((view)->{
                Intent intent = new Intent(mContext,DetailActivity.class);
                intent.putExtra(DetailActivity.ITEM_ID,mFeedData.getFeedItems().get(position).getId());
                mContext.startActivity(intent);
            });
            holder.textView.setText(mFeedData.getFeedItems().get(position).getText());
            //cancel all request before
            GlideApp.with(mContext).clear(holder.imageView);
            // load new image from cache or network
            GlideApp.with(mContext).load(mFeedData.getFeedItems().get(position).getImageSrc()).error(R.drawable.error).into(holder.imageView);
        }
    }

    @Override
    public long getItemId(int position) {
        if(mFeedData != null)
            // effect notification at modify data
            return mFeedData.getFeedItems().get(position).getId().hashCode();
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        if(mFeedData != null)
            return mFeedData.getFeedItems().size();
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView  textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.text);
        }
    }
    public void setData(FeedData feedData){
        mFeedData = feedData;
    }
}
