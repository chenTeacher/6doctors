package cn.android.a6doctors.model;

import java.util.List;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.callback.CallBack;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public interface SeePatientCaseModel {
    /**
     * 向服务器上传添加患者的信息
     */
    void updateTherapy(String token, int therapyId, List<String> photos, int doctorId, int patientId, String state, String date, String record, CallBack callBack);

    /**
     * 获取病例信息
     */
    void getTherapy(String token,int therapyId,CallBack callBack);
}
