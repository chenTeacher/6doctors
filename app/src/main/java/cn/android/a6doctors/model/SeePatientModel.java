package cn.android.a6doctors.model;

import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.callback.CallBack;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public interface SeePatientModel {
    /**
     * 获取患者的详细信息
     * @param patinetId 患者的Id编码
     */
    void getPatient(String token,int patinetId,CallBack callback);
    /**
     * 向服务器上传添加患者的信息
     */
    void updatePatient(String photo,String token,int patientId,String patientName,String gender,String mobPhone,int age,String identityType,String identityNum,String address,String place,CallBack callBack);

}
