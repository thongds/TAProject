package com.example.thongds.idontknow.detailAction.present;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.thongds.idontknow.R;
import com.example.thongds.idontknow.detailAction.view.DetailAdapter;
import com.example.thongds.idontknow.homeAction.model.FeedData;
import com.example.thongds.idontknow.homeAction.present.Present;
import com.example.thongds.idontknow.homeAction.view.IView;
import com.example.thongds.idontknow.network.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;

public class DetailActivity extends AppCompatActivity implements IView {
    public static String ITEM_ID;
    private String mUrl = "https://api.popjam.com/v2/users/2e009fcf-d63c-4a3f-92f4-e847e2d5eee8/homeFeed?lastId=";
    private FeedData mFeedData = null;
    private DetailAdapter mAdapter;
    private Present mPresent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Intent intent = getIntent();
        mPresent = new Present(this);
        mPresent.loadData(mUrl+intent.getStringExtra(ITEM_ID));
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mAdapter =  new DetailAdapter(this,mFeedData);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public RequestCallback responseData() {
        return new RequestCallback() {
            @Override
            public void response(String data) {
                Gson gson = new Gson();
                mFeedData = gson.fromJson(data,FeedData.class);
                mAdapter.setData(mFeedData);
                runOnUiThread(mAdapter::notifyDataSetChanged);
            }

            @Override
            public void networkIOException(IOException e) {
                e.printStackTrace();
            }
        };
    }
}
