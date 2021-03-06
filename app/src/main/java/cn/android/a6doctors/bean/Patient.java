package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ChenTeacher on 2017/12/1.
 */

public class Patient implements Parcelable {


        //患者id
        private Integer patientId;
        //患者姓名
        private String patientName;
        //性别
        private String gender;
        //手机号
        private String mobPhone;
        //年龄
        private Integer age;
        //证件类型
        private String identityType;
        //证件号码
        private String identityNum;
        //地址
        private String address;
        //地方
        private String place;
        //照片地址
        private String photoPath;
        //label
        private List<Labels> labels;


    public Patient() {
    }

    public Patient(Integer patientId, String patientName, String gender, String mobPhone, Integer age, String identityType, String identityNum, String address, String place, String photoPath, List<Labels> labels) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.gender = gender;
        this.mobPhone = mobPhone;
        this.age = age;
        this.identityType = identityType;
        this.identityNum = identityNum;
        this.address = address;
        this.place = place;
        this.photoPath = photoPath;
        this.labels = labels;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobPhone() {
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String hasNull(){
        if(this.patientName == null&&this.patientName.length()>0){
            return "请填写患者姓名";
        }else if(this.gender == null){
            return "请选择性别";
        }else if(this.mobPhone == null&&this.mobPhone.length()>0){
            return "请填写手机号";
        }else if( this.age == null){
            return  "请选择年龄";
        }else if(this.identityType == null){
            return "请选择证件类型";
        }else if(this.identityNum == null&&this.identityNum.length()>0){
            return "请填写证件号";
        }else if(this.address == null){
            return "请选择地址";
        }else if(this.place == null){
            return "请填写详细地址";
        }else if(this.photoPath == null){
            return "请选择照片";
        }
        return null;
    }

    public List<Labels> getLabels() {
        return labels;
    }

    public void setLabels(List<Labels> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", gender='" + gender + '\'' +
                ", mobPhone='" + mobPhone + '\'' +
                ", age=" + age +
                ", identityType='" + identityType + '\'' +
                ", identityNum='" + identityNum + '\'' +
                ", address='" + address + '\'' +
                ", place='" + place + '\'' +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.patientId);
        dest.writeString(this.patientName);
        dest.writeString(this.gender);
        dest.writeString(this.mobPhone);
        dest.writeValue(this.age);
        dest.writeString(this.identityType);
        dest.writeString(this.identityNum);
        dest.writeString(this.address);
        dest.writeString(this.place);
        dest.writeString(this.photoPath);
        dest.writeTypedList(this.labels);
    }

    protected Patient(Parcel in) {
        this.patientId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.patientName = in.readString();
        this.gender = in.readString();
        this.mobPhone = in.readString();
        this.age = (Integer) in.readValue(Integer.class.getClassLoader());
        this.identityType = in.readString();
        this.identityNum = in.readString();
        this.address = in.readString();
        this.place = in.readString();
        this.photoPath = in.readString();
        this.labels = in.createTypedArrayList(Labels.CREATOR);
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel source) {
            return new Patient(source);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };
}
