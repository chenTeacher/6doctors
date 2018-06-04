package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ChenTeacher on 2018/5/31.
 */

public class Therapy implements Parcelable {
    private String date;
    private int doctorId;
    private String doctorName;
    private int patientId;
    private String patientName;
    private List<Photo> photos;
    private String record;
    private String state;
    private int therapyId;

    public Therapy() {
    }

    public Therapy(String date, int doctorId, String doctorName, int patientId, String patientName, List<Photo> photos, String record, String state, int therapyId) {
        this.date = date;
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.patientId = patientId;
        this.patientName = patientName;
        this.photos = photos;
        this.record = record;
        this.state = state;
        this.therapyId = therapyId;
    }

    @Override
    public String toString() {
        return "Therapy{" +
                "date='" + date + '\'' +
                ", doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", photos=" + photos +
                ", record='" + record + '\'' +
                ", state='" + state + '\'' +
                ", therapyId=" + therapyId +
                '}';
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

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
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
        dest.writeString(this.date);
        dest.writeInt(this.doctorId);
        dest.writeString(this.doctorName);
        dest.writeInt(this.patientId);
        dest.writeString(this.patientName);
        dest.writeTypedList(this.photos);
        dest.writeString(this.record);
        dest.writeString(this.state);
        dest.writeInt(this.therapyId);
    }

    protected Therapy(Parcel in) {
        this.date = in.readString();
        this.doctorId = in.readInt();
        this.doctorName = in.readString();
        this.patientId = in.readInt();
        this.patientName = in.readString();
        this.photos = in.createTypedArrayList(Photo.CREATOR);
        this.record = in.readString();
        this.state = in.readString();
        this.therapyId = in.readInt();
    }

    public static final Parcelable.Creator<Therapy> CREATOR = new Parcelable.Creator<Therapy>() {
        @Override
        public Therapy createFromParcel(Parcel source) {
            return new Therapy(source);
        }

        @Override
        public Therapy[] newArray(int size) {
            return new Therapy[size];
        }
    };
}
