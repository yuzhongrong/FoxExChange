package com.cjwsc.idcm.model.bean.providerbean;


import java.util.List;

/**
 * Created by ${zipp} on 2017/12/20.
 * 功能描述：获取交易配置信息
 */

public class TradeConfigBean {

    /**
     * DefaultBuyerCoinID : M716wsg8Q0eMmw-ksWkyjA
     * DefaultSellerCoinID : 1xx3-k3olkGgIwEiPqwXMw
     * DefaultTradeConfigID : _DSQ3BmslE-cS-HP3POlnA
     * Signalr : http://192.168.1.251:8308
     * TradeVarietyList : [{"AvgPrice":0,"BuyNum":0,"BuyPrice":0,"BuyerCoinCode":"USD","BuyerCoinID":"M716wsg8Q0eMmw-ksWkyjA","BuyerCoinName":"美元","Close":12577,"DisplayPrecision":0,"FastRose":0,"High":525606,"Low":1,"MinBuyerCoinPrecision":0.01,"MinSellerCoinPrecision":0.01,"Newest":12577,"Open":12290,"Rise":287,"Rose":0.023352318958502847,"SellNum":0,"SellPrice":0,"SellerCoinCode":"BTC","SellerCoinID":"1xx3-k3olkGgIwEiPqwXMw","SellerCoinName":"BTC","Symbol":"BTC/USD","TradingConfigID":"_DSQ3BmslE-cS-HP3POlnA","TradingStatus":1,"TradingType":0},{"AvgPrice":0,"BuyNum":0,"BuyPrice":0,"BuyerCoinCode":"USD","BuyerCoinID":"M716wsg8Q0eMmw-ksWkyjA","BuyerCoinName":"美元","Close":2276.5,"DisplayPrecision":0,"FastRose":0,"High":50001,"Low":2050,"MinBuyerCoinPrecision":0.01,"MinSellerCoinPrecision":0.01,"Newest":2276.5,"Open":2276.6,"Rise":-0.1,"Rose":-4.392515154177282E-5,"SellNum":0,"SellPrice":0,"SellerCoinCode":"BCH","SellerCoinID":"8PqFnATs2U6oYPFxJjAooQ","SellerCoinName":"BCH","Symbol":"BCH/USD","TradingConfigID":"_DSQ3BmslE-cS-HP1POlnA","TradingStatus":1,"TradingType":0},{"AvgPrice":0,"BuyNum":0,"BuyPrice":0,"BuyerCoinCode":"BTC","BuyerCoinID":"1xx3-k3olkGgIwEiPqwXMw","BuyerCoinName":"BTC","Close":500,"DisplayPrecision":0,"FastRose":0,"High":282373,"Low":0.16361,"MinBuyerCoinPrecision":1.0E-4,"MinSellerCoinPrecision":1.0E-4,"Newest":500,"Open":100.5,"Rise":399.5,"Rose":3.9751243781094527,"SellNum":0,"SellPrice":0,"SellerCoinCode":"BCH","SellerCoinID":"8PqFnATs2U6oYPFxJjAooQ","SellerCoinName":"BCH","Symbol":"BCH/BTC","TradingConfigID":"_DSQ3BmslE-cS-HP0POlnA","TradingStatus":1,"TradingType":2}]
     */

    private String DefaultBuyerCoinID;
    private String DefaultSellerCoinID;
    private String DefaultTradeConfigID;
    private String Signalr;
    private List<TradeVarietyListBean> TradeVarietyList;

    public String getDefaultBuyerCoinID() {
        return DefaultBuyerCoinID;
    }

    public void setDefaultBuyerCoinID(String DefaultBuyerCoinID) {
        this.DefaultBuyerCoinID = DefaultBuyerCoinID;
    }

    public String getDefaultSellerCoinID() {
        return DefaultSellerCoinID;
    }

    public void setDefaultSellerCoinID(String DefaultSellerCoinID) {
        this.DefaultSellerCoinID = DefaultSellerCoinID;
    }

    public String getDefaultTradeConfigID() {
        return DefaultTradeConfigID;
    }

    public void setDefaultTradeConfigID(String DefaultTradeConfigID) {
        this.DefaultTradeConfigID = DefaultTradeConfigID;
    }

    public String getSignalr() {
        return Signalr;
    }

    public void setSignalr(String Signalr) {
        this.Signalr = Signalr;
    }

    public List<TradeVarietyListBean> getTradeVarietyList() {
        return TradeVarietyList;
    }

    public void setTradeVarietyList(List<TradeVarietyListBean> TradeVarietyList) {
        this.TradeVarietyList = TradeVarietyList;
    }

    public static class TradeVarietyListBean {
        /**
         * AvgPrice : 0.0
         * BuyNum : 0.0
         * BuyPrice : 0.0
         * BuyerCoinCode : USD
         * BuyerCoinID : M716wsg8Q0eMmw-ksWkyjA
         * BuyerCoinName : 美元
         * Close : 12577.0
         * DisplayPrecision : 0.0
         * FastRose : 0.0
         * High : 525606.0
         * Low : 1.0
         * MinBuyerCoinPrecision : 0.01
         * MinSellerCoinPrecision : 0.01
         * Newest : 12577.0
         * Open : 12290.0
         * Rise : 287.0
         * Rose : 0.023352318958502847
         * SellNum : 0.0
         * SellPrice : 0.0
         * SellerCoinCode : BTC
         * SellerCoinID : 1xx3-k3olkGgIwEiPqwXMw
         * SellerCoinName : BTC
         * Symbol : BTC/USD
         * TradingConfigID : _DSQ3BmslE-cS-HP3POlnA
         * TradingStatus : 1
         * TradingType : 0
         */

