package com.cjwsc.idcm.model.bean;

import java.io.Serializable;

/**
 * Created by yuzhongrong on 2017/10/20.
 */

public class upgradeBean implements Serializable {


    /**
     * code : 0
     * data : {"info":{"download_url":"","info":"","is_force":"1","version":""},"is_upgrade":false}
     * status : 200
     * message :
     */

    private int code;
    private DataBean data;
    private int status;
    private String message;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean implements Serializable {
        /**
         * info : {"download_url":"","info":"","is_force":"1","version":""}
         * is_upgrade : false
         */

        private InfoBean info;
        private boolean is_upgrade;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public boolean isIs_upgrade() {
            return is_upgrade;
        }

        public void setIs_upgrade(boolean is_upgrade) {
            this.is_upgrade = is_upgrade;
        }

        public static class InfoBean implements Serializable {
            /**
             * download_url :
             * info :
             * is_force : 1
             * version :
             */

            private String download_url;
            private String info;
            private int is_force;
            private String version;

            public int getWin_times() {
                return win_times;
            }

            public void setWin_times(int win_times) {
                this.win_times = win_times;
            }

            private int win_times;

            public String getDownload_url() {
                return download_url;
            }

            public void setDownload_url(String download_url) {
                this.download_url = download_url;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public int getIs_force() {
                return is_force;
            }

            public void setIs_force(int is_force) {
                this.is_force = is_force;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

        }
    }


}
