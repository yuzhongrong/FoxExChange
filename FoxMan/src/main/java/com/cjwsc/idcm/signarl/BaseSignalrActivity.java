package com.cjwsc.idcm.signarl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.caption.netmonitorlibrary.netStateLib.NetChangeObserver;
import com.caption.netmonitorlibrary.netStateLib.NetStateReceiver;
import com.caption.netmonitorlibrary.netStateLib.NetUtils;
import com.cjwsc.idcm.Utils.LogUtil;
import com.cjwsc.idcm.Utils.LoginUtils;
import com.cjwsc.idcm.Utils.NetworkUtil;
import com.cjwsc.idcm.base.AppManager;
import com.cjwsc.idcm.base.BaseActivity;
import com.cjwsc.idcm.base.BaseModel;
import com.cjwsc.idcm.base.BasePresenter;
import com.cjwsc.idcm.constant.BaseSignalConstants;
import com.cjwsc.idcm.signarl.impl.HubOnDataCallBackImpl;
import com.trello.rxlifecycle2.android.ActivityEvent;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import microsoft.aspnet.signalr.client.ConnectionState;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

/**
 * Created by admin-2 on 2018/4/3.
 */
public abstract class BaseSignalrActivity<M extends BaseModel, P extends BasePresenter> extends BaseActivity<M, P> {

    protected FoxHubConnection hubConnection;//链接对象
    public HubProxy hubProxy;//代理对象
    protected static Stack<String> subscribeStack;
    protected String host = "";// ACacheUtil.get(this).getAsString(AcacheKeys.HOST);
    protected String groupid = "";
    protected String mHub = "";
    protected String userId;
    protected SignalRFuture<Void> con;

    private MyNetChangeObserver observer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createSignalr();
        //这个是全局网络监听
        NetStateReceiver.registerObserver(observer = new MyNetChangeObserver());
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    protected void createSignalr() {
        LogUtil.i("createSignalr==========");
        if (isAutoCreateSignalrConnection()) {

            if (getIntent() != null && getIntent().getExtras() != null) {
                groupid = getIntent().getExtras().getString("groupid", groupid);
            }
            userId = LoginUtils.getLoginBean(this).getId() + "";
            initSignalr(host, groupid, mHub);
        }

    }

    //这里原本不需要userid 的 因为后台服务垃圾所以要在创建聊天连接的时候加入userid

    /**
     * @param mhost
     * @param groupi:加组
     * @param hub
     */
    protected void initSignalr(String mhost, String groupi, String hub) {
        synchronized (this) {
            if (!TextUtils.isEmpty(mhost)) {
                Platform.loadPlatformComponent(new AndroidPlatformComponent());
                if (hubConnection == null) {
                    hubConnection = new FoxHubConnection(mhost, "UserID=" + getUserId(), true);
                    if (TextUtils.isEmpty(hub)){
                        if (this.getClass().getSimpleName().equals("StartActivity")){
                            hub = "ExchangesHub";
                        }else{
                            hub = BaseSignalConstants.SIGNAL_HUB_NAME;
                        }
                    }
                    hubProxy = hubConnection.createHubProxy(hub);
                    mHub = hub;
                    groupid = groupi;
                    host = mhost;
                    if (hubConnection != null) {
                        initFoxConnectionListener(hubConnection);
                        if (hubConnection != null) {
                            con = hubConnection.start();
                            SignalrConnectionManager.getInstance().addConnection(con);
                        }

                    }
                }
            }
        }

    }

    protected String getUserId() {
        return "";
    }

