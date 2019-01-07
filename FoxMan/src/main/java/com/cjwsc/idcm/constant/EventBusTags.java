package com.cjwsc.idcm.constant;

/**
 * Created by yuzhongrong on 2017/8/31.
 */

public class EventBusTags {

    public static final String EVENT_UPDATE_MINEPAGE="updatemine";
    public static final String EVENT_UPLOAD_UPDATE="uploadupdate";

    //登录完成
    public static final String EVENT_LOGIN_COMPLETE="loginupdate";
    //退出完成
    public static final String EVENT_OUTLOGIN_COMPLETE="outloginupdate";
    //刷新购物车
    public static final String EVENT_UPDATE_SHOP_CART ="update_shop_cart";

    public static final String EVENT_TARGET_CLOSE="targetclose";

    public static final String TEST="targetclose";


    //添加收货地址成功通知key
    public static final String EVENT_CHANGE_ADDRESS ="change_address";
    //删除收货地址
    public static final String EVENT_DEL_ADDRESS ="delete_address";


    //token 失效
    public static final String EVENT_TOKEN_FAIL="token_fail";
    public static final String EVENT_TOKEN_FAIL_NOTIFY="token_fail_notify";
    //支付结束
    public static final String EVENT_PAY_END="pay_end";
    //第三方支付取消
    public static final String EVENT_THIRD_PAY_CANCEL ="third_pay_cancel";
    //第三方支付成功
    public static final String EVENT_THIRD_PAY_SUCCESS="third_pay_success";

    //售后跟新状态
    public static final String EVENT_APPLY_AFTER_STATE="after_state";

    //提现更新
    public static final String EVENT_APPLY_WITHDRAW="event_apply_withdraw";

    //跳转到首页
    public static final String EVENT_CHANGE_NAVIGATE_INDEX ="event_change_navigate_index";

    //更新昵称
    public static final String EVENT_UPDATE_NICKNAME="event_update_nickname";

    //更新消息通知
    public static final String EVENT_UPDATE_MESSAGE="event_update_message";


    //红包提现更新通知
    public static final String EVENT_UPDATE_RED2WALLET="event_update_red2wallet";


    //推送礼包消息
    public static final String EVENT_PUSH_GIFT="event_push_gift";

    //推送广告消息
    public static final String EVENT_PUSH_AD="event_push_gift";


    //推送消息更新
    public static final String EVENT_PUSH_XGPOSTBEAN="event_push_xgpostbean";

    //更新积分信息
    public static final String EVENT_UPDATE_INTEGRAL ="event_update_integral";




    //微信登录通知
    public static final String EVENT_WXLOGIN_SUCCESS_NOTIFY ="event_wxlogin_success_notify";


    //开团消息推送
    public static final String EVENT_PUSH_GROUP_INFO_BEAN = "event_push_group_info_bean";


    //更新应用强制退出消息推送
    public static final String EVENT_FORCE_EXIT = "event_force_exit_bean";



    //信鸽点击notification跳转更新

    public static final String EVENT_XGPUSHCLICKEDRESULT_NOTIFY = "xgpushclickedresult";

    //关闭闪屏页
    public static final String EVENT_FINISH_SPLASH ="finish_splash";


    //消息-更新通知栏展示和点击频率

    public static final String EVENT_PUSHMESSAGE_UPDATE ="event_pushmessage_update";

    public static final String EVENT_UPDATE_EVALUATE ="event_update_evaluate";


    public static final String EVENT_UPDATE_UPGRADE ="event_update_upgrade";

    public static final String EVENT_SERVICE_UPGRADE ="event_service_upgrade";

    public static final String SCAN2AUTH ="scan2auth";
    public static final String RECONNECT ="reconnect";


}
