package com.cjwsc.idcm.manager.TongLianPayManager;

import android.text.TextUtils;

import com.cjwsc.idcm.Utils.LogUtil;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yuzhongrong on 2017/9/29.
 */

public class PaaCreator {


    public static JSONObject randomPaa(TonlianInfoPayResp orderCreateReturnBean) {
//        String amount = "13900";
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
//		String timeStr = dateFormat.format(new Date());
//		String orderStr = timeStr + "0000";


        /**
         * inputCharset : 1
         * receiveUrl : http://portalservice.pt.wmjtc.com/pay/notifypay/quickwapNotify
         * version : v1.0
         * signType : 0
         * merchantId : 2000944035311411817
         * orderNo : 11104170929365669188
         * orderAmount : 4150
         * orderCurrency : 0
         * orderDatetime : 20170929191533
         * productName : 农特产APP购物
         * ext1 : <USER>170926308530823</USER>
         * payType : 33
         * sign : 1C6B194FAEC01468576881247373E189
         */


        String inputCharset=orderCreateReturnBean.getQuickappData().getInputCharset()+"";
        String receiveUrl=orderCreateReturnBean.getQuickappData().getReceiveUrl();
        String version=orderCreateReturnBean.getQuickappData().getVersion();
        String signType=orderCreateReturnBean.getQuickappData().getSignType()+"";

        String amount = orderCreateReturnBean.getQuickappData().getOrderAmount()+"";
        String timeStr =  orderCreateReturnBean.getQuickappData().getOrderDatetime() ;
        String orderStr = orderCreateReturnBean.getQuickappData().getOrderNo();
        String ext1 =orderCreateReturnBean.getQuickappData().getExt1() ;
        String merchantId =orderCreateReturnBean.getQuickappData().getMerchantId() ;
        String productName =orderCreateReturnBean.getQuickappData().getProductName() ;
        String sign=orderCreateReturnBean.getQuickappData().getSign();
      //  String key =orderCreateReturnBean.getKey() ;
        JSONObject paaParams = new JSONObject();
        try {
            paaParams.put("inputCharset",inputCharset);
            paaParams.put("receiveUrl", receiveUrl);
            paaParams.put("version", version);
            paaParams.put("signType", signType);
            LogUtil.i("-----merchantId111----->"+merchantId);
            if(!TextUtils.isEmpty(merchantId)){
                merchantId=merchantId.substring(2,merchantId.length()-2);
            }
            LogUtil.i("-----merchantId222----->"+merchantId);
            paaParams.put("merchantId", merchantId);

            LogUtil.i("-----orderStr111----->"+orderStr);
            if(!TextUtils.isEmpty(orderStr)){
                orderStr=orderStr.substring(1,orderStr.length()-1);
            }
            LogUtil.i("-----orderStr222----->"+orderStr);

            paaParams.put("orderNo", orderStr); //订单号
            paaParams.put("orderAmount", amount);//金额
            paaParams.put("orderCurrency", "0");
            paaParams.put("orderDatetime", timeStr);//订单生成时间
            paaParams.put("productName", productName); //商品名称
//			paaParams.put("ext1", ext1FromInput());
            paaParams.put("ext1", ext1);
            paaParams.put("payType", "33");
//			paaParams.put("issuerId", "visa");
//			paaParams.put("tradeNature", "GOODS");
//			paaParams.put("language", "3");
           // paaParams.put("cardNo", "");
            paaParams.put("signMsg", sign);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] paaParamsArray = {
                inputCharset, "inputCharset",
                receiveUrl, "receiveUrl",
                version, "version",
//				"3","language",
                "0", "signType",
                merchantId, "merchantId",
                orderStr, "orderNo",
                amount, "orderAmount",
                "0", "orderCurrency",
                timeStr, "orderDatetime",
                productName, "productName",
//				ext1FromInput(),"ext1",
                ext1, "ext1",
                "33", "payType",
                sign,"signMsg"
//				"visa","issuerId",
//				"GOODS","tradeNature",
            //    key, "key",
        };
//
//        String paaStr = "";
//        for (int i = 0; i < paaParamsArray.length; i++) {
//            paaStr += paaParamsArray[i + 1] + "=" + paaParamsArray[i] + "&";
//            i++;
//        }

//        String md5Str = md5(paaStr.substring(0, paaStr.length() - 1));
//
//        try {
//            paaParams.put("signMsg", md5Str);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        return paaParams;
    }

    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(
                    string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        return hexString(hash);
    }


    public static final String hexString(byte[] bytes) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            buffer.append(hexString(bytes[i]));
        }
        return buffer.toString();
    }

    public static final String hexString(byte byte0) {
        char ac[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char ac1[] = new char[2];
        ac1[0] = ac[byte0 >>> 4 & 0xf];
        ac1[1] = ac[byte0 & 0xf];
        String s = new String(ac1);
        return s;
    }

}
