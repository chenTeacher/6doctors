package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ChenTeacher on 2018/5/27.
 */

public class Doctor implements Parcelable {
    /**医生的id*/
    int  doctorId;
    /**医生的名字*/
    String doctorName;
    /**医生的医院*/
    String hospitalName;
    /**医生的科室*/
    String departName;

    public Doctor() {
    }

    public Doctor(int doctorId, String doctorName, String hospitalName, String departName) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
        this.departName = departName;
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

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorName='" + doctorName + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", departName='" + departName + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.doctorId);
        dest.writeString(this.doctorName);
        dest.writeString(this.hospitalName);
        dest.writeString(this.departName);
    }

    protected Doctor(Parcel in) {
        this.doctorId = in.readInt();
        this.doctorName = in.readString();
        this.hospitalName = in.readString();
        this.departName = in.readString();
    }

    public static final Parcelable.Creator<Doctor> CREATOR = new Parcelable.Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel source) {
            return new Doctor(source);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };
}
