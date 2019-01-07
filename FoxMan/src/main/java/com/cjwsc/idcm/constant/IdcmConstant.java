package com.cjwsc.idcm.constant;

/**
 * Created by ${zipp} on 2017/11/28.
 * 功能描述：这是公共常量
 */

public interface IdcmConstant {
    public static final int LOGIN = 1;
    public static final int UNLOGIN = 0;
    public static final String  SIGNAELURL = "http://192.168.1.192:8309";
    public static final String  SIGNAELURL_8409 = "http://192.168.1.192:8409";
    String HOME_PAGE="/app/activitys/MainActivity";
    String HISTORYORDER_PAGE="/app/activitys/HistoryOrderActivity";
    String LOGINPAGE="/mine_idcm/activitys/LoginActivity";
    String REGISTER_PAGE="/mine_idcm/activitys/RegisterActivity";
    String SETMONEYPS_PAGE="/mine_idcm/activitys/SetMoneyPsActivity";
    String SETTING_PAGE="/mine_idcm/activitys/SettingActivity";
    String LANGEUAGE_SELET_PAGE="/mine_idcm/activitys/LanguageSeletActivity";
    String SETPW_PAGE="/mine_idcm/activitys/ReSetPwActivity";
    String SETPWFINAL_PAGE="/mine_idcm/activitys/ReSetPwFinalActivity";
    String SELETECOUNTRY_PAGE="/mine_idcm/activitys/SelecCountryActivity";
    String PROPERTY_PAGE="/mine_idcm/activitys/PropertyTotalActivity";
    String RealNameVerify_PAGE="/mine_idcm/activitys/RealNameVerifyActivity";
    String USER_CENTER_PAGE="/mine_idcm/activitys/UserCenterActivity";
    String Contack_US_PAGE="/mine_idcm/activitys/ContackUsActivity";
    String ABOUTMEACTIVITY_PAGE="/mine_idcm/activitys/AboutMeActivity";
    String SetGESTURE_PAGE="/mine_idcm/activitys/SetPatternActivity";
    String ConfrimGESTURE_PAGE="/mine_idcm/activitys/ConfrimPatternActivity";
    String ClearGESTURE_PAGE="/mine_idcm/activitys/ClearPatternActivity";
    String BAND_EAMIL_PAGE="/mine_idcm/activitys/BandingEamilActivity";
    String BAND_TEl_PAGE="/mine_idcm/activitys/BandingTelActivity";
    String MYCHARGEMONEY_PAGE="/mine_idcm/activitys/MyRechargeMoneyActivity";
    String WITHDRAW_PAGE="/mine_idcm/activitys/WithDrawDepositActivity";
    String WITHDRAWBTC_PAGE="/mine_idcm/activitys/WithDrawBTCActivity";
    String RECHARGEBTC_PAGE="/mine_idcm/activitys/RechargeBTCActivity";
    String ADDBANKCARD_PAGE="/mine_idcm/activitys/AddBankCardActivity";
    String ADDBTCADDRESS_PAGE="/mine_idcm/activitys/AddBTCAddressActivity";
    String FinancialDetail_PAGE="/mine_idcm/activitys/FinancialDetailActivity";
    String ZXing_PAGE="/mine_idcm/activitys/ZXingActivity";
    String BankCardList_PAGE="/mine_idcm/activitys/AddBankCardListActivity";
    String FINALCIALRECORD_PAGE="/mine_idcm/activitys/FinalcialRecordActivity";
    String MODIFYUSERPS_PAGE="/mine_idcm/activitys/ModifyUserLoginPS";
    String MODIFYUSERMONEY_PAGE="/mine_idcm/activitys/ModifyUserMoneyPS";
    String MODIFYUSERTEL_PAGE="/mine_idcm/activitys/ModifyUserTel";
    String REMITTANCEMESSAGEACTIVITY_PAGE="/mine_idcm/activitys/RemittanceMessageActivity";
    String CHOICEMULITEPICACTIVIY_PAGE="/mine_idcm/activitys/ChoiceMulitePicActiviy";
    String KLine_PAGE="/app/activitys/quote/KLineActivity";
    String KLine_Land_PAGE="/app/activitys/quote/KLineLandActivity";
    String NoticeDetail_PAGE="/app/activitys/quote/NoticeDetailActivity";
    String NoticeList_PAGE="/app/activitys/quote/NoticeListActivity";
    String WebSetting_PAGE="/mine_idcm/activitys/WebActivity";
    String PINSetting_PAGE="/mine_idcm/activitys/PINSettingActivity";
    String PINEdit_PAGE="/mine_idcm/activitys/EditPayPassActivity";
    String MustLock_PAGE="/mine_idcm/activitys/MustSetPayActivity";
    String NumLock_PAGE="/mine_idcm/activitys/NumLockActivity";
    String ForgetPin_PAGE="/mine_idcm/activitys/ForgetPayPassActivity";
    String SELETESearchCOUNTRY_PAGE="/mine_idcm/activitys/SeleCountryActivity";
    String FindSetPin_PAGE="/mine_idcm/activitys/FindSetPinActivity";
    String DefaultCoin_SELET_PAGE="/mine_idcm/activitys/DefaultCoinSeletActivity";
    String ReSetPwActivity_PAGE="/mine_idcm/activitys/ReSetPwActivity211";
    String OPTIONAL_PAGE="/app/activitys/OptionalActivity";
    String SAFEVERIFY_PAGE="/mine_idcm/activitys/SafeVerifyActivity";
    String ServiceMaintenance_PAGE="/mine_idcm/activitys/ServiceMaintenanceActivity";


