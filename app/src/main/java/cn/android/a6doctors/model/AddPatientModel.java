package cn.android.a6doctors.model;

import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.callback.CallBack;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public interface AddPatientModel {
    /**
     * 向服务器上传添加患者的信息
     */
    void  createPatient(Doctor doctor, String token, Patient patient, CallBack callBack);
}
