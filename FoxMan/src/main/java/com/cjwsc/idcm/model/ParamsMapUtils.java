package com.cjwsc.idcm.model;
import android.text.TextUtils;

import com.cjwsc.idcm.Utils.BaseParamsMapUtil;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;

/**
 * 作者：yzr
 * <p>
 * 邮箱:956942189@qq.com
 * <p>
 * 日期：2016/2/25
 * <p>
 * 描述信息： 参数封装类
 * <p>
 * 备注信息:
 */
public class ParamsMapUtils extends BaseParamsMapUtil {

    public static MediaType mediaTypeJson = MediaType.parse("application/json;charset=utf-8");
    public static MediaType mediaTypeFrom = MediaType.parse("multipart/form-data");

    private static Map<String, String> mapparam;

    /**
     * 默认参数
     *
     * @return
     */
    public static Map<String, String> getDefaultParams() {
        return getParamsMap();
    }

    /**
     * 注册获取短信验证码
     *
     * @param tel
     * @param businessType
     * @param area
     * @param verifyDeviceType
     * @return
     */
    public static Map<String, String> getRegisterPhoneCheck(String tel, String businessType, String area, String verifyDeviceType) {
        Map<String, String> params = getDefaultParams();
        params.put("phoneOrEmail", tel);
        params.put("businessType", businessType);
        params.put("areaCode", area);
        params.put("verifyDeviceType", verifyDeviceType);
        return params;
    }

    public static Map<String, String> getVerifyCode(String codeKey, String imgCode, String verifyDeviceType, String businessType, String areaCode, String phoneOrEmail) {
        Map<String, String> params = getDefaultParams();
        params.put("codeKey", codeKey);
        params.put("imgCode", imgCode);
        params.put("verifyDeviceType", verifyDeviceType);
        params.put("businessType", businessType);
        params.put("phoneOrEmail", phoneOrEmail);
        params.put("areaCode", areaCode);
        params.put("verifyDeviceType", verifyDeviceType);
        return params;
    }

    /**
     * 找回密码第一步
     *
     * @param verifyDeviceType
     * @param areaCode
     * @param phoneOrEmail
     * @param verifyCode
     * @return
     */
    public static Map<String, String> foundPassWordFirst(String verifyDeviceType, String areaCode, String phoneOrEmail, String verifyCode) {
        Map<String, String> params = getDefaultParams();
        params.put("verifyDeviceType", verifyDeviceType);
        params.put("areaCode", areaCode);
        params.put("phoneOrEmail", phoneOrEmail);
        params.put("verifyCode", verifyCode);
        return params;
    }

    public static Map<String, String> foundPassWordFinal(String VerifyCode, String AreaCode, String PhoneOrEmail, String Password, String ConfirmPassword) {
        Map<String, String> params = getDefaultParams();
        params.put("VerifyCode", VerifyCode);
        params.put("AreaCode", AreaCode);
        params.put("PhoneOrEmail", PhoneOrEmail);
        params.put("Password", Password);
        params.put("ConfirmPassword", ConfirmPassword);
        return params;
    }

    public static Map<String, String> getRegisterEmailCheck(String email, String type) {
        Map<String, String> params = getDefaultParams();
        params.put("Email", email);
        params.put("Type", type);
        return params;
    }

    public static Map<String, String> registerAccount(String countryCode, String areaCode, String phoneOrEmail, String account, String password, String verifyCode, String verifyDeviceType) {
        Map<String, String> params = getDefaultParams();
        params.put("countryCode", countryCode);
        params.put("areaCode", areaCode);
        params.put("phoneOrEmail", phoneOrEmail);
        params.put("account", account);
        params.put("password", password);
        params.put("verifyCode", verifyCode);
        params.put("verifyDeviceType", verifyDeviceType);
        return params;
    }

    public static Map<String, String> registerEmailAccount(String account, String userName, String password, String confrimPs, String Code) {
        Map<String, String> params = getDefaultParams();
        params.put("Account", account);
        params.put("UserName", userName);
        params.put("Password", password);
        params.put("ConfirmPassword", confrimPs);
        params.put("Code", Code);
        return params;
    }


    public static Map<String, String> userLogin(String account, String ps, String areaCode) {
        Map<String, String> params = getDefaultParams();
        params.put("account", account);
        params.put("password", ps);
        params.put("areaCode", areaCode);
        return params;
    }
    public static Map<String, String> bindEmail(String email, String pin, String tpwd) {
        Map<String, String> params = getDefaultParams();
        params.put("mail", email);
        params.put("pin", pin);
        params.put("tpwd", tpwd);
        return params;
    }

    public static Map<String, String> bindTel(String aredCode, String phone, String pin, String tpwd) {
        Map<String, String> params = getDefaultParams();
        params.put("areaCode", aredCode);
        params.put("phone", phone);
        params.put("pin", pin);
        params.put("tpwd", tpwd);
        return params;
    }

