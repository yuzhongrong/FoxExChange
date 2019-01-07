package com.cjwsc.idcm.model.bean;


import com.cjwsc.idcm.widget.quickSelect.bean.BaseIndexPinyinBean;

import java.util.List;

/**
 * Created by ${zipp} on 2017/12/5.
 * 功能描述：获取注册时国家代码
 */

public class CountryCodeBean {


    private List<DataBeanX> Data;

    public List<DataBeanX> getData() {
        return Data;
    }

    public void setData(List<DataBeanX> Data) {
        this.Data = Data;
    }

    public static class DataBeanX {
        /**
         * Data : [{"Areacode":"+86","Code":"CN","CreateTime":"2016-06-01T00:00:00","ID":"2a233f8804432143","LanguageType":"zh-CN","LastUpdateTime":"2016-06-01T13:58:50","LastUpdateUserID":"FFFFFFFFFFFFFFFF","Name":"中国","SortNumber":1},{"Areacode":"+852","CreateTime":"2016-10-12T10:13:33","CreatorID":"FFFFFFFFFFFFFFFF","ID":"6ea8d3eae8be0bda","LanguageType":"zh-CN","LastUpdateTime":"2016-10-12T10:13:33","LastUpdateUserID":"FFFFFFFFFFFFFFFF","Name":"香港","SortNumber":2},{"Areacode":"+853","CreateTime":"2016-10-12T10:16:54","CreatorID":"FFFFFFFFFFFFFFFF","ID":"3f4c965eeddd2df2","LanguageType":"zh-CN","LastUpdateTime":"2016-10-12T10:16:54","LastUpdateUserID":"FFFFFFFFFFFFFFFF","Name":"澳门","SortNumber":3},{"Areacode":"+886","Code":"T","CreateTime":"2016-10-12T11:26:26","CreatorID":"FFFFFFFFFFFFFFFF","ID":"68d566d710ccb04e","LanguageType":"zh-CN","LastUpdateTime":"2016-10-12T11:26:26","LastUpdateUserID":"FFFFFFFFFFFFFFFF","Name":"台湾","SortNumber":3},{"Areacode":"+81","Code":"JP","CreateTime":"2016-10-12T11:07:48","CreatorID":"FFFFFFFFFFFFFFFF","ID":"529198900747ccc6","LanguageType":"zh-CN","LastUpdateTime":"2016-10-12T11:07:48","LastUpdateUserID":"FFFFFFFFFFFFFFFF","Name":"日本","SortNumber":4},{"Areacode":"+82","Code":"H","CreateTime":"2016-10-12T11:33:45","CreatorID":"FFFFFFFFFFFFFFFF","ID":"450d68a889b6b48f","LanguageType":"zh-CN","LastUpdateTime":"2016-10-12T11:33:45","LastUpdateUserID":"FFFFFFFFFFFFFFFF","Name":"韩国","SortNumber":5},{"Areacode":"+1","Code":"USA","CreateTime":"2016-06-01T00:00:00","ID":"2a233f8804332143","LanguageType":"zh-CN","LastUpdateTime":"2016-06-01T13:58:57","LastUpdateUserID":"FFFFFFFFFFFFFFFF","Name":"美国","SortNumber":6},{"Areacode":"+49","Code":"D","CreateTime":"2016-10-12T11:13:15","CreatorID":"FFFFFFFFFFFFFFFF","ID":"5419c8742b403652","LanguageType":"zh-CN","LastUpdateTime":"2016-10-12T11:13:15","LastUpdateUserID":"FFFFFFFFFFFFFFFF","Name":"德国","SortNumber":7}]
         * Key : 常用
         */

        private String Key;
        private List<DataBean> Data;

        public String getKey() {
            return Key;
        }

        public void setKey(String Key) {
            this.Key = Key;
        }

        public List<DataBean> getData() {
            return Data;
        }

        public void setData(List<DataBean> Data) {
            this.Data = Data;
        }

        public static class DataBean extends BaseIndexPinyinBean {
            /**
             * Areacode : +86
             * Code : CN
             * CreateTime : 2016-06-01T00:00:00
             * ID : 2a233f8804432143
             * LanguageType : zh-CN
             * LastUpdateTime : 2016-06-01T13:58:50
             * LastUpdateUserID : FFFFFFFFFFFFFFFF
             * Name : 中国
             * SortNumber : 1
             * CreatorID : FFFFFFFFFFFFFFFF
             */

            private String Areacode;
            private String Code;
            private String CreateTime;
            private String ID;
            private String LanguageType;
            private String LastUpdateTime;
            private String LastUpdateUserID;
            private String Name;
            private int SortNumber;
            private String CreatorID;

            public boolean isTop() {
                return isTop;
            }

            public void setTop(boolean top) {
                isTop = top;
            }

            private boolean isTop;//是否是最上面的 不需要被转化成拼音的

            public String getAreacode() {
                return Areacode;
            }

            public void setAreacode(String Areacode) {
                this.Areacode = Areacode;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }

            public String getCreateTime() {
                return CreateTime;
            }

            public void setCreateTime(String CreateTime) {
                this.CreateTime = CreateTime;
            }

            public String getID() {
                return ID;
            }

            public void setID(String ID) {
                this.ID = ID;
            }

            public String getLanguageType() {
                return LanguageType;
            }

            public void setLanguageType(String LanguageType) {
                this.LanguageType = LanguageType;
            }

            public String getLastUpdateTime() {
                return LastUpdateTime;
            }

            public void setLastUpdateTime(String LastUpdateTime) {
                this.LastUpdateTime = LastUpdateTime;
            }

            public String getLastUpdateUserID() {
                return LastUpdateUserID;
            }

            public void setLastUpdateUserID(String LastUpdateUserID) {
                this.LastUpdateUserID = LastUpdateUserID;
            }

            public String getName() {
                return Name;
            }

            public void setName(String Name) {
                this.Name = Name;
            }

            public int getSortNumber() {
                return SortNumber;
            }

            public void setSortNumber(int SortNumber) {
                this.SortNumber = SortNumber;
            }

            public String getCreatorID() {
                return CreatorID;
            }

            public void setCreatorID(String CreatorID) {
                this.CreatorID = CreatorID;
            }

            @Override
            public String getTarget() {
                return Name;
            }
            @Override
            public boolean isNeedToPinyin() {
                return !isTop;
            }


            @Override
            public boolean isShowSuspension() {
                return true;
            }
        }

    }
}
