package cn.android.a6doctors.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by ChenTeacher on 2018/6/3.
 */

public class PatientInfo implements Parcelable {
    /**
     *  患者id
     */
    private int patientId;
    /*患者姓名*/
    private String patientName;
    /*患者性别*/
    private String gender;
    /*患者电话*/
    private String mobPhone;
    /*患者年龄*/
    private int age;
    /*证件类型*/
    private String identityType;
    /*证件号码*/
    private String identityNum;
    /*地址*/
    private String address;
    /*详细地址*/
    private String place;
    /*头像地址*/
    private String avatar;
    /*病例集合*/
    private List<TherapieItem> therapies;
    /*首次就诊时间*/
    private String firstTherapyDate;
    /*首次就诊医生*/
    private String firstTherapyDoctor;
    /*最近一次就诊时间*/
    private String lastTherapyDate;
    /*最近一次就诊医生*/
    private String lastTherapyDoctor;
    /*最近一次就诊状态*/
    private String lastState;
    /*病例数*/
    private String therapyCount;

    public PatientInfo() {
    }

    public PatientInfo(int patientId, String patientName, String gender, String mobPhone, int age, String identityType, String identityNum, String address, String place, String avatar, List<TherapieItem> therapies, String firstTherapyDate, String firstTherapyDoctor, String lastTherapyDate, String lastTherapyDoctor, String lastState, String therapyCount) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.gender = gender;
        this.mobPhone = mobPhone;
        this.age = age;
        this.identityType = identityType;
        this.identityNum = identityNum;
        this.address = address;
        this.place = place;
        this.avatar = avatar;
        this.therapies = therapies;
        this.firstTherapyDate = firstTherapyDate;
        this.firstTherapyDoctor = firstTherapyDoctor;
        this.lastTherapyDate = lastTherapyDate;
        this.lastTherapyDoctor = lastTherapyDoctor;
        this.lastState = lastState;
        this.therapyCount = therapyCount;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        if(patientName == null){
            patientName ="";
        }
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getGender() {
        if(gender ==  null){
            gender ="";
        }
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobPhone() {
        if(mobPhone ==  null){
            mobPhone ="";
        }
        return mobPhone;
    }

    public void setMobPhone(String mobPhone) {
        this.mobPhone = mobPhone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdentityType() {
        if(identityType == null){
            identityType = "身份证";
        }
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    public String getIdentityNum() {
        if(identityNum == null){
            identityNum = "";
        }
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getAddress() {
        if(address == null){
            address = "";
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlace() {
        if(place == null){
            place = "";
        }
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAvatar() {
        if(avatar == null){
            avatar ="";
        }
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<TherapieItem> getTherapies() {
        return therapies;
    }

    public void setTherapies(List<TherapieItem> therapies) {
        this.therapies = therapies;
    }

    public String getFirstTherapyDate() {
        if(firstTherapyDate ==null){
            return "暂无数据";
        }
        return firstTherapyDate.substring(0,10);
    }

    public void setFirstTherapyDate(String firstTherapyDate) {
        this.firstTherapyDate = firstTherapyDate;
    }

    public String getFirstTherapyDoctor() {
        if(firstTherapyDoctor == null ){
            return "暂无数据";
        }
        return firstTherapyDoctor;
    }

    public void setFirstTherapyDoctor(String firstTherapyDoctor) {
        this.firstTherapyDoctor = firstTherapyDoctor;
    }

    public String getLastTherapyDate() {
        if(lastTherapyDate == null){
            return  "暂无数据";
        }
        return lastTherapyDate.substring(0,10);
    }

    public void setLastTherapyDate(String lastTherapyDate) {
        this.lastTherapyDate = lastTherapyDate;
    }

    public String getLastTherapyDoctor() {
        if( lastTherapyDoctor == null){
            lastTherapyDoctor = "暂无数据";
        }
        return lastTherapyDoctor;
    }

    public void setLastTherapyDoctor(String lastTherapyDoctor) {
        this.lastTherapyDoctor = lastTherapyDoctor;
    }

    public String getLastState() {
        if(lastState == null){
            lastState = "暂无数据";
        }
        return lastState;
    }

    public void setLastState(String lastState) {
        this.lastState = lastState;
    }

    public String getTherapyCount() {
        if(therapyCount == null){
            therapyCount = "暂无数据";
        }
        return therapyCount;
    }

    public void setTherapyCount(String therapyCount) {
        this.therapyCount = therapyCount;
    }

    @Override
    public String toString() {
        return "PatientInfo{" +
                "patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", gender='" + gender + '\'' +
                ", mobPhone='" + mobPhone + '\'' +
                ", age=" + age +
                ", identityType='" + identityType + '\'' +
                ", identityNum='" + identityNum + '\'' +
                ", address='" + address + '\'' +
                ", place='" + place + '\'' +
                ", avatar='" + avatar + '\'' +
                ", therapies=" + therapies +
                ", firstTherapyDate='" + firstTherapyDate + '\'' +
                ", firstTherapyDoctor='" + firstTherapyDoctor + '\'' +
                ", lastTherapyDate='" + lastTherapyDate + '\'' +
                ", lastTherapyDoctor='" + lastTherapyDoctor + '\'' +
                ", lastState='" + lastState + '\'' +
                ", therapyCount='" + therapyCount + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.patientId);
        dest.writeString(this.patientName);
        dest.writeString(this.gender);
        dest.writeString(this.mobPhone);
        dest.writeInt(this.age);
        dest.writeString(this.identityType);
        dest.writeString(this.identityNum);
        dest.writeString(this.address);
        dest.writeString(this.place);
        dest.writeString(this.avatar);
        dest.writeTypedList(this.therapies);
        dest.writeString(this.firstTherapyDate);
        dest.writeString(this.firstTherapyDoctor);
        dest.writeString(this.lastTherapyDate);
        dest.writeString(this.lastTherapyDoctor);
        dest.writeString(this.lastState);
        dest.writeString(this.therapyCount);
    }

    protected PatientInfo(Parcel in) {
        this.patientId = in.readInt();
        this.patientName = in.readString();
        this.gender = in.readString();
        this.mobPhone = in.readString();
        this.age = in.readInt();
        this.identityType = in.readString();
        this.identityNum = in.readString();
        this.address = in.readString();
        this.place = in.readString();
        this.avatar = in.readString();
        this.therapies = in.createTypedArrayList(TherapieItem.CREATOR);
        this.firstTherapyDate = in.readString();
        this.firstTherapyDoctor = in.readString();
        this.lastTherapyDate = in.readString();
        this.lastTherapyDoctor = in.readString();
        this.lastState = in.readString();
        this.therapyCount = in.readString();
    }

    public static final Parcelable.Creator<PatientInfo> CREATOR = new Parcelable.Creator<PatientInfo>() {
        @Override
        public PatientInfo createFromParcel(Parcel source) {
            return new PatientInfo(source);
        }

        @Override
        public PatientInfo[] newArray(int size) {
            return new PatientInfo[size];
        }
    };
}
