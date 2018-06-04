package cn.android.a6doctors.model;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;
import cn.android.a6doctors.callback.CallBack;

/**
 * Created by ChenTeacher on 2018/5/13.
 */

public interface IManagementModel {
    /**
     * 获取患者列表
     * @param doctorId 医生的Id编码
     */
    void getPatients(String token , int doctorId, CallBack callBack);
    /**
     *
     */
    void getPatientInfo(String token,int patientId,CallBack callBack);

//    /**
//     * 获取患者的详细信息
//     * @param patinetId 患者的Id编码
//     */
//    void getPatientInfo(String token,int patinetId,CallBack callback);
//
//    /**
//     * 获取患者的病例的列表
//     * @param token
//     * @param patientId
//     * @param callback
//     */
//    void getTherapies(String token, int patientId, CallBack callback);

}
