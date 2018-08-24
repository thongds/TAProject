package com.example.thongds.idontknow.homeAction.present;

import com.example.thongds.idontknow.network.HttpConnection;
import com.example.thongds.idontknow.homeAction.view.IView;

public class Present {
    private IView mIView;
    public Present(IView iView){
        mIView = iView;
    }
    public void loadData(String url){
        //in MVP, we should use Model interaction to request Data but this example just call from network, so I skip
        HttpConnection httpConnection = new HttpConnection();
        httpConnection.connect(url, mIView.responseData());
    }

}