    /*Sp保存的Key*/
    String ACCOUNTLOGINKEY="accountLoginKey";
    String LOGIN_CACHE="login_cache";
    String LANGUAGE_POSITION="language_position";
    String DEFAULT_COIN_POSITION="default_coin_position";
    String KEY_PATTERN_SHA1="pref_key_pattern_sha1";
    String DEFAULT_PATTERN_SHA1="";
    String FOUNEPS_TELOREMAIL = "founePs_teloremail";
    String FOUNEPS_Verfy = "founePs_tel_Verfy";
    String FOUNEPS_Area = "founePs_tel_area";
    String COIN_ID = "Coin_id";
    String CAN_WITHDRAW_MONEY = "Can_WithDraw_Money";
    String TRADECONFIG_ID = "tradeConfig_id";
    String TRADECONFIG_POSITION = "tradeConfig_position";
    String ASSETSCODE = "AssetsCode";
    String CoinTag = "CoinTag";
    String CoinUrl = "CoinUrl";
    String AVAILABLEAMOUNT = "AvailableAmount";
    String RECORDID = "recordId";
    String FINAC_RECORD_CATRGORY = "Finac_record_catrgory";
    String Finac_record_statu = "Finac_record_statu";
    String FINAC_RECORD_DATE = "Finac_record_date";
    String FINAC_RECORD_ACCOUNT = "Finac_record_account";
    String FINAC_RECORD_PROCEDURE = "Finac_record_procedure";
    String GroupID="GroupID";
    String Digits="Digits";
    String ISFOUNDMONEYPS="isFoundMoneyPs";
    String GESTRUE_CHARTEGORY="gestrue_chartegory";
    String NOTICELIST_OBJECT="NoticeList_OBJECT";
    String NOTICELIST_TIME="NoticeList_TIME";
    String NOTICELIST_CONTENT="NoticeList_CONTENT";
    String NOTICELIST_TITLE="NoticeList_TITLE";
    String FROM_USER_CENTER="from_user_center";
    String WEB_URL = "web_url";
    String SHOWTRADEPAGE = "ShowTradePage";
    String VER_CODE = "Ver_Code";
    String VERIFY_TYPE = "verify_type";
    String Scanner_Business = "Scanner_Business";
    String Ignore_notice_time = "Ignore_notice_time";
    String ISNEED_GOOGLE="isNeedGoogle";
    String OptinalTitle="OptinalTitle";
    String OptionalConfigId="OptionalConfigId";
    String CurrentMineBuyerCoinPresion="CurrentMineBuyerCoinPresion";

    /*序列化bean*/
    String NOTICELIST_JSON="/service/json/notice";

    String KEYWORDS = "keywords";

}
