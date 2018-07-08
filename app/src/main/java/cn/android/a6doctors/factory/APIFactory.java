package cn.android.a6doctors.factory;


import android.telecom.Call;

import java.util.List;

import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;
import cn.android.a6doctors.callback.CallBack;


/**
 * Created by ChenTeacher on 2018-05-15.
 */

public interface APIFactory {
    /**
     * 登录验证
     */
    void login(String account, String password, CallBack callBack);
    /**
     * 获取患者列表
     */
    void getPatients(String token ,int doctorId,CallBack callBack);
    /**
     *
     */
    void getPatient(String token,int patientId,CallBack callBack);
    /**
     * 获取患者的详情
     * */
    void getPatientInfo(String token,int patientId,CallBack callBack);
    /**
     * 添加患者信息
     */
    void createPatient(Doctor doctor, String token, Patient patient,CallBack callBack);
    /**
     * 更新患者信息
     */
    void updatePatient(String photo,String token,int patientId,String patientName,String gender,String mobPhone,int age,String identityType,String identityNum,String address,String place,CallBack callBack);
    /**
     * 创建病例
     * */
    void createTherapy(String token, List<String> photos, int doctorId, int patientId, String state, String date, String record, CallBack callBack);
    /**
     * 获取病例列表
     */
    void getTherapies(String token,int patientId,CallBack callBack);
    /**
     * 获取病例
     */
    void getTherapy(String token,int therapyId,CallBack callBack);
    /**
     * 更新病例
     */
    void updateTherapy(String token,int therapyId,List<String> photos,int doctorId,int patientId,String state,String date,String record,CallBack callBack);


}
