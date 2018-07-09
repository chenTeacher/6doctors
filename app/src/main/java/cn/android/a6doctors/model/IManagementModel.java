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


}
