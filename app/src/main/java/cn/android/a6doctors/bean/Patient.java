package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ChenTeacher on 2017/12/1.
 */

public class Patient implements Parcelable {
    /**患者Id*/
    private String patient_id;
    /**姓名*/
    private String patient_name;
    /**病症*/
    private String patient_disease;
    /**就诊状态*/
    private String patient_state;
    /**就诊意愿*/
    private String patient_intention;
    /**头像*/
    private String patient_portrait;

    public Patient() {

    }

    public Patient(String patient_id, String patient_name, String patient_disease, String patient_state, String patient_intention, String patient_portrait) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        this.patient_disease = patient_disease;
        this.patient_state = patient_state;
        this.patient_intention = patient_intention;
        this.patient_portrait = patient_portrait;
    }

    public String getPatient_id() {

        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_disease() {
        return patient_disease;
    }

    public void setPatient_disease(String patient_disease) {
        this.patient_disease = patient_disease;
    }

    public String getPatient_state() {
        return patient_state;
    }

    public void setPatient_state(String patient_state) {
        this.patient_state = patient_state;
    }

    public String getPatient_intention() {
        return patient_intention;
    }

    public void setPatient_intention(String patient_intention) {
        this.patient_intention = patient_intention;
    }

    public String getPatient_portrait() {
        return patient_portrait;
    }

    public void setPatient_portrait(String patient_portrait) {
        this.patient_portrait = patient_portrait;
    }
    @Override
    public String toString() {
        return "Patient{" +
                "patient_id='" + patient_id + '\'' +
                ", patient_name='" + patient_name + '\'' +
                ", patient_disease='" + patient_disease + '\'' +
                ", patient_state='" + patient_state + '\'' +
                ", patient_intention='" + patient_intention + '\'' +
                ", patient_portrait='" + patient_portrait + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.patient_id);
        dest.writeString(this.patient_name);
        dest.writeString(this.patient_disease);
        dest.writeString(this.patient_state);
        dest.writeString(this.patient_intention);
        dest.writeString(this.patient_portrait);
    }

    protected Patient(Parcel in) {
        this.patient_id = in.readString();
        this.patient_name = in.readString();
        this.patient_disease = in.readString();
        this.patient_state = in.readString();
        this.patient_intention = in.readString();
        this.patient_portrait = in.readString();
    }

    public static final Parcelable.Creator<Patient> CREATOR = new Parcelable.Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel source) {
            return new Patient(source);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    public interface CallBack{
        /**
         * 数据请求成功
         */
        public abstract void onSuccess(List data);
        /**
         *  使用网络API接口请求方式时，虽然已经请求成功但是由
         *  于{@code msg}的原因无法正常返回数据。
         */
        public abstract void onFailure(String msg);
        /**
         * 请求数据失败，指在请求网络API接口请求方式时，出现无法联网、
         * 缺少权限，内存泄露等原因导致无法连接到请求数据源。
         */
        public abstract void onError();
        /**
         * 当请求数据结束时，无论请求结果是成功，失败或是抛出异常都会执行此方法给用户做处理，通常做网络
         * 请求时可以在此处隐藏“正在加载”的等待控件
         */
        public abstract void onComplete();
    }
}
