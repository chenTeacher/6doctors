package cn.android.a6doctors.presenter.label;

import android.content.Context;

import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.model.Label.SeeInfoModel;
import cn.android.a6doctors.view.label.SeeInfoView;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public class SeeInfoPresenter {
    SeeInfoModel iMoudel;
    SeeInfoView iView;
    Context mContext;

    public SeeInfoPresenter(SeeInfoModel iMoudel, SeeInfoView iView, Context mContext) {
        this.iMoudel = iMoudel;
        this.iView = iView;
        this.mContext = mContext;
    }

    /**
     * 返回上一个界面
     */
    public void  goBack(){
        iView.goBack();
    }
    /**
     * 获取网络数据获取患者的详细信息与列表
     */
    public void getPatientInfo(String token,int patinetId){
        iMoudel.getPatientInfo(token,patinetId,new CallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.showPatient(data);

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
        iView.seePatientInfo();
    };
    /**
     * 添加诊疗记录
     */
    public void addDiagnosisInfo(){
        iView.addDiagnosisInfo();
    };
    public void seeDiagnosisInfo(int therapyId){
        iView.seeDiagnosisInfo(therapyId);
    }
}
