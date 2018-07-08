package cn.android.a6doctors.presenter;

import android.content.Context;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.util.List;

import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.model.IManagementModel;
import cn.android.a6doctors.util.LogUtil;
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
     * 获取网络数据获取患者的详细信息与列表
     */
    public void getPatientInfo(int patinetId){
        iManagementModel.getPatientInfo(token,patinetId,new CallBack() {
            @Override
            public void onSuccess(Object data) {
//                LogUtil.I(mContext,data.toString());
//                Gson gson=  new GsonBuilder()
//                        //配置你的Gson
//                        .setDateFormat("yyyy-MM-dd hh:mm:ss")
//                        .create();
//                Patient patient = new Gson().fromJson((JsonObject)data, Patient.class);
                iManagementView.showPatient(data);

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
    public void seeDiagnosisInfo(int therapyId){
        iManagementView.seeDiagnosisInfo(therapyId);
    };
    /**
     * 通过二维码搜索患者
     */
    public void searchPatientForZXing(){
        iManagementView.searchPatientForZXing();
    };
    /**
     * 患者分组
     */
    public void patientLabel(){
        iManagementView.patientLabel();
    };
    /**
     * 刷新数据
     */
    public void refreshData(){
        iManagementModel.getPatients(token,doctorId,new CallBack() {
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
    /**
     * 上拉加载
     * */
    public void loadMoreData(){
        iManagementView.loadMoreDataOnSuccess(null);
    }




}
