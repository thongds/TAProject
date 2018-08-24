package com.example.thongds.idontknow.homeAction.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.thongds.idontknow.R;
import com.example.thongds.idontknow.homeAction.model.FeedData;
import com.example.thongds.idontknow.network.RequestCallback;
import com.example.thongds.idontknow.homeAction.present.Present;
import com.google.gson.Gson;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements IView {
    private  String mUrl = "https://api.popjam.com/v2/users/2e009fcf-d63c-4a3f-92f4-e847e2d5eee8/homeFeed";
    private FeedData mFeedData = null;
    private Adapter mAdapter;
    private Present mPresent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresent = new Present(this);
        mPresent.loadData(mUrl);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mAdapter =  new Adapter(this,mFeedData);
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
