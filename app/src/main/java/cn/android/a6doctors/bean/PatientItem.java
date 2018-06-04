package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ChenTeacher on 2018/5/29.
 */
//{"doctorPatientId":1,"doctorId":2,"patientId":2,"doctorName":"赵医生","patientName":"赵病人","status":"update"}
public class PatientItem implements Parcelable {
//    {"patientId":7,"doctorName":"赵医生","patientName":"联","status":"update","patientIntention":null,"patientAvatar":"http://api.6doctors.cn/avatar/1527962749060.png"
       private int patientId;
       private String doctorName;
       private String patientName;
       private String status;
       private String  patientIntention;
       private String  patientAvatar;

    public PatientItem() {
    }

    public PatientItem(int patientId, String doctorName, String patientName, String status, String patientIntention, String patientAvatar) {
        this.patientId = patientId;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.status = status;
        this.patientIntention = patientIntention;
        this.patientAvatar = patientAvatar;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPatientIntention() {
        return patientIntention;
    }

    public void setPatientIntention(String patientIntention) {
        this.patientIntention = patientIntention;
    }

    public String getPatientAvatar() {
        return patientAvatar;
    }

    public void setPatientAvatar(String patientAvatar) {
        this.patientAvatar = patientAvatar;
    }

    @Override
    public String toString() {
        return "PatientItem{" +
                "patientId=" + patientId +
                ", doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", status='" + status + '\'' +
                ", patientIntention='" + patientIntention + '\'' +
                ", patientAvatar='" + patientAvatar + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.patientId);
        dest.writeString(this.doctorName);
        dest.writeString(this.patientName);
        dest.writeString(this.status);
        dest.writeString(this.patientIntention);
        dest.writeString(this.patientAvatar);
    }

    protected PatientItem(Parcel in) {
        this.patientId = in.readInt();
        this.doctorName = in.readString();
        this.patientName = in.readString();
        this.status = in.readString();
        this.patientIntention = in.readString();
        this.patientAvatar = in.readString();
    }

    public static final Parcelable.Creator<PatientItem> CREATOR = new Parcelable.Creator<PatientItem>() {
        @Override
        public PatientItem createFromParcel(Parcel source) {
            return new PatientItem(source);
        }

        @Override
        public PatientItem[] newArray(int size) {
            return new PatientItem[size];
        }
    };
}
