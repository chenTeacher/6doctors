package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ChenTeacher on 2017/12/1.
 */

public class Patient_Case_Collection implements Parcelable {
    /*病例id*/
    private String caseId;
    /*就诊时间*/
    private String time;
    /*就诊医生*/
    private String doctor;
    /*病例描述*/
    private String describe;

    public Patient_Case_Collection() {
    }

    public Patient_Case_Collection(String caseId, String time, String doctor, String describe) {
        this.caseId = caseId;
        this.time = time;
        this.doctor = doctor;
        this.describe = describe;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Patient_Case_Collection{" +
                "caseId='" + caseId + '\'' +
                ", time='" + time + '\'' +
                ", doctor='" + doctor + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.caseId);
        dest.writeString(this.time);
        dest.writeString(this.doctor);
        dest.writeString(this.describe);
    }

    protected Patient_Case_Collection(Parcel in) {
        this.caseId = in.readString();
        this.time = in.readString();
        this.doctor = in.readString();
        this.describe = in.readString();
    }

    public static final Parcelable.Creator<Patient_Case_Collection> CREATOR = new Parcelable.Creator<Patient_Case_Collection>() {
        @Override
        public Patient_Case_Collection createFromParcel(Parcel source) {
            return new Patient_Case_Collection(source);
        }

        @Override
        public Patient_Case_Collection[] newArray(int size) {
            return new Patient_Case_Collection[size];
        }
    };
}
