package cn.android.a6doctors.presenter;

import android.content.Context;


import java.util.List;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.model.IManagementModel;
import cn.android.a6doctors.view.fragment.IManagementView;

/**
 * Created by ChenTeacher on 2018/5/13.
 */

public class IManagementPresenter {
    IManagementModel iManagementModel;
    IManagementView iManagementView;
    Context mContext;

    String token;
    int doctorId;


    public void setToken(String token) {
        this.token = token;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public IManagementPresenter(Context context, IManagementModel iManagementModel, IManagementView iManagementView){
        this.mContext = context;
        this.iManagementModel = iManagementModel;
        this.iManagementView = iManagementView;
    }
    /**
     * 获取网络数据获取患者的详细信息
     */
    public void getPatientInfo(){
        iManagementModel.getPatientInfo("1",new  Patient_Case.CallBack() {

            @Override
            public void onSuccess(Patient_Case patient_case) {
                iManagementView.showPatientInfo(patient_case);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });
    }
    /**
     * 查看患者信息
     */
    public void seePatientInfo(){
        iManagementView.seePatientInfo();
    };
    /**
     * 添加患者信息
     */
    public void addPatientInfo(){
        iManagementView.addPatientInfo();
    };
    /**
     * 添加诊疗记录
     */
    public void addDiagnosisInfo(){
        iManagementView.addDiagnosisInfo();
    };
    /**
     *
     */
    public void seeDiagnosisInfo(){
        iManagementView.seeDiagnosisInfo();
    };
    /**
     * 通过二维码搜索患者
     */
    public void searchPatientForZXing(){
        iManagementView.searchPatientForZXing();
    };
    /**
     * 刷新数据
     */
    public void refreshData(){
        iManagementModel.getPatientList(token,doctorId,new CallBack() {
            @Override
            public void onSuccess(Object data) {
                iManagementView.refreshDataOnSuccess(data);
            }

            @Override
            public void onFailure(String msg) {
                iManagementView.refreshDataOnFailure("");
            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });

    }
    /***/
    public void loadMoreData(){


    }
}