    public static Map<String, String> getKLineData(String varietyId, String StartDate,String EndDate, String lineType, String pagesize, String pageIndex) {
        Map<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(StartDate)){
            params.put("StartDate",StartDate);
        }
        if (!TextUtils.isEmpty(EndDate)){
            params.put("EndDate",EndDate);
        }
        params.put("TradingConfigId", varietyId);
        params.put("LineType", lineType);
        params.put("PageSize", pagesize);
        params.put("PageIndex", pageIndex);
        return params;
    }

    public static Map<String, String> isExistAccount(String username) {
        Map<String, String> params = new HashMap<>();
        params.put("account", username);
        return params;
    }

    public static Map<String, String> getHandicap(String tradingConfigId, String deep, String precision) {
        Map<String, String> params = getDefaultParams();
        params.put("tradingConfigId", tradingConfigId);
        params.put("deep", deep);
        params.put("precision", precision);
        return params;
    }

    public static Map<String,String> sendTelCode(String AreaCode, String PhoneOrEmail, String BussinessType, String LanguageCode){
        Map<String, String> params = getDefaultParams();
        params.put("AreaCode",AreaCode);
        params.put("PhoneOrEmail",PhoneOrEmail);
        params.put("BussinessType",BussinessType);
        params.put("LanguageCode",LanguageCode);
        return params;
    }
    public static Map<String,String> sendEmailVerify( String PhoneOrEmail, String BussinessType, String LanguageCode){
        Map<String, String> params = getDefaultParams();
        params.put("PhoneOrEmail",PhoneOrEmail);
        params.put("BussinessType",BussinessType);
        params.put("LanguageCode",LanguageCode);
        return params;
    }

    public static Map<String,String> setMoneyPS( String pwd){
        Map<String, String> params = getDefaultParams();
        params.put("pwd",pwd);
        return params;
    }

    public static Map<String,String> modifyLoginPs(String oldPwd, String newPwd, String pin, String passwordLevel){
        Map<String, String> params = getDefaultParams();
        params.put("oldPwd",oldPwd);
        params.put("newPwd",newPwd);
        params.put("pin",pin);
        params.put("passwordLevel",passwordLevel);
        return params;
    }
    public static Map<String,String> modifyMoneyPs(String oldPwd, String newPwd, String pin ){
        Map<String, String> params = getDefaultParams();
        params.put("oldPwd",oldPwd);
        params.put("newPwd",newPwd);
//        params.put("pin",pin);
        return params;
    }

    public static Map<String,String> foundMoneyPs(String newPwd, String pin, int  verifyType){
        Map<String, String> params = getDefaultParams();
        params.put("newPwd",newPwd);
        params.put("pin",pin);
        params.put("verifyType","" + verifyType);
        return params;
    }

    public static Map<String,String> getImgCode(String codeKey, String verifyDeviceType, String clientType){
        Map<String, String> params = getDefaultParams();
        params.put("codeKey",codeKey);
        params.put("verifyDeviceType",verifyDeviceType);
        params.put("clientType",clientType);
        return params;
    }

    public static Map<String,String> addBankCard(String BankName, String CardNumber, String CardUserName, String Branch,String TransactionPassword, boolean isDefault, String SWIFTCode){
        Map<String, String> params = getDefaultParams();
        params.put("BankName",BankName);
        params.put("CardNumber",CardNumber);
        params.put("CardUserName",CardUserName);
        params.put("Branch",Branch);
        params.put("TransactionPassword",TransactionPassword);
        params.put("IsDefault",isDefault+"");
        params.put("SWIFTCode",SWIFTCode);
        return params;
    }

    public static Map<String ,String> deleteBankCard(String cardId){
        Map<String, String> params = getDefaultParams();
        params.put("id",cardId);
        return params;
    }

    public static Map<String ,String> getCoinTransactionOutConfig(String assetsID){
        Map<String, String> params = getDefaultParams();
        params.put("assetsID",assetsID);
        return params;
    }

    public static Map<String ,String> getWithDrawMoney(String ApplyAmount, String BankCardID, String TradePassword, String GoogleCode){
        Map<String, String> params = getDefaultParams();
        params.put("ApplyAmount",ApplyAmount);
        params.put("BankCardID",BankCardID);
        params.put("TradePassword",TradePassword);
        params.put("GoogleCode",GoogleCode);
        return params;
    }
    public static Map<String ,String> deletBTCAddress(String id){
        Map<String, String> params = getDefaultParams();
        params.put("id",id);
        return params;
    }

    public static Map<String ,String> addBTCAddress(String CoinID, String Address, String Remark, String TransactionPassword){
        Map<String, String> params = getDefaultParams();
        params.put("CoinID",CoinID);
        params.put("Address",Address);
        params.put("Remark",Remark);
        params.put("TransactionPassword",TransactionPassword);
        return params;
    }

    public static Map<String ,String> checkCoinAddress(String coinID, String address){
        Map<String, String> params = getDefaultParams();
        params.put("coinID",coinID);
        params.put("address",address);
        return params;
    }

    public static Map<String ,String> getUserCoinAddress(String CoinID, String Remark, String CoinAddress, int PageSize, int PageIndex){
        Map<String, String> params = getDefaultParams();
        params.put("CoinID",CoinID);
        params.put("Remark",Remark);
        params.put("CoinAddress",CoinAddress);
        params.put("PageSize",PageSize+"");
        params.put("PageIndex",PageIndex+"");
        return params;
    }

    public static Map<String ,String> applyTransctionOut(String CoinID, String CoinAddress, String ApplyQuantity, String TradePassword, String GoogleCode){
        Map<String, String> params = getDefaultParams();
        params.put("CoinID",CoinID);
        params.put("CoinAddress",CoinAddress);
        params.put("ApplyQuantity",ApplyQuantity);
        params.put("TradePassword",TradePassword);
        params.put("GoogleCode",GoogleCode);
        return params;
    }

    public static Map<String ,String> getAsstesRecordList(String AssetsID, int AssetsRecordType, String PageSize, String PageIndex){
        Map<String, String> params =new HashMap<>();
        if (!TextUtils.isEmpty(AssetsID)){
            params.put("AssetsID",AssetsID);
        }
        if (AssetsRecordType!=0){
            params.put("AssetsRecordType",AssetsRecordType+"");
        }
        params.put("PageSize",PageSize);
        params.put("PageIndex",PageIndex);
        return params;
    }

    public static Map<String,String>  getCurrentEntrustOrder(String TradingConfigID, String PageIndex, String PageSize){
        Map<String, String> params = getDefaultParams();
        if (TradingConfigID!=null&&!TextUtils.isEmpty(TradingConfigID)){
            params.put("TradingConfigID",TradingConfigID);
        }
        params.put("PageIndex",PageIndex);
        params.put("PageSize",PageSize);
        return params;
    }

    public static Map<String,String>  sendOrder(String TradingConfigID, String Symbol, String Quantity, String Price, String Side, String Type, String TradePassword){
        Map<String, String> params = getDefaultParams();
        params.put("TradingConfigID",TradingConfigID);
        params.put("Symbol",Symbol);
        params.put("Quantity",Quantity);
        params.put("Price",Price);
        params.put("Side",Side);
        params.put("Type",Type);
        params.put("TradePassword",TradePassword);
        return params;
    }

    public static Map<String,String>  cancleOrder(String tradingConfigID, String side, String orderID){
        Map<String, String> params = getDefaultParams();
        params.put("tradingConfigID",tradingConfigID);
        params.put("side",side);
        params.put("orderID",orderID);
        return params;
    }

    public static Map<String,String>  getDealDetailList(String orderID,String tradeSide){
        Map<String, String> params = getDefaultParams();
        params.put("orderID",orderID);
        params.put("tradeSide",tradeSide);
        return params;
    }

    public static Map<String,String>  getCustormerDealList(String PageIndex, String PageSize){
        Map<String, String> params = getDefaultParams();
        params.put("PageIndex", PageIndex);
        params.put("PageSize", PageSize);
        return params;
    }

    public static Map<String,String>  getAssestRecordDetail(String recordId){
        Map<String, String> params = new HashMap<>();
        params.put("recordId",recordId);
        return params;
    }

    public static Map<String,String>  getCoinAssets(String coinID){
        Map<String, String> params = new HashMap<>();
        params.put("coinID",coinID);
        return params;
    }

    public static Map<String,String>  getNoticeMessageList( String pageIndex,String pageSize,String languageCode,String articleType){
        Map<String, String> params = new HashMap<>();
        params.put("pageIndex",pageIndex);
        params.put("pageSize",pageSize);
        params.put("languageCode",languageCode);
        params.put("articleType",articleType);
        return params;
    }

    public static Map<String,String>  userRecharge(String amount){
        Map<String, String> params = new HashMap<>();
        params.put("amount",amount);
        return params;
    }

    public static Map<String,String> setDefaultVarify(String varietyId){
        Map<String, String> params = new HashMap<>();
        params.put("varietyId",varietyId);
        return params;
    }

    public static Map<String,String>  getAllHistoryTradeOrder(String tradingConfigId,String PageIndex, String PageSize){
        Map<String, String> params = getDefaultParams();
        params.put("tradingConfigId",tradingConfigId);
        params.put("PageIndex",PageIndex);
        params.put("PageSize",PageSize);
        return params;
    }
    public static Map<String,String>  AddOrDelete(String configId,int collectionType){
        Map<String, String> params = getDefaultParams();
        params.put("configId",configId);
        params.put("collectionType",String.valueOf(collectionType));
        return params;
    }

    public static Map<String, String> checkPIN(String pin){
        Map<String, String> params =  getDefaultParams();
        params.put("pin", pin);
        return params;
    }

    public static Map<String, String> checkVerifyCode(String code, int bussinessType, int verifyType){
        Map<String, String> params = getDefaultParams();
        params.put("code", code);
        params.put("bussinessType", "" + bussinessType);
        params.put("verifyType", "" + verifyType);
        return params;
    }

    public static Map<String, String> checkPsw(String oldPwd){
        Map<String, String> params = getDefaultParams();
        params.put("oldPwd", oldPwd);
        return params;
    }
}
