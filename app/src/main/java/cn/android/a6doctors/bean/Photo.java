package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ChenTeacher on 2018/5/30.
 */

public class Photo implements Parcelable {
    /**
     * 照片Id
     */
    private int  caseId;
    /**
     * 照片地址
     */
    private String casePath;
    /**
     * 病例Id
     */
    private int therapyId;

    public Photo() {
    }

    public Photo(int caseId, String casePath, int therapyId) {
        this.caseId = caseId;
        this.casePath = casePath;
        this.therapyId = therapyId;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCasePath() {
        return casePath;
    }

    public void setCasePath(String casePath) {
        this.casePath = casePath;
    }

    public int getTherapyId() {
        return therapyId;
    }

    public void setTherapyId(int therapyId) {
        this.therapyId = therapyId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.caseId);
        dest.writeString(this.casePath);
        dest.writeInt(this.therapyId);
    }

    protected Photo(Parcel in) {
        this.caseId = in.readInt();
        this.casePath = in.readString();
        this.therapyId = in.readInt();
    }

    public static final Parcelable.Creator<Photo> CREATOR = new Parcelable.Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel source) {
            return new Photo(source);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };
}
