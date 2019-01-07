package com.cjwsc.idcm.signarl;

/**
 * Created by hpz on 17/9/20.
 */

public class WorkerInfo {

    private String MiningPool;
    private String WorkerGroup;
    private String WorkerGroupID;
    private String WorkerID;
    private String CoinType;
    private Boolean UserFlag;
    private int ActionFlag;
    private boolean isSelect;

    public String getMiningPool() {
        return MiningPool;
    }

    public void setMiningPool(String miningPool) {
        MiningPool = miningPool;
    }

    public String getWorkerGroup() {
        return WorkerGroup;
    }

    public void setWorkerGroup(String workerGroup) {
        WorkerGroup = workerGroup;
    }

    public String getWorkerGroupID() {
        return WorkerGroupID;
    }

    public void setWorkerGroupID(String workerGroupID) {
        WorkerGroupID = workerGroupID;
    }

    public String getWorkerID() {
        return WorkerID;
    }

    public void setWorkerID(String workerID) {
        WorkerID = workerID;
    }

    public String getCoinType() {
        return CoinType;
    }

    public void setCoinType(String coinType) {
        CoinType = coinType;
    }

    public Boolean getUserFlag() {
        return UserFlag;
    }

    public void setUserFlag(Boolean userFlag) {
        UserFlag = userFlag;
    }

    public int getActionFlag() {
        return ActionFlag;
    }

    public void setActionFlag(int actionFlag) {
        ActionFlag = actionFlag;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
