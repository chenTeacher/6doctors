package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenTeacher on 2018/5/30.
 */

public class TherapieItem implements Parcelable {

    /**
     * 就诊时间
     */
    private String date;
    /**
     * 医生Id
     */
    private int doctorId;
    /**
     * 医生姓名
     */
    private String doctorName;
    /**
     * 患者Id
     */
    private int patientId;
    /**
     * 病例Id
     */
    private int therapyId;
    /**
     * 照片集合
     */
    private List<Photo> photos;
    /**
     *记录
     */
    private String record;
    /**
     * 状态
     */
    private String state;

    public TherapieItem() {
    }

    public TherapieItem(String date, int doctorId, int patientId, int therapyId, List<Photo> photos, String record, String state, String doctorName) {
        this.date = date;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.therapyId = therapyId;
        this.photos = photos;
        this.record = record;
        this.state = state;
        this.doctorName = doctorName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getTherapyId() {
        return therapyId;
    }

    public void setTherapyId(int therapyId) {
        this.therapyId = therapyId;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.date);
        dest.writeInt(this.doctorId);
        dest.writeInt(this.patientId);
        dest.writeInt(this.therapyId);
        dest.writeList(this.photos);
        dest.writeString(this.record);
        dest.writeString(this.state);
        dest.writeString(this.doctorName);
    }

    protected TherapieItem(Parcel in) {
        this.date = in.readString();
        this.doctorId = in.readInt();
        this.patientId = in.readInt();
        this.therapyId = in.readInt();
        this.photos = new ArrayList<Photo>();
        in.readList(this.photos, Photo.class.getClassLoader());
        this.record = in.readString();
        this.state = in.readString();
        this.doctorName = in.readString();
    }

    public static final Parcelable.Creator<TherapieItem> CREATOR = new Parcelable.Creator<TherapieItem>() {
        @Override
        public TherapieItem createFromParcel(Parcel source) {
            return new TherapieItem(source);
        }

        @Override
        public TherapieItem[] newArray(int size) {
            return new TherapieItem[size];
        }
    };
}

