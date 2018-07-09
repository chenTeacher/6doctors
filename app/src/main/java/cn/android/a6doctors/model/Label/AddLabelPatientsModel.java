package cn.android.a6doctors.model.Label;

import java.util.List;

import cn.android.a6doctors.callback.CallBack;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public interface AddLabelPatientsModel {

    /**
     * 获取患者列表
     * @param doctorId 医生的Id编码
     */
    void getPatients(String token , int doctorId, CallBack callBack);
    /**
     *
     */
    void addLabelPatients(String token, List<Integer> patientsId, int labelId, CallBack callBack);
}
