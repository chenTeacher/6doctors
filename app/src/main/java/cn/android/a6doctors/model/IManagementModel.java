package cn.android.a6doctors.model;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;

/**
 * Created by ChenTeacher on 2018/5/13.
 */

public interface IManagementModel {
    /**
     * 获取患者列表
     * @param doctorId 医生的Id编码
     */
    void getPatientList(String doctorId,Patient.CallBack callback);

    /**
     * 获取患者的详细信息
     * @param patinetId 患者的Id编码
     */
    void getPatientInfo(String patinetId,Patient_Case.CallBack callback);

}
