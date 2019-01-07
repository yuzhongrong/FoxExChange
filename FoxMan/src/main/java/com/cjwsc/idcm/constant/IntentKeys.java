package com.cjwsc.idcm.constant;

/**
 * 放页面传值key
 * Created by yuzhongrong on 2017/9/5.
 */

public class IntentKeys {
    //购物车->提交订单页，商品列表
    public static final String CHOSEN_SHOP_CART_LIST = "shop_cart_list";
    //0：加入购物车 1：立即购买
    public static final String CHOSEN_SHOP_CART_TYPE = "shop_cart_type";
    public static final String COMMODITY_TYPE = "commodity_type";

    public static final String COMMODITY_OPEN_GROUPID = "openGroupId";
    public static final String COMMODITY_OPEN_GROUP_TYPE = "openGroupType";
    public static final String OPENID = "openid";
    public static final String THIRD_TYPE = "type";//2:微信2：qq

    public static final String ARTICLE_ID = "articleid";
    public static final String PAY_PWDMSG_CODE = "paypwdcode";
    public static final String ADDRESSBEAN = "addressbean";
    public static final String KEY_PHONE = "phone";
    public static final String SETTLE_ID = "settle_id";
    public static final String FEED_ID = "feed_id";
    public static final String SETTLE_TYPE = "settle_type";
    public static final String SETTLE_MODE = "settle_mode";
    public static final String STATUS = "status";
    public static final String COME_FROM = "come_from";
    public static final String COMMODITY_TOTAL_PRICE = "commodity_total_price";
    public static final String COUPON_CODE = "coupon_code";
    public static final String COUPON_VALUE = "coupon_value";
    public static final String COUPON_FROM = "coupon_from";



    //支付回传参数
    public static final String KEY_AMOUNT = "amount";//订单金额
    public static final String KEY_INVOICEID = "invoiceId";//订单号
    public static final String KEY_PAY_TYPE = "type";//支付方式
    public static final String KEY_ISUSEWALLET = "isUseWallet";//是否使用钱包支付

    //end

    //订单
    public static final String ORDER_CODE = "order_code";
    public static final String ORDER_STATUS = "order_status";
    public static final String ORDER_MANAGER_INDEX = "order_manager_index";
    public static final String ORDER_PAYMENT_BEAN = "order_payment_bean";
    public static final String ORDER_LEFT_PAY_TIME = "order_left_pay_time";
    public static final String ORDER_REMAIN_TIME = "order_remain_time";
    public static final String ORDER_PAY_TYPE = "order_pay_type";
    public static final String ORDER_AMOUNT = "order_amount";
    public static final String ORDER_RETURN_FLOW_CODE = "return_flow_code";
    public static final String ORDER_PRODUCT_BEAN = "order_products_bean";
    public static final String ORDER_PAYMENT_CALLBACK = "order_payment_callback";
    public static final String PRODUCT_ID = "productId";


    //售后

    public static final String AFTER_HEALP = "after_healp";
    public static final String AFTER_SALE_RESULT= "aftersale_result";
    public static final String PRODUCT_INFO= "productinfo";
    //寄回商品
    public static final String SEND_BACK_COMMODITY = "SEND_BACK_COMMODITY";

    //用户信息
    public static final int USER_COMMON_CODE = 101;
    public static final int NICKNAME_CODE = 101;
    public static final int SEX_CODE = 201;
    public static final int BIRTHDAY_CODE = 301;


    //进首页
    public static final String SKIP_HOME_KEY = "skip_home_key";
    public static final String WEB_URL = "WEB_URL";
    public static final String FROM_NAVI = "from_navi";
    //寻乡味 特色馆信息
    public static final String FIND_SPECIAL_SHOP_INFO = "find_special_shop_info";
    //寻乡味 详情页主页
    public static final String FIND_SPECIAL_SHOP_HOME = "find_special_shop_home";
    //寻乡味 详情页思
    public static final String FIND_SPECIAL_SHOP_XIANG = "find_special_shop_xiang";
    //寻乡味 详情页念
    public static final String FIND_SPECIAL_SHOP_NIAN = "find_special_shop_nian";
    //寻乡味 详情页你
    public static final String FIND_SPECIAL_SHOP_NI = "find_special_shop_ni";
    //搜索关键字
    public static final String FIND_SEARCH_CONTENT= "search_content";
    //特色馆名字
    public static final String FIND_SPECIAL_SHOP_NAME= "search_content";
    //
    //支付宝用户信息

    public static final String KEY_ALI_ACCOUNT = "key_ali_account";



    //账单
    public static final String KEY_MONTH = "key_month";

    public static final String KEY_ACCOUNT_DETAIL = "key_account_detail";

    //切换地址
    public static final String KEY_CHANGE_ADDRESS = "key_change_address";

    public static final String SERVICE_SEARCH_WORD = "service_search_word";
    public static final String CURRENT_MONTH = "current_month";

    public static final String GROUP_ID = "group_id";


    public static final String RED2WALLET="redpackagebean";

    public static final String USER_ROLE = "user_role";
    public static final String BUNDLE = "bundle";

    public static final String URL = "url";

}
