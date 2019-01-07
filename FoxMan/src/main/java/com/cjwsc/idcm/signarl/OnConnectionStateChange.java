package com.cjwsc.idcm.signarl;

import com.cjwsc.idcm.Utils.TypeUtils;

import microsoft.aspnet.signalr.client.Connection;
import microsoft.aspnet.signalr.client.ErrorCallback;

/**
 * Created by admin-2 on 2018/4/4.
 */

public abstract class OnConnectionStateChange {
    FoxHubConnection connection;
    public OnConnectionStateChange(FoxHubConnection connection) {
         this.connection=connection;
        connection.connected(new Runnable() {
             @Override
             public void run() {
                 OnConnected();
             }
         });

        connection.connectionSlow(new Runnable() {
             @Override
             public void run() {
                 OnConnectionSlow();
             }
         });

        connection.error(new ErrorCallback() {
             @Override
             public void onError(Throwable throwable) {
                 OnError(throwable);

             }
         });


        connection.reconnected(new Runnable() {
            @Override
            public void run() {
                OnReconnected();
            }
        });
        connection.reconnecting(new Runnable() {
            @Override
            public void run() {
                OnReconnecting();
            }
        });



    }
    public abstract void OnConnected();
    public abstract void OnConnectionSlow();
    public abstract void OnError(Throwable throwable);
    public abstract void OnReconnected();
    public abstract void OnReconnecting();


}
