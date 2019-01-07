package com.cjwsc.idcm.signarl;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.cjwsc.idcm.iprovider.impl.UploadImgProviderImpl;
import com.cjwsc.idcm.net.callback.RxSubscriber;
import com.cjwsc.idcm.net.exception.ResponseThrowable;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.cjwsc.idcm.iprovider.UploadImgProviderServices;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import microsoft.aspnet.signalr.client.hubs.HubProxy;

/**
 * Created by admin-2 on 2018/4/5.
 */

public class GroupsUtils {

    public static final String send="Send";//发送消息
    public static final String joingroup="JoinGroup";//加入聊天室

    public static final String leavegroup="Leavegroup";//离开聊天室

    public static final String removeGroup="removeGroup";//移除分组

    public static  final String addgroup="AddGroup";

    UploadImgProviderServices uploadImgProviderServices;


    public GroupsUtils() {
        uploadImgProviderServices =new UploadImgProviderImpl();
    }

    public static void addGroupId(BaseSignalrActivity activity,HubProxy hubProxy, String groupid, SwitchGroupIdCallBack callBack){
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> observable) throws Exception {
                String addresult=  hubProxy.invoke(String.class, addgroup, groupid).get(10, TimeUnit.SECONDS);
                if(observable.isCancelled())return;
                if(!TextUtils.isEmpty(addresult)&&addresult.equals("success")){
                    String setResult= hubProxy.invoke(String.class, "SetDisplayPrecision", groupid, 8).get(5, TimeUnit.SECONDS);
                    if(!TextUtils.isEmpty(setResult)&&setResult.equals("success")){
                            observable.onNext(setResult);
                    }else{
                         observable.onError(new ResponseThrowable("fail"));

                    }

                }else{
                    observable.onError(new ResponseThrowable("fail"));
                }
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>() {
                    @Override
                    public void onSuccess(String s) {
                        callBack.OnSeccess();
                    }

                    @Override
                    protected void onError(ResponseThrowable ex) {
                        callBack.OnFail();
                    }
                });
    }


    /**
     *  发送消息
     * @param callBack
     * @param content:传入文字的内容
     */
    public static void senMessageChat(Context context,String content, SwitchGroupIdCallBack callBack ){

        BaseSignalrActivity activity=(BaseSignalrActivity)context;
        HubProxy hubProxy=activity.hubProxy;
        String groupid=activity.groupid;
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> observable) throws Exception {
                        //发送消息  参数：[group:聊天室id,action:动作,args:内容]

                       try{//有时候后台signalr会gg
                           String sendmsg=  hubProxy.invoke(String.class, send, groupid,content,"").get(10, TimeUnit.SECONDS);
                           if(!TextUtils.isEmpty(sendmsg)&&sendmsg.equals("success")){
                               observable.onNext(sendmsg);
                           }else{
                               observable.onError(new ResponseThrowable("fail"));
                           }
                       }catch (Exception e){
                           observable.onError(new ResponseThrowable("fail"));

                       }



            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RxSubscriber<String>() {
                    @Override
                    public void onSuccess(String s) {
                        callBack.OnSeccess();
                    }

                    @Override
                    protected void onError(ResponseThrowable ex) {
                        callBack.OnFail();
                    }
                });
    }


    /**
     *  发送图片消息
     * @param callBack
     * @param path:图片内容
     */
    public static void sendImgMessageChat(Context context,String path, SwitchGroupIdCallBack callBack){
        BaseSignalrActivity activity=(BaseSignalrActivity)context;
        HubProxy hubProxy=activity.hubProxy;
        String groupid=activity.groupid;

        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> observable) throws Exception {
                //发送消息  参数：[group:聊天室id,action:动作,args:内容]

                String sendmsg=  hubProxy.invoke(String.class, send, groupid,"",path).get(10, TimeUnit.SECONDS);
                if(!TextUtils.isEmpty(sendmsg)&&sendmsg.equals("success")){
                    observable.onNext(sendmsg);
                }else{
                    observable.onError(new ResponseThrowable("fail"));
                }
                observable.onComplete();
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new RxSubscriber<String>() {
                    @Override
                    public void onSuccess(String s) {
                        callBack.OnSeccess();
                    }
                    @Override
                    protected void onError(ResponseThrowable ex) {
                        callBack.OnFail();
                    }
                });
    }




    public void sendImgMessageChat2(Context context,String path, SwitchGroupIdCallBack callBack){
        BaseSignalrActivity activity=(BaseSignalrActivity)context;
        HubProxy hubProxy=activity.hubProxy;
        String groupid=activity.groupid;

        uploadImgProviderServices.requestUpload(Arrays.asList(path))
                .compose(activity.bindToLifecycle())
                .subscribeWith(new RxSubscriber<Object>() {

                    @Override
                    protected void onError(ResponseThrowable ex) {
                        callBack.OnFail();
                    }

                    @Override
                    public void onSuccess(Object s) {
                        try {
                            String  sendmsg = hubProxy.invoke(String.class, send, groupid,"",s.toString()).get(10, TimeUnit.SECONDS);
                            if(!TextUtils.isEmpty(sendmsg)&&sendmsg.equals("success")){

                                callBack.OnSeccess();
                            }else{
                                callBack.OnFail();

                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            callBack.OnFail();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                            callBack.OnFail();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                            callBack.OnFail();
                        }


                    }
                });




    }




    //添加到聊天室
    public static void addChatGroupId(BaseSignalrActivity activity,HubProxy hubProxy,String groupid, SwitchGroupIdCallBack callBack){
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> observable) throws Exception {
                String addresult=  hubProxy.invoke(String.class, joingroup, groupid).get(10, TimeUnit.SECONDS);
                if(observable.isCancelled())return;
                if(!TextUtils.isEmpty(addresult)&&addresult.equals("success")){
                    observable.onNext(addresult);

                }else{
                    observable.onError(new ResponseThrowable("fail"));
                }
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
                .compose(activity.bindUntilEvent(ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>() {
                    @Override
                    public void onSuccess(String s) {
                        callBack.OnSeccess();
                    }

                    @Override
                    protected void onError(ResponseThrowable ex) {
                        callBack.OnFail();
                    }
                });
    }








    //离开聊天室
    public static void removeChatGroupId(HubProxy hubProxy,String groupid, SwitchGroupIdCallBack callBack){
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(FlowableEmitter<String> observable) throws Exception {
                String addresult=  hubProxy.invoke(String.class, leavegroup, groupid).get(10, TimeUnit.SECONDS);
                if(!TextUtils.isEmpty(addresult)&&addresult.equals("success")){
                    observable.onNext(addresult);

                }else{
                    observable.onError(new ResponseThrowable("fail"));
                }
            }
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxSubscriber<String>() {
                    @Override
                    public void onSuccess(String s) {
                        callBack.OnSeccess();
                    }

                    @Override
                    protected void onError(ResponseThrowable ex) {
                        callBack.OnFail();
                    }
                });
    }











    public static  void switchGroupID(HubProxy hubProxy,String newgroupid,String oldgroupid,SwitchGroupIdCallBack callBack){
        if(hubProxy!=null){
            Flowable.create(new FlowableOnSubscribe<String>() {
                @Override
                public void subscribe(FlowableEmitter<String> observable) throws Exception {

                    String removeresult=  hubProxy.invoke(String.class, removeGroup,oldgroupid).get(5, TimeUnit.SECONDS);
                    if(!TextUtils.isEmpty(removeresult)&&removeresult.equals("success")){

                    //    addGroupId(hubProxy,newgroupid,callBack);

                    }else{
                        observable.onError(new ResponseThrowable("fail"));

                    }
                }
            }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RxSubscriber<String>() {
                        @Override
                        public void onSuccess(String s) {
                            callBack.OnSeccess();
                        }

                        @Override
                        protected void onError(ResponseThrowable ex) {
                            callBack.OnFail();
                        }
                    });

        }


    }
}
