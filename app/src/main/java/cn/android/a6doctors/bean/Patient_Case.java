package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ChenTeacher on 2018/5/16.
 */

public class Patient_Case implements Parcelable {
    /*患者编号*/
    private String patient_id;
    /*头像*/
    private String patient_portrait;
    /*患者姓名*/
    private String patient_name;
    /*患者性别*/
    private String patient_sex;
    /*患者年龄*/
    private String patient_age;
    /*患者籍贯*/
    private String native_place;
    /*病症*/
    private String patient_disease;
    /*就诊状态*/
    private String patient_state;
    /*首次就诊时间 */
    private String first_time;
    /*最后一次就诊时间 */
    private String last_time;
    /*首次就诊医生*/
    private String first_doctor;
    /*最后一次就诊医生 */
    private String last_doctor;
    /*病例集合*/
    private List<Patient_Case_Collection>  patient_case_collections;
    public Patient_Case() {
    }

    public Patient_Case(String patient_id, String patient_portrait, String patient_name, String patient_sex, String patient_age, String native_place, String patient_disease, String patient_state, String first_time, String last_time, String first_doctor, String last_doctor, List<Patient_Case_Collection> patient_case_collections) {
        this.patient_id = patient_id;
        this.patient_portrait = patient_portrait;
        this.patient_name = patient_name;
        this.patient_sex = patient_sex;
        this.patient_age = patient_age;
        this.native_place = native_place;
        this.patient_disease = patient_disease;
        this.patient_state = patient_state;
        this.first_time = first_time;
        this.last_time = last_time;
        this.first_doctor = first_doctor;
        this.last_doctor = last_doctor;
        this.patient_case_collections = patient_case_collections;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getPatient_portrait() {
        return patient_portrait;
    }

    public void setPatient_portrait(String patient_portrait) {
        this.patient_portrait = patient_portrait;
    }

    public String getPatient_name() {
        return patient_name;
    }

    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    public String getPatient_sex() {
        return patient_sex;
    }

    public void setPatient_sex(String patient_sex) {
        this.patient_sex = patient_sex;
    }

    public String getPatient_age() {
        return patient_age;
    }

    public void setPatient_age(String patient_age) {
        this.patient_age = patient_age;
    }

    public String getNative_place() {
        return native_place;
    }

    public void setNative_place(String native_place) {
        this.native_place = native_place;
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

    public String getFirst_time() {
        return first_time;
    }

    public void setFirst_time(String first_time) {
        this.first_time = first_time;
    }

    public String getLast_time() {
        return last_time;
    }

    public void setLast_time(String last_time) {
        this.last_time = last_time;
    }

    public String getFirst_doctor() {
        return first_doctor;
    }

    public void setFirst_doctor(String first_doctor) {
        this.first_doctor = first_doctor;
    }

    public String getLast_doctor() {
        return last_doctor;
    }

    public void setLast_doctor(String last_doctor) {
        this.last_doctor = last_doctor;
    }

    public List<Patient_Case_Collection> getPatient_case_collections() {
        return patient_case_collections;
    }

    public void setPatient_case_collections(List<Patient_Case_Collection> patient_case_collections) {
        this.patient_case_collections = patient_case_collections;
    }

    @Override
    public String toString() {
        return "Patient_Case{" +
                "patient_id='" + patient_id + '\'' +
                ", patient_portrait='" + patient_portrait + '\'' +
                ", patient_name='" + patient_name + '\'' +
                ", patient_sex='" + patient_sex + '\'' +
                ", patient_age='" + patient_age + '\'' +
                ", native_place='" + native_place + '\'' +
                ", patient_disease='" + patient_disease + '\'' +
                ", patient_state='" + patient_state + '\'' +
                ", first_time='" + first_time + '\'' +
                ", last_time='" + last_time + '\'' +
                ", first_doctor='" + first_doctor + '\'' +
                ", last_doctor='" + last_doctor + '\'' +
                ", patient_case_collections=" + patient_case_collections +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.patient_id);
        dest.writeString(this.patient_portrait);
        dest.writeString(this.patient_name);
        dest.writeString(this.patient_sex);
        dest.writeString(this.patient_age);
        dest.writeString(this.native_place);
        dest.writeString(this.patient_disease);
        dest.writeString(this.patient_state);
        dest.writeString(this.first_time);
        dest.writeString(this.last_time);
        dest.writeString(this.first_doctor);
        dest.writeString(this.last_doctor);
        dest.writeTypedList(this.patient_case_collections);
    }

    protected Patient_Case(Parcel in) {
        this.patient_id = in.readString();
        this.patient_portrait = in.readString();
        this.patient_name = in.readString();
        this.patient_sex = in.readString();
        this.patient_age = in.readString();
        this.native_place = in.readString();
        this.patient_disease = in.readString();
        this.patient_state = in.readString();
        this.first_time = in.readString();
        this.last_time = in.readString();
        this.first_doctor = in.readString();
        this.last_doctor = in.readString();
        this.patient_case_collections = in.createTypedArrayList(Patient_Case_Collection.CREATOR);
    }

    public static final Parcelable.Creator<Patient_Case> CREATOR = new Parcelable.Creator<Patient_Case>() {
        @Override
        public Patient_Case createFromParcel(Parcel source) {
            return new Patient_Case(source);
        }

        @Override
        public Patient_Case[] newArray(int size) {
            return new Patient_Case[size];
        }
    };

    public interface CallBack{
        /**
         * 数据请求成功
         */
        public abstract void onSuccess(Patient_Case patient_case);
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
