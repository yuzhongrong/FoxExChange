package com.cjwsc.idcm.Utils;


/**
 * Created by hpz on 2017/12/25.
 */

public class ClientConfig {
    private static ClientConfig Ins;

    public static synchronized ClientConfig Instance() {
        if (Ins == null) {
            synchronized (ClientConfig.class) {
                if (Ins == null) {
                    Ins = new ClientConfig();
                }
            }
        }
        return Ins;
    }

    public SharedPreUtils mSharedPre;

    public void Init() {
        try {
            mSharedPre = new SharedPreUtils("UserConfig");
        } catch (Exception ex) {

        }
    }

    private String ticketValue;

    public String getTicketValue() {
        this.ticketValue = mSharedPre.getString("ticketValue",ticketValue);
        return ticketValue;
    }

    public void setTicketValue(String ticketValue) {
        this.ticketValue = ticketValue;
        mSharedPre.putString("ticketValue",ticketValue);
    }

    public String getSaveID() {
        this.saveID = mSharedPre.getString("saveID");
        return saveID;
    }

    public void setSaveID(String saveID) {
        this.saveID = saveID;
        mSharedPre.putString("saveID", saveID);
    }

    private String saveID;

    public String getSaveUserID() {
        this.saveUserID = mSharedPre.getString("saveUserID");
        return saveUserID;
    }

    public void setSaveUserID(String saveUserID) {
        this.saveUserID = saveUserID;
        mSharedPre.putString("saveUserID", saveUserID);
    }

    private String saveUserID;

    public String getPhoneSaveID() {
        this.savePhoneID = mSharedPre.getString("savePhoneID");
        return savePhoneID;
    }

    public void setSavePhoneID(String savePhoneID) {
        this.savePhoneID = savePhoneID;
        mSharedPre.putString("savePhoneID", savePhoneID);
    }

    private String savePhoneID;

    public String getEmailSaveID() {
        this.saveEmailID = mSharedPre.getString("saveEmailID");
        return saveEmailID;
    }

    public void setSaveEmailID(String saveEmailID) {
        this.saveEmailID = saveEmailID;
        mSharedPre.putString("saveEmailID", saveEmailID);
    }

    private String saveEmailID;

    private int position;

    public int getPosition() {
        this.position = mSharedPre.getInt("position");
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
        mSharedPre.putInt("position",position);
    }

    private int pos;

    public int getPos() {
        this.pos = mSharedPre.getInt("pos");
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
        mSharedPre.putInt("pos",pos);
    }

    public String getEmailNameSaveID() {
        this.saveEmailNameID = mSharedPre.getString("saveEmailNameID");
        return saveEmailNameID;
    }

    public void setSaveEmailNameID(String saveEmailNameID) {
        this.saveEmailNameID = saveEmailNameID;
        mSharedPre.putString("saveEmailNameID", saveEmailNameID);
    }

    private String saveEmailNameID;

    public String getPhoneNameSaveID() {
        this.savePhoneNameID = mSharedPre.getString("savePhoneNameID");
        return savePhoneNameID;
    }

    public void setSavePhoneNameID(String savePhoneNameID) {
        this.savePhoneNameID = savePhoneNameID;
        mSharedPre.putString("savePhoneNameID", savePhoneNameID);
    }

    private String savePhoneNameID;

    private String saveCurrency;

    public String getSaveCurrency() {
        this.saveCurrency = mSharedPre.getString("saveCurrency",saveCurrency);
        return saveCurrency;
    }

    public void setSaveCurrency(String saveCurrency) {
        this.saveCurrency = saveCurrency;
        mSharedPre.putString("saveCurrency", saveCurrency);
    }

    private String saveBtc;

    public String getSaveBtc() {
        this.saveBtc = mSharedPre.getString("saveBtc",saveBtc);
        return saveBtc;
    }

    public void setSaveBtc(String saveBtc) {
        this.saveBtc = saveBtc;
        mSharedPre.putString("saveBtc", saveBtc);
    }

    private String saveNum;

    public String getSaveNum() {
        this.saveNum = mSharedPre.getString("saveNum",saveNum);
        return saveNum;
    }

    public void setSaveNum(String saveNum) {
        this.saveNum = saveNum;
        mSharedPre.putString("saveNum", saveNum);
    }

    private String saveName;

    public String getSaveName() {
        this.saveName = mSharedPre.getString("saveName",saveName);
        return saveName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
        mSharedPre.putString("saveName", saveName);
    }

    private int posCreate;

    public int getPosCreate() {
        this.posCreate = mSharedPre.getInt("posCreate");
        return posCreate;
    }

    public void setPosCreate(int posCreate) {
        this.posCreate = posCreate;
        mSharedPre.putInt("posCreate",posCreate);
    }

    private int posForget;

    public int getPosForget() {
        this.posForget = mSharedPre.getInt("posForget");
        return posForget;
    }

