package com.example.thongds.idontknow.network;

import java.io.IOException;

public interface RequestCallback {
    void response(String data);
    void networkIOException(IOException e);
}

