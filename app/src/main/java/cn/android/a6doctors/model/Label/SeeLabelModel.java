package cn.android.a6doctors.model.Label;


import cn.android.a6doctors.callback.CallBack;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public interface SeeLabelModel {
    /**
     *查看标签（分组）中的所有患者
     */
    void findByLabelId(String token, int labelId, CallBack callback);

    /**
     * 移除患者
     */
    void delLabelPatient(String token,int labelId,int patientId,CallBack callback);

    /**
     * 修改标签名称
     */
    void updateLabel(String token,String name,int labelId,CallBack callback);
}
