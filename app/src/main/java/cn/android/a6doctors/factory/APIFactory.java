package cn.android.a6doctors.factory;


import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;


/**
 * Created by ChenTeacher on 2018-05-15.
 */

public interface APIFactory {

    /**
     * 获取患者列表
     */
    void getPatientList(String patientId,Patient.CallBack callBack);
    /**
     * 获取患者的详情
     * */
    void getPatientInfo(String patientId,Patient_Case.CallBack callBack);
}
