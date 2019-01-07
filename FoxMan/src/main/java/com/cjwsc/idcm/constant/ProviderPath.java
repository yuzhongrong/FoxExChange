package com.cjwsc.idcm.constant;

/**
 * Created by hzp on 2017/12/18.
 */

public class ProviderPath {

    //登入接口暴露
    public static final String ACCOUNT_LOGIN = "/mine_idcm/services/AccountLoginImpl";


    //检查服务器是否在维修
    public static final String checkServiceMaintenance = "/mine_idcm/services/checkServiceMaintenance";

    // ------------------------------路径命名格式: /模块/services/provider接口名------------------------------------

    public static final String path_CheckVersionProviderServiceLogic = "/common/services/CheckVersionProviderServiceLogic";
    public static final String path_GetCoinProviderServicesLogic = "/app/services/GetCoinProviderServicesLogic";
    public static final String path_EditPayPassProviderServices_CheckOriginalPwdProviderServices = "/app/services/EditPayPassProviderServices_CheckOriginalPwdProviderServices";
    public static final String path_MoneyBagListProviderServices_GetWalletBalanceProviderServices = "/app/services/MoneyBagListProviderServices_GetWalletBalanceProviderServices";
    public static final String path_SecurityPayServices = "/pay/iprovider/SecurityPayServices";
    public static final String path_SecurityPayCustomerInfoServices = "/pay/iprovider/SecurityPayCustomerInfoServices";
    public static final String path_SecurityPayValidAddressServices = "/pay/iprovider/SecurityPayValidAddressServices";
    public static final String path_GetAccountAddressServicer = "/pay/iprovider/GetAccountAddressServicer";

    public static final String path_VerifyCodeAndEMailProviderServices = "/app/services/VerifyCodeAndEMailProviderServices";

    public static final String path_AccountAddressInfoProviderServices_TransMsgProviderServices = "/app/services/AccountAddressInfoProviderServices_TransMsgProviderServices";

    public static final String path_SettingStateProviderServices = "/app/services/SettingStateProviderServices";

    public static final String path_GetAddAllCurrencyProviderServices = "/app/services/GetAddAllCurrencyProviderServices";
    public static final String path_LoginWalletProviderServices = "/app/services/LoginWalletLogic";
    public static final String path_CreateSetPassProviderServices = "/app/services/CreateSetPassLogic";
    public static final String path_ExitAppProviderServices = "/app/services/ExitAppProviderServices";
    public static final String path_SetPayPassInfoProviderServices = "/app/services/SetPayPassInfoProviderServices";

    public static final String path_GetExchangeDetailProviderServices = "/app/services/GetExchangeDetailProviderServices";
    public static final String path_ExchangeInProviderServices = "/app/services/ExchangeInProviderServices";

    //获取交易配置信息
    public static final String verfityUserName = "/app/services/VerifyUserNameProviderServices";


    public static final String path_GetExchangeBalanceProviderServices = "/app/services/GetExchangeBalanceProviderServices";
    public static final String path_GetCoinListProviderServices = "/app/services/GetCoinListProviderServices";

    //------------------------------OTC------------------------------------
    public static final String path_GetApplyCoinListProviderServices = "/otc/services/GetCoinListProviderServices";
    public static final String path_GetApplyCoinCurrListProviderServices = "/otc/services/MoneyBagListProviderServices";
    public static final String path_OTCLocalCurrencyProviderServices = "/otc/services/OTCLocalCurrencyProviderServices";
    public static final String path_GetPaymentModeManagementServices = "/otc/services/GetPaymentModeManagementServices";
    public static final String path_OTCGetCoinListProviderServices = "/otc/services/OTCGetCoinListProviderServices";
    public static final String path_GetOtcTradeSettingServices = "/otc/services/GetOtcTradeSettingServices";
    public static final String path_OTCAddBuyCurrProviderServices = "/otc/services/OTCAddBuyCurrProviderServices";
    public static final String path_OTCGetBuyListProviderServices = "/otc/services/OTCGetBuyListProviderServices";
    public static final String path_OTCPaymentMethodManagerPageServices = "/otc/services/OTCPaymentMethodManagerPageServices";
    public static final String path_OTCGetOtcOrders = "/otc/services/OTCGetOtcOrdersServices";


    //上传图片
    public static final String path_UploadServices = "/FoxMan/services/UploadImgProviderServices";
    //获取关于我们页面列表
    public static final String path_AboutUsServices = "/app/services/GetAboutUsListProviderServices";

    //获取关于我们页面列表
    public static final String path_GetTradeConfigServices = "/FoxMan/services/GetTradeConfigServices";
}
