package com.cjwsc.idcm.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 描述:图片的实体类
 * author: Zhujun
 * Date: 2016/6/29 15:32
 */
public class PictureInfo implements Parcelable {
	private String path;
	private String time;
	private int section;
	public  boolean isChoiced=false;//是否被选中
	public boolean isSdkImage=false;//是否为无人机拍照的图片


	public PictureInfo(String path, String time) {
		super();
		this.path = path;
		this.time = time;
	}

	public PictureInfo() {
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.path);
		dest.writeString(this.time);
		dest.writeInt(this.section);
		dest.writeByte(this.isChoiced ? (byte) 1 : (byte) 0);
		dest.writeByte(this.isSdkImage ? (byte) 1 : (byte) 0);
	}

	protected PictureInfo(Parcel in) {
		this.path = in.readString();
		this.time = in.readString();
		this.section = in.readInt();
		this.isChoiced = in.readByte() != 0;
		this.isSdkImage = in.readByte() != 0;
	}

	public static final Creator<PictureInfo> CREATOR = new Creator<PictureInfo>() {
		@Override
		public PictureInfo createFromParcel(Parcel source) {
			return new PictureInfo(source);
		}

		@Override
		public PictureInfo[] newArray(int size) {
			return new PictureInfo[size];
		}
	};
}
