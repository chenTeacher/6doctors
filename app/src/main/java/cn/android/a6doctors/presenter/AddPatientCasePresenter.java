package cn.android.a6doctors.presenter;

import android.content.Context;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.model.AddPatientCaseModel;
import cn.android.a6doctors.model.AddPatientModel;
import cn.android.a6doctors.view.AddPatientCaseView;
import cn.android.a6doctors.view.AddPatientView;

/**
 * Created by ChenTeacher on 2018/5/16.
 */

public class AddPatientCasePresenter {
    AddPatientCaseModel iMoudel;
    AddPatientCaseView iView;
    Context mContext;

    public AddPatientCasePresenter(AddPatientCaseModel iMoudel, AddPatientCaseView iView, Context mContext) {
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
    public void save(){

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
}
