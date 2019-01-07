package com.cjwsc.idcm.model.bean.signalr;

import java.util.List;

/**
 * Created by ${zipp} on 2017/12/21.
 * 功能描述：
 */

public class HandicapStatisticsBean {

        /**
         * BuyHandicapList : [{"Amount":0,"OrderType":0,"Price":0,"Quantity":0,"TradeDirection":1}]
         * BuyerCoinID : string
         * BuyerCoinName : string
         * SellHandicapList : [{"Amount":0,"OrderType":0,"Price":0,"Quantity":0,"TradeDirection":1}]
         * SellerCoinID : string
         * SellerCoinName : string
         * Symbol : string
         * TradingConfigID : string
         * TradingConfigType : 0
         */

        private String BuyerCoinID;
        private String BuyerCoinName;
        private String SellerCoinID;
        private String SellerCoinName;
        private String Symbol;
        private String TradingConfigID;
        private double TradingConfigType;
        private List<BuyHandicapListBean> BuyHandicapList;
        private List<SellHandicapListBean> SellHandicapList;

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

        public double getTradingConfigType() {
            return TradingConfigType;
        }

        public void setTradingConfigType(int TradingConfigType) {
            this.TradingConfigType = TradingConfigType;
        }

        public List<BuyHandicapListBean> getBuyHandicapList() {
            return BuyHandicapList;
        }

        public void setBuyHandicapList(List<BuyHandicapListBean> BuyHandicapList) {
            this.BuyHandicapList = BuyHandicapList;
        }

        public List<SellHandicapListBean> getSellHandicapList() {
            return SellHandicapList;
        }

        public void setSellHandicapList(List<SellHandicapListBean> SellHandicapList) {
            this.SellHandicapList = SellHandicapList;
        }

        public static class BuyHandicapListBean {
            /**
             * Amount : 0
             * OrderType : 0
             * Price : 0
             * Quantity : 0
             * TradeDirection : 1
             */

            private double Amount;
            private double OrderType;
            private double Price;
            private double Quantity;
            private double TradeDirection;
            private String Timestamp;
            private double TotalCurrentAmount;
            private boolean isUserData;     //盘口数据是否自己的

            public boolean isUserData() {
                return isUserData;
            }

            public void setUserData(boolean userData) {
                isUserData = userData;
            }

            public double getTotalCurrentAmount() {
                return TotalCurrentAmount;
            }

            public void setTotalCurrentAmount(double totalCurrentAmount) {
                TotalCurrentAmount = totalCurrentAmount;
            }

            public String getTimestamp() {
                return Timestamp;
            }

            public void setTimestamp(String timestamp) {
                Timestamp = timestamp;
            }

            public double getAmount() {
                return Amount;
            }

            public void setAmount(int Amount) {
                this.Amount = Amount;
            }

            public double getOrderType() {
                return OrderType;
            }

            public void setOrderType(int OrderType) {
                this.OrderType = OrderType;
            }

            public double getPrice() {
                return Price;
            }

            public void setPrice(int Price) {
                this.Price = Price;
            }

            public double getQuantity() {
                return Quantity;
            }

            public void setQuantity(int Quantity) {
                this.Quantity = Quantity;
            }

            public double getTradeDirection() {
                return TradeDirection;
            }

            public void setTradeDirection(int TradeDirection) {
                this.TradeDirection = TradeDirection;
            }
        }

        public static class SellHandicapListBean {
            /**
             * Amount : 0
             * OrderType : 0
             * Price : 0
             * Quantity : 0
             * TradeDirection : 1
             */

            private double Amount;
            private double OrderType;
            private double Price;
            private double Quantity;
            private double TradeDirection;

            private String Timestamp;
            private double TotalCurrentAmount;
            private boolean isUserData;     //盘口数据是否自己的

            public boolean isUserData() {
                return isUserData;
            }

            public void setUserData(boolean userData) {
                isUserData = userData;
            }

            public double getTotalCurrentAmount() {
                return TotalCurrentAmount;
            }

            public void setTotalCurrentAmount(double totalCurrentAmount) {
                TotalCurrentAmount = totalCurrentAmount;
            }

            public String getTimestamp() {
                return Timestamp;
            }

            public void setTimestamp(String timestamp) {
                Timestamp = timestamp;
            }

            public double getAmount() {
                return Amount;
            }

            public void setAmount(int Amount) {
                this.Amount = Amount;
            }

            public double getOrderType() {
                return OrderType;
            }

            public void setOrderType(int OrderType) {
                this.OrderType = OrderType;
            }

            public double getPrice() {
                return Price;
            }

            public void setPrice(int Price) {
                this.Price = Price;
            }

            public double getQuantity() {
                return Quantity;
            }

            public void setQuantity(int Quantity) {
                this.Quantity = Quantity;
            }

            public double getTradeDirection() {
                return TradeDirection;
            }

            public void setTradeDirection(int TradeDirection) {
                this.TradeDirection = TradeDirection;
            }
        }

}
