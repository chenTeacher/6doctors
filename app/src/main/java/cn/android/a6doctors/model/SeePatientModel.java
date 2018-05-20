package cn.android.a6doctors.model;

import cn.android.a6doctors.bean.Patient;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public interface SeePatientModel {
    /**
     * 向服务器上传添加患者的信息
     */
    void  updataPatient(Patient patient);
}
