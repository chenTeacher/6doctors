package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

class Labels implements Parcelable {
    private  int labelId;
    private String name;
    private int doctorId;
    private int labelPatientId;

    public Labels() {
    }

    public Labels(int labelId, String name, int doctorId, int labelPatientId) {
        this.labelId = labelId;
        this.name = name;
        this.doctorId = doctorId;
        this.labelPatientId = labelPatientId;
    }

    public int getLabelId() {
        return labelId;
    }

    public void setLabelId(int labelId) {
        this.labelId = labelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getLabelPatientId() {
        return labelPatientId;
    }

    public void setLabelPatientId(int labelPatientId) {
        this.labelPatientId = labelPatientId;
    }

    @Override
    public String toString() {
        return "Labels{" +
                "labelId=" + labelId +
                ", name='" + name + '\'' +
                ", doctorId=" + doctorId +
                ", labelPatientId=" + labelPatientId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.labelId);
        dest.writeString(this.name);
        dest.writeInt(this.doctorId);
        dest.writeInt(this.labelPatientId);
    }

    protected Labels(Parcel in) {
        this.labelId = in.readInt();
        this.name = in.readString();
        this.doctorId = in.readInt();
        this.labelPatientId = in.readInt();
    }

    public static final Parcelable.Creator<Labels> CREATOR = new Parcelable.Creator<Labels>() {
        @Override
        public Labels createFromParcel(Parcel source) {
            return new Labels(source);
        }

        @Override
        public Labels[] newArray(int size) {
            return new Labels[size];
        }
    };
}
