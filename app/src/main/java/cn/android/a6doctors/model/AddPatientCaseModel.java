package cn.android.a6doctors.model;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public interface AddPatientCaseModel {
    /**
     * 向服务器上传添加患者的病例信息
     */
    void  updataPatient(Patient_Case patient_case);
}