    private void initFoxConnectionListener(FoxHubConnection connection) {
        if (connection == null) return;
        connection.setOnConnectionStateChange(new OnConnectionStateChange(connection) {
            @Override
            public void OnConnected() {
                LogUtil.i("setOnConnectionStateChange" + "-----OnConnected------>");

                //垃圾后台不统一导致这里出现2个group

                if (BaseSignalConstants.isAddGroup) {//加组情况

                    if (AppManager.getInstance().currentActivity().getClass().getSimpleName().equals("StartActivity")) {//添加Pin页面数据组
                        GroupsUtils.addGroupId(BaseSignalrActivity.this,hubProxy, groupid, new SwitchGroupIdCallBack() {
                            @Override
                            public void OnSeccess() {
                                LogUtil.i("---init--addgroup---OnSeccess-->");
                                subScribes();
                            }

                            @Override
                            public void OnFail() {
                                LogUtil.i("---init--addgroup---OnFail-->");
                            }
                        });

                    } else {//添加调天组

                        GroupsUtils.addChatGroupId(BaseSignalrActivity.this,hubProxy, groupid, new SwitchGroupIdCallBack() {
                            @Override
                            public void OnSeccess() {
                                LogUtil.i("---init--addgroup---OnSeccess-->");
                                subScribes();
                            }

                            @Override
                            public void OnFail() {
                                LogUtil.i("---init--addgroup---OnFail-->");
                            }
                        });
                    }


                } else {//不加组情况

                    subScribes();

                }


            }

            //弱网重新连接连接不上，或者后台关闭推送服务

            @Override
            public void OnError(Throwable throwable) {


                LogUtil.i("setOnConnectionStateChange" + "-----OnError------>" + throwable.getMessage());
                if (!NetworkUtil.isNetworkAvailable(BaseSignalrActivity.this))
                    return;//如果网络无效重置也没有意义
                if (connection != null && connection.getState().equals(ConnectionState.Disconnected)) {//


                    //2秒后重连
                    Observable.timer(3, TimeUnit.SECONDS)
                            .compose(bindUntilEvent(ActivityEvent.DESTROY))
                            .subscribe(num->{ resetConnection(host, groupid, mHub);});

                }

            }

            @Override
            public void OnReconnected() {
                LogUtil.i("setOnConnectionStateChange" + "-----OnReconnected------>");

            }

            //弱网重新连接
            @Override
            public void OnReconnecting() {
                LogUtil.i("setOnConnectionStateChange" + "-----OnReconnecting------>");
                if (!NetworkUtil.isNetworkAvailable(BaseSignalrActivity.this))return;//如果网络无效重置也没有意义
             //   if (connection != null && (connection.getState().equals(ConnectionState.Disconnected)||connection.getState().equals(ConnectionState.Reconnecting))) {//
                if (connection!=null){
                    resetConnection(host, groupid, mHub);
                }

              //  }
            }

            @Override
            public void OnConnectionSlow() {
                LogUtil.i("setOnConnectionStateChange" + "-----OnConnectionSlow------>");
            }
        });

    }


    @Override
    protected void onDestroy() {
        destorySignalr();
        if (observer != null) NetStateReceiver.removeRegisterObserver(observer);
        super.onDestroy();
    }

    private void destorySignalr() {
        finishAllSubScribe();
        SignalrConnectionManager.getInstance().finishConnection(con);
//        mHub = null;
//        groupid = null;
//        host = null;
        if(null!=hubConnection) hubConnection.disconnect();
        hubConnection=null;
        hubProxy = null;
    }

    //subscribe the event
    protected <T> void subscribe(String eventName, HubOnDataCallBackImpl<T> dataCallBack) {
        if (hubProxy == null) return;
        hubProxy.on(eventName, dataCallBack, Object.class);
        addSubScribe(eventName);


    }

    /**
     * 添加subScribe到堆栈
     */
    public void addSubScribe(String eventName) {
        if (subscribeStack == null) {
            subscribeStack = new Stack<>();
        }
        subscribeStack.add(eventName);
    }

    /**
     * 结束所有subScribe
     */
    public void finishAllSubScribe() {

        if (subscribeStack == null || hubProxy == null) return;//初始化的时候为空

        for (int i = 0, size = subscribeStack.size(); i < size; i++) {
            if (null != subscribeStack.get(i)) {
                hubProxy.removeSubscription(subscribeStack.get(i));

            }
        }
        subscribeStack.clear();
    }


    public class MyNetChangeObserver implements NetChangeObserver {

        @Override
        public void onNetConnected(NetUtils.NetType type) {
            LogUtil.i("setOnConnectionStateChange" + "-----onNetConnected------>" + type);
            //只要有网就重置链接
            resetConnection(host, groupid, mHub);
            OnNetSuccess();

        }

        @Override
        public void onNetDisConnect() {
            LogUtil.i("setOnConnectionStateChange" + "-----onNetDisConnect------>");
            OnNetFail();
        }
    }

    public boolean isAutoCreateSignalrConnection() {
        return false;
    }

    protected void resetConnection(String host, String groupid, String hub) {
        LogUtil.i("setOnConnectionStateChange========resetConnection==>");
        destorySignalr();
        initSignalr(host, groupid, hub);

    }
    public void subScribes() {}
    protected void OnNetSuccess() {}
    protected void OnNetFail() { }
}
