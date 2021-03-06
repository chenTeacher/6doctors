package cn.android.a6doctors.factory.DoctorApi;


import java.util.List;

import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.callback.CallBack;


/**
 * Created by ChenTeacher on 2018-05-15.
 */

public interface DoctorAPIFactory {
    /**
     * 查询分组
     */
     void findLabelsByDoctorId(String token,CallBack callBack);
    /**
     * 添加分组
     */
    void addLabel(String token,String name,CallBack callBack);
    /**
     * 删除分组
     */
    void delLabel(String token,int labelId,CallBack callBack);
    /**
     *查看标签（分组）中的所有患者
     */
    void findByLabelId(String token,int labelId,CallBack callBack);
    /**
     *给病人添加已经存在标签（分组）
     */
    void addLabelPatients(String token, List<Integer> patientsId, int labelId, CallBack callBack);
    /**
     * 移除患者
     */
    void delLabelPatient(String token,int labelId,int patientId,CallBack callback);

    /**
     * 修改标签名称
     */
    void updateLabel(String token,String name,int labelId,CallBack callback);
}
