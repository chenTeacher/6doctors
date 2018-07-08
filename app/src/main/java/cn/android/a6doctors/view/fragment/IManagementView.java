package cn.android.a6doctors.view.fragment;

import android.view.View;

import java.util.List;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;

/**
 * Created by ChenTeacher on 2018/5/13.
 */

public interface IManagementView {
    /**
     * 初始化view
     */
    void initView(View view);
    /**
     *   显示单个患者的信息与病例列表
     */
    void showPatient(Object object);
    /**
     * 查看患者信息
     */
    void seePatientInfo();
    /**
     * 添加患者信息
     */
    void addPatientInfo();
    /**
     * 添加诊疗记录
     */
    void addDiagnosisInfo();
    /**
     * 查看诊疗记录
     */
    void seeDiagnosisInfo(int therapyId);
    /**
     * 搜索患者
     */
    void searchPatient();
    /**
     * 通过二维码搜索患者
     */
    void searchPatientForZXing();
    /**
     * 下拉刷新成功的操作
     */
    void  refreshDataOnSuccess(Object data);
    /**
     * 下拉刷新失败的操作
     */
    void  refreshDataOnFailure(String error);
    /**
     * 上拉加载成功的操作
     */
    void loadMoreDataOnSuccess(Object data);
    /**
     * 上拉加载失败的操作
     */
    void  loadMoreDataOnFailure(String error);
    /**
     * 患者分组
     */
    void  patientLabel();

}
