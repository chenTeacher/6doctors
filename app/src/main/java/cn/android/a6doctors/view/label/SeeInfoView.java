package cn.android.a6doctors.view.label;

import android.view.View;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public interface SeeInfoView {
    /**
     * 返回上一个界面
     */
    void goBack();
    /**
     *   显示单个患者的信息与病例列表
     */
    void showPatient(Object object);
    /**
     * 查看患者信息
     */
    void seePatientInfo();
    /**
     * 添加诊疗记录
     */
    void addDiagnosisInfo();
    /**
     * 查看诊疗记录
     */
    void seeDiagnosisInfo(int therapyId);

}
