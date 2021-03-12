package com.wxd.firstlinecode.net;

public interface HttpCallbackListener {

    void onFinish(String response);

    void onError(Exception e);
}
