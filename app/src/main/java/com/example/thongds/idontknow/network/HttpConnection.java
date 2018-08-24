package com.example.thongds.idontknow.network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {


    public void connect(final String uri,RequestCallback callback){

        Runnable runnable = () -> {
            try {
                URL url = new URL(uri);
                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = new BufferedInputStream(connection.getInputStream());
                readStream(inputStream,callback);
            } catch (IOException e) {
                callback.networkIOException(e);
                e.printStackTrace();
            }
        };

        Thread requestHttp = new Thread(runnable);
        requestHttp.start();

    }

    private void readStream(InputStream inputStream,RequestCallback callback) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuffer = new StringBuilder();
        String line = bufferedReader.readLine();
        while (line != null){
            stringBuffer.append(line).append("\n");
            line = bufferedReader.readLine();
        }
        callback.response(stringBuffer.toString());
        bufferedReader.close();
    }

}
