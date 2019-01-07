package com.cjwsc.idcm.signarl;

import microsoft.aspnet.signalr.client.LogLevel;
import microsoft.aspnet.signalr.client.Logger;
import microsoft.aspnet.signalr.client.hubs.HubConnection;

/**
 * Created by admin-2 on 2018/4/4.
 */

public class FoxHubConnection extends HubConnection {
    private OnConnectionStateChange stateChange;
    public FoxHubConnection(String url, String queryString, boolean useDefaultUrl, Logger logger) {
        super(url, queryString, useDefaultUrl, logger);
    }


    public FoxHubConnection(String url, String queryString, boolean useDefaultUrl) {
        super(url, queryString, useDefaultUrl, new Logger() {
            @Override
            public void log(String s, LogLevel logLevel) {

            }
        });
    }

    public FoxHubConnection(String url) {
        super(url);
    }

    public FoxHubConnection(String url, boolean useDefaultUrl) {
        super(url, useDefaultUrl);
    }

    public void setOnConnectionStateChange(OnConnectionStateChange stateChange){
        this.stateChange=stateChange;
    }


}
