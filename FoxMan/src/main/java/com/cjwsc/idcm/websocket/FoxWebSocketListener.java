package com.cjwsc.idcm.websocket;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class FoxWebSocketListener extends WebSocketListener {

    Gson gson = new Gson();
    private disConnectListener listener;

    public FoxWebSocketListener(disConnectListener listener) {
        this.listener = listener;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {

    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {

//        AuthBean authBean = gson.fromJson(text, AuthBean.class);
//        if (TextUtils.equals(authBean.getCmd(), "auth1")) { //发送认证消息
//            String encode = MD5Utils.encode(authBean.getResult().getSeed() + "6PIRqVw3cRm84dKVg"); //由于是公司在线业务，所以加密串不能展示，原理是做MD5秘钥签名
//            String s = sendData(encode);
//            webSocket.send(s);
//        }


        Logger.d("----onMessage------->"+text);
        output("onMessage: " + text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        output("onMessage byteString: " + bytes);

    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        output("onClosing: " + code + "/" + reason);
        Logger.d("----onClosing------->"+reason);

    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        output("onClosed: " + code + "/" + reason);
        Logger.d("----onClosed------->");
    }

    @Override

    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        output("onFailure: " + t.getMessage());
        Logger.d("----onFailure------->"+t.getMessage());

        listener.reconnect();
    }

    private void output(String params) {
        System.out.println(params);
        Logger.d("-------params----->" + params);
    }

    private String sendData(String sign) {
        String jsonHead = "";
        Map<String, Object> mapHead = new HashMap<>();
        mapHead.put("cmd", "auth2");
        mapHead.put("msg_id", "1");
        mapHead.put("authCode", sign);
        mapHead.put("userId", "111");
        jsonHead = buildRequestParams(mapHead);
        Log.e("TAG", "sendData: " + jsonHead);
        return jsonHead;
    }

    public static String buildRequestParams(Object params) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(params);
        return jsonStr;
    }

    //定义失败回调的接口
    interface disConnectListener {
        void reconnect();
    }

}
