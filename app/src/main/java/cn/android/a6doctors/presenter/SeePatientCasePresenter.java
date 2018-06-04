package cn.android.a6doctors.presenter;

import android.content.Context;

import java.util.List;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.model.AddPatientCaseModel;
import cn.android.a6doctors.model.SeePatientCaseModel;
import cn.android.a6doctors.model.SeePatientModel;
import cn.android.a6doctors.view.AddPatientCaseView;
import cn.android.a6doctors.view.SeePatientCaseView;
import cn.android.a6doctors.view.SeePatientView;

/**
 * Created by ChenTeacher on 2018/5/16.
 */

public class SeePatientCasePresenter {
    SeePatientCaseModel iMoudel;
    SeePatientCaseView iView;
    Context mContext;

    public SeePatientCasePresenter(SeePatientCaseModel iMoudel, SeePatientCaseView iView, Context mContext) {
        this.iMoudel = iMoudel;
        this.iView = iView;
        this.mContext = mContext;


    }
    /**
     * 返回上一个界面
     */
    public void  goBack(){
        iView.goBack();
    };
    /**
     * 选择当前状态
     */
    public void  selectState(){
        iView.selectState();
    };
    /**
     * 选择医生
     */
    public void selectDoctor(){
        iView.selectDoctor();
    };
    /**
     * 选择时间
     */
    public  void selectTime(){
        iView.selectTime();
    };

    /**
     * 保存信息
     */
    public void updateTherapy(String token, int therapyId, List<String> photos, int doctorId, int patientId, String state, String date, String record){
        iMoudel.updateTherapy(token, therapyId, photos, doctorId, patientId, state, date, record, new CallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.saveOnSuccess();
            }

            @Override
            public void onFailure(String msg) {
                iView.saveOnFailure();
            }

            @Override
            public void onError() {
                iView.saveOnFailure();
            }

            @Override
            public void onComplete() {

            }
        });
    };

    /**
     * 保存成功
     */
    public void saveOnSuccess(Patient patient) {

    }

    /**
     * 保存失败
     */
    public void saveOnFailure() {

    }
    /**
     * 获取病例信息
     */
    public void getTherapy(String token,int therapyId){
        iMoudel.getTherapy(token,therapyId, new CallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.getTherapy(data);
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
}
