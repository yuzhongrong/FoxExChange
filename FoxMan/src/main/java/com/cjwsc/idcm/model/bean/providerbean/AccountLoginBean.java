package com.cjwsc.idcm.model.bean.providerbean;

import java.io.Serializable;

/**
 * Created by ${zipp} on 2017/12/5.
 * 功能描述：
 */

public class AccountLoginBean implements Serializable{
        /**
         * Account : string
         * CountryCode : string
         * AreaCode : string
         * Password : string
         * HeadPortrait : string
         * Email : string
         * Phone : string
         * Tpassword : string
         * LoginTime : 2017-12-26T01:29:14.317Z
         * RealName : string
         * Card : string
         * Sex : string
         * Image1 : string
         * Image2 : string
         * Image3 : string
         * VerifyResult : 0
         * IsFrozen : true
         * IsGoogleTFA : true
         * LogoutTime : 2017-12-26T01:29:14.317Z
         * IsOnline : true
         * VerifyTime : 2017-12-26T01:29:14.317Z
         * VerifyType : 1
         * ValidateLogon : 0
         * ModifySettingsValidation : 0
         * PresentVerification : 0
         * TransactionVerification : 0
         * CreatorID : string
         * CreateTime : 2017-12-26T01:29:14.317Z
         * LastUpdateUserID : string
         * LastUpdateTime : 2017-12-26T01:29:14.317Z
         * IP : string
         * Token : string
         * NeedToSetSecurifyPwd : true
         * VerifyDeviceType : 1
         * PasswordLevel : 0
         * SecurityVerifyType : 100
         * IsRemoteLogin : true
         * ID : string
         */

        private String Account;
        private String CountryCode;
        private String AreaCode;
        private String Password;
        private String HeadPortrait;
        private String Email;
        private String Phone;
        private String Tpassword;
        private String LoginTime;
        private String RealName;
        private String Card;
        private String Sex;
        private String Image1;
        private String Image2;
        private String Image3;
        private int VerifyResult;
        private boolean IsFrozen;
        private boolean IsGoogleTFA;
        private String LogoutTime;
        private boolean IsOnline;
        private String VerifyTime;
        private int VerifyType;
        private int ValidateLogon;
        private int ModifySettingsValidation;
        private int PresentVerification;
        private int TransactionVerification;
        private String CreatorID;
        private String CreateTime;
        private String LastUpdateUserID;
        private String LastUpdateTime;
        private String IP;
        private String Token;
        private boolean NeedToSetSecurifyPwd;
        private int VerifyDeviceType;
        private int PasswordLevel;
        private int SecurityVerifyType;
        private boolean IsRemoteLogin;
        private String ID;
        private String fingerprint;
        private String DefaultVarietyCode;
        private String DefaultVarietyID;

    public String getDefaultVarietyID() {
        return DefaultVarietyID;
    }

    public void setDefaultVarietyID(String defaultVarietyID) {
        DefaultVarietyID = defaultVarietyID;
    }

    public String getDefaultVarietyCode() {
        return DefaultVarietyCode;
    }

    public void setDefaultVarietyCode(String defaultVarietyCode) {
        DefaultVarietyCode = defaultVarietyCode;
    }


        public String getFingerprint() {
            return fingerprint;
        }

