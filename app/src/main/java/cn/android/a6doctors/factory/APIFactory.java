package cn.android.a6doctors.factory;


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
    void getPatientList(String token ,int doctorId,CallBack callBack);
    /**
     * 获取患者的详情
     * */
    void getPatientInfo(String patientId,Patient_Case.CallBack callBack);
    /**
     * 添加患者信息
     */
    void createPatient(Doctor doctor, String token, Patient patient);
}
