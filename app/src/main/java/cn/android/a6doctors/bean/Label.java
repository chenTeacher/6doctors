package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ChenTeacher on 2018/7/7.
 */

public class Label implements Parcelable {
  private int labelId;//标签id
  private String  name;//标签名称
  private int doctorId;//医生id

    public Label() {
    }

    public Label(int labelId, String name, int doctorId) {
        this.labelId = labelId;
        this.name = name;
        this.doctorId = doctorId;
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

    @Override
    public String toString() {
        return "Label{" +
                "labelId=" + labelId +
                ", name='" + name + '\'' +
                ", doctorId=" + doctorId +
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
    }

    protected Label(Parcel in) {
        this.labelId = in.readInt();
        this.name = in.readString();
        this.doctorId = in.readInt();
    }

    public static final Parcelable.Creator<Label> CREATOR = new Parcelable.Creator<Label>() {
        @Override
        public Label createFromParcel(Parcel source) {
            return new Label(source);
        }

        @Override
        public Label[] newArray(int size) {
            return new Label[size];
        }
    };
}
