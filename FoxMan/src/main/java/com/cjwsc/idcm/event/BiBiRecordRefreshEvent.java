package com.cjwsc.idcm.event;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 * @author yiyang
 */
public class BiBiRecordRefreshEvent implements Parcelable{
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public BiBiRecordRefreshEvent() {
    }

    protected BiBiRecordRefreshEvent(Parcel in) {
    }

    public static final Creator<BiBiRecordRefreshEvent> CREATOR = new Creator<BiBiRecordRefreshEvent>() {
        @Override
        public BiBiRecordRefreshEvent createFromParcel(Parcel source) {
            return new BiBiRecordRefreshEvent(source);
        }

        @Override
        public BiBiRecordRefreshEvent[] newArray(int size) {
            return new BiBiRecordRefreshEvent[size];
        }
    };
}
