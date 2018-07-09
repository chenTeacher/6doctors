package cn.android.a6doctors.model.Label;

import cn.android.a6doctors.callback.CallBack;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public interface SeeInfoModel {
    void getPatientInfo(String token,int patientId,CallBack callBack);
}