    public void setPosForget(int posForget) {
        this.posForget = posForget;
        mSharedPre.putInt("posForget",posForget);
    }

    public Boolean getExecPopup() {
        this.execPopup = mSharedPre.getBoolean("execPopup",execPopup);
        return execPopup;
    }

    public void setExecPopup(Boolean execPopup) {
        this.execPopup = execPopup;
        mSharedPre.putBoolean("execPopup", execPopup);
    }

    private Boolean execPopup;

    private int posMain;

    public int getPosMain() {
        this.posMain = mSharedPre.getInt("posMain");
        return posMain;
    }

    public void setPosMain(int posMain) {
        this.posMain = posMain;
        mSharedPre.putInt("posMain",posMain);
    }


    private Boolean touchPlay = false;

    public Boolean getTouchPlay() {
        this.touchPlay = mSharedPre.getBoolean("touchPlay", touchPlay);
        return touchPlay;
    }

    public void setTouchPlay(Boolean touchPlay) {
        this.touchPlay = touchPlay;
        mSharedPre.putBoolean("touchPlay", touchPlay);
    }

    private Boolean facePlay = true;

    public Boolean getFacePlay() {
        return facePlay;
    }

    public void setFacePlay(Boolean facePlay) {
        this.facePlay = facePlay;
        mSharedPre.putBoolean("facePlay", facePlay);
    }

    private String savePhone;

    public String getSavePhone() {
        this.savePhone = mSharedPre.getString("savePhone",savePhone);
        return savePhone;
    }

    public void setSavePhone(String savePhone) {
        this.savePhone = savePhone;
        mSharedPre.putString("savePhone", savePhone);
    }

    private String savePhonePhone;

    public String getSavePhonePhone() {
        this.savePhonePhone = mSharedPre.getString("savePhonePhone",savePhonePhone);
        return savePhonePhone;
    }

    public void setSavePhonePhone(String savePhonePhone) {
        this.savePhonePhone = savePhonePhone;
        mSharedPre.putString("savePhonePhone", savePhonePhone);
    }

    private String saveEmail;

    public String getSaveEmail() {
        this.saveEmail = mSharedPre.getString("saveEmail",saveEmail);
        return saveEmail;
    }

    public void setSaveEmail(String saveEmail) {
        this.saveEmail = saveEmail;
        mSharedPre.putString("saveEmail", saveEmail);
    }

    private String saveEmailEmail;

    public String getSaveEmailEmail() {
        this.saveEmailEmail = mSharedPre.getString("saveEmailEmail",saveEmailEmail);
        return saveEmailEmail;
    }

    public void setSaveEmailEmail(String saveEmailEmail) {
        this.saveEmailEmail = saveEmailEmail;
        mSharedPre.putString("saveEmailEmail", saveEmailEmail);
    }

    private Boolean select = true;

    public Boolean getSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
        mSharedPre.putBoolean("select", select);
    }

    private int posLocal;

    public int getPosLocal() {
        this.posLocal = mSharedPre.getInt("posLocal");
        return posLocal;
    }

    public void setPosLocal(int posLocal) {
        this.posLocal = posLocal;
        mSharedPre.putInt("posLocal",posLocal);
    }

    private String savePayPass;

    public String getSavePayPass() {
        this.savePayPass = mSharedPre.getString("savePayPass",savePayPass);
        return savePayPass;
    }

    public void setSavePayPass(String savePayPass) {
        this.savePayPass = savePayPass;
        mSharedPre.putString("savePayPass", savePayPass);
    }

    private int posLanguage;

    public int getPosLanguage() {
        this.posLanguage = mSharedPre.getInt("posLanguage");
        return posLanguage;
    }

    public void setPosLanguage(int posLanguage) {
        this.posLanguage = posLanguage;
        mSharedPre.putInt("posLanguage",posLanguage);
    }

    private int ticketId = -1;

    public int getTicketId() {
        this.ticketId = mSharedPre.getInt("ticketId",ticketId);
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
        mSharedPre.putInt("ticketId",ticketId);
    }

    private Boolean touchSelect = true;

    public Boolean getTouchSelect() {
        this.touchSelect = mSharedPre.getBoolean("touchSelect", touchSelect);
        return touchSelect;
    }

    public void setTouchSelect(Boolean touchSelect) {
        this.touchSelect = touchSelect;
        mSharedPre.putBoolean("touchSelect", touchSelect);
    }

    private String saveLocal;

    public String getSaveLocal() {
        this.saveLocal = mSharedPre.getString("saveLocal",saveLocal);
        return saveLocal;
    }

    public void setSaveLocal(String saveLocal) {
        this.saveLocal = saveLocal;
        mSharedPre.putString("saveLocal", saveLocal);
    }

    private String saveUrl;

    public String getSaveUrl() {
        this.saveUrl = mSharedPre.getString("saveUrl",saveUrl);
        return saveUrl;
    }

    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl;
        mSharedPre.putString("saveUrl", saveUrl);
    }
}