        private double AvgPrice;
        private double BuyNum;
        private double BuyPrice;
        private String BuyerCoinCode;
        private String BuyerCoinID;
        private String BuyerCoinName;
        private double Close;
        private double DisplayPrecision;
        private double FastRose;
        private double High;
        private double Low;
        private double MinBuyerCoinPrecision;
        private double MinSellerCoinPrecision;
        private double Newest;
        private double Open;
        private double Rise;
        private double Rose;
        private double SellNum;
        private double SellPrice;
        private double Last24TradeQuantity ;
        private String SellerCoinCode;
        private String SellerCoinID;
        private String SellerCoinName;
        private String Symbol;
        private String TradingConfigID;
        private int TradingStatus;
        private int TradingType;

        private boolean isAddOptional;

        public double getLast24TradeQuantity() {
            return Last24TradeQuantity;
        }

        public void setLast24TradeQuantity(double last24TradeQuantity) {
            Last24TradeQuantity = last24TradeQuantity;
        }
        public boolean isAddOptional() {
            return isAddOptional;
        }

        public void setAddOptional(boolean addOptional) {
            isAddOptional = addOptional;
        }

        public double getAvgPrice() {
            return AvgPrice;
        }

        public void setAvgPrice(double AvgPrice) {
            this.AvgPrice = AvgPrice;
        }

        public double getBuyNum() {
            return BuyNum;
        }

        public void setBuyNum(double BuyNum) {
            this.BuyNum = BuyNum;
        }

        public double getBuyPrice() {
            return BuyPrice;
        }

        public void setBuyPrice(double BuyPrice) {
            this.BuyPrice = BuyPrice;
        }

        public String getBuyerCoinCode() {
            return BuyerCoinCode;
        }

        public void setBuyerCoinCode(String BuyerCoinCode) {
            this.BuyerCoinCode = BuyerCoinCode;
        }

        public String getBuyerCoinID() {
            return BuyerCoinID;
        }

        public void setBuyerCoinID(String BuyerCoinID) {
            this.BuyerCoinID = BuyerCoinID;
        }

        public String getBuyerCoinName() {
            return BuyerCoinName;
        }

        public void setBuyerCoinName(String BuyerCoinName) {
            this.BuyerCoinName = BuyerCoinName;
        }

        public double getClose() {
            return Close;
        }

        public void setClose(double Close) {
            this.Close = Close;
        }

        public double getDisplayPrecision() {
            return DisplayPrecision;
        }

        public void setDisplayPrecision(double DisplayPrecision) {
            this.DisplayPrecision = DisplayPrecision;
        }

        public double getFastRose() {
            return FastRose;
        }

        public void setFastRose(double FastRose) {
            this.FastRose = FastRose;
        }

        public double getHigh() {
            return High;
        }

        public void setHigh(double High) {
            this.High = High;
        }

        public double getLow() {
            return Low;
        }

        public void setLow(double Low) {
            this.Low = Low;
        }

        public double getMinBuyerCoinPrecision() {
            return MinBuyerCoinPrecision;
        }

        public void setMinBuyerCoinPrecision(double MinBuyerCoinPrecision) {
            this.MinBuyerCoinPrecision = MinBuyerCoinPrecision;
        }

        public double getMinSellerCoinPrecision() {
            return MinSellerCoinPrecision;
        }

        public void setMinSellerCoinPrecision(double MinSellerCoinPrecision) {
            this.MinSellerCoinPrecision = MinSellerCoinPrecision;
        }

        public double getNewest() {
            return Newest;
        }

        public void setNewest(double Newest) {
            this.Newest = Newest;
        }

        public double getOpen() {
            return Open;
        }

        public void setOpen(double Open) {
            this.Open = Open;
        }

        public double getRise() {
            return Rise;
        }

        public void setRise(double Rise) {
            this.Rise = Rise;
        }

        public double getRose() {
            return Rose;
        }

        public void setRose(double Rose) {
            this.Rose = Rose;
        }

        public double getSellNum() {
            return SellNum;
        }

        public void setSellNum(double SellNum) {
            this.SellNum = SellNum;
        }

        public double getSellPrice() {
            return SellPrice;
        }

        public void setSellPrice(double SellPrice) {
            this.SellPrice = SellPrice;
        }

        public String getSellerCoinCode() {
            return SellerCoinCode;
        }

        public void setSellerCoinCode(String SellerCoinCode) {
            this.SellerCoinCode = SellerCoinCode;
        }

        public String getSellerCoinID() {
            return SellerCoinID;
        }

        public void setSellerCoinID(String SellerCoinID) {
            this.SellerCoinID = SellerCoinID;
        }

        public String getSellerCoinName() {
            return SellerCoinName;
        }

        public void setSellerCoinName(String SellerCoinName) {
            this.SellerCoinName = SellerCoinName;
        }

        public String getSymbol() {
            return Symbol;
        }

        public void setSymbol(String Symbol) {
            this.Symbol = Symbol;
        }

        public String getTradingConfigID() {
            return TradingConfigID;
        }

        public void setTradingConfigID(String TradingConfigID) {
            this.TradingConfigID = TradingConfigID;
        }

        public int getTradingStatus() {
            return TradingStatus;
        }

        public void setTradingStatus(int TradingStatus) {
            this.TradingStatus = TradingStatus;
        }

        public int getTradingType() {
            return TradingType;
        }

        public void setTradingType(int TradingType) {
            this.TradingType = TradingType;
        }

    }
}