        public void setFingerprint(String fingerprint) {
            this.fingerprint = fingerprint;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public String getCountryCode() {
            return CountryCode;
        }

        public void setCountryCode(String CountryCode) {
            this.CountryCode = CountryCode;
        }

        public String getAreaCode() {
            return AreaCode;
        }

        public void setAreaCode(String AreaCode) {
            this.AreaCode = AreaCode;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getHeadPortrait() {
            return HeadPortrait;
        }

        public void setHeadPortrait(String HeadPortrait) {
            this.HeadPortrait = HeadPortrait;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getPhone() {
            return Phone;
        }

        public void setPhone(String Phone) {
            this.Phone = Phone;
        }

        public String getTpassword() {
            return Tpassword;
        }

        public void setTpassword(String Tpassword) {
            this.Tpassword = Tpassword;
        }

        public String getLoginTime() {
            return LoginTime;
        }

        public void setLoginTime(String LoginTime) {
            this.LoginTime = LoginTime;
        }

        public String getRealName() {
            return RealName;
        }

        public void setRealName(String RealName) {
            this.RealName = RealName;
        }

        public String getCard() {
            return Card;
        }

        public void setCard(String Card) {
            this.Card = Card;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public String getImage1() {
            return Image1;
        }

        public void setImage1(String Image1) {
            this.Image1 = Image1;
        }

        public String getImage2() {
            return Image2;
        }

        public void setImage2(String Image2) {
            this.Image2 = Image2;
        }

        public String getImage3() {
            return Image3;
        }

        public void setImage3(String Image3) {
            this.Image3 = Image3;
        }

        public int getVerifyResult() {
            return VerifyResult;
        }

        public void setVerifyResult(int VerifyResult) {
            this.VerifyResult = VerifyResult;
        }

        public boolean isIsFrozen() {
            return IsFrozen;
        }

        public void setIsFrozen(boolean IsFrozen) {
            this.IsFrozen = IsFrozen;
        }

        public boolean isIsGoogleTFA() {
            return IsGoogleTFA;
        }

        public void setIsGoogleTFA(boolean IsGoogleTFA) {
            this.IsGoogleTFA = IsGoogleTFA;
        }

        public String getLogoutTime() {
            return LogoutTime;
        }

        public void setLogoutTime(String LogoutTime) {
            this.LogoutTime = LogoutTime;
        }

        public boolean isIsOnline() {
            return IsOnline;
        }

        public void setIsOnline(boolean IsOnline) {
            this.IsOnline = IsOnline;
        }

        public String getVerifyTime() {
            return VerifyTime;
        }

        public void setVerifyTime(String VerifyTime) {
            this.VerifyTime = VerifyTime;
        }

        public int getVerifyType() {
            return VerifyType;
        }

        public void setVerifyType(int VerifyType) {
            this.VerifyType = VerifyType;
        }

        public int getValidateLogon() {
            return ValidateLogon;
        }

        public void setValidateLogon(int ValidateLogon) {
            this.ValidateLogon = ValidateLogon;
        }

        public int getModifySettingsValidation() {
            return ModifySettingsValidation;
        }

        public void setModifySettingsValidation(int ModifySettingsValidation) {
            this.ModifySettingsValidation = ModifySettingsValidation;
        }

        public int getPresentVerification() {
            return PresentVerification;
        }

        public void setPresentVerification(int PresentVerification) {
            this.PresentVerification = PresentVerification;
        }

        public int getTransactionVerification() {
            return TransactionVerification;
        }

        public void setTransactionVerification(int TransactionVerification) {
            this.TransactionVerification = TransactionVerification;
        }

        public String getCreatorID() {
            return CreatorID;
        }

        public void setCreatorID(String CreatorID) {
            this.CreatorID = CreatorID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getLastUpdateUserID() {
            return LastUpdateUserID;
        }

        public void setLastUpdateUserID(String LastUpdateUserID) {
            this.LastUpdateUserID = LastUpdateUserID;
        }

        public String getLastUpdateTime() {
            return LastUpdateTime;
        }

        public void setLastUpdateTime(String LastUpdateTime) {
            this.LastUpdateTime = LastUpdateTime;
        }

        public String getIP() {
            return IP;
        }

        public void setIP(String IP) {
            this.IP = IP;
        }

        public String getToken() {
            return Token;
        }

        public void setToken(String Token) {
            this.Token = Token;
        }

        public boolean isNeedToSetSecurifyPwd() {
            return NeedToSetSecurifyPwd;
        }

        public void setNeedToSetSecurifyPwd(boolean NeedToSetSecurifyPwd) {
            this.NeedToSetSecurifyPwd = NeedToSetSecurifyPwd;
        }

        public int getVerifyDeviceType() {
            return VerifyDeviceType;
        }

        public void setVerifyDeviceType(int VerifyDeviceType) {
            this.VerifyDeviceType = VerifyDeviceType;
        }

        public int getPasswordLevel() {
            return PasswordLevel;
        }

        public void setPasswordLevel(int PasswordLevel) {
            this.PasswordLevel = PasswordLevel;
        }

        public int getSecurityVerifyType() {
            return SecurityVerifyType;
        }

        public void setSecurityVerifyType(int SecurityVerifyType) {
            this.SecurityVerifyType = SecurityVerifyType;
        }

        public boolean isIsRemoteLogin() {
            return IsRemoteLogin;
        }

        public void setIsRemoteLogin(boolean IsRemoteLogin) {
            this.IsRemoteLogin = IsRemoteLogin;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

}
