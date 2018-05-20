package cn.android.a6doctors.presenter;

import android.content.Context;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.model.AddPatientModel;
import cn.android.a6doctors.view.AddPatientView;

/**
 * Created by ChenTeacher on 2018/5/16.
 */

public class AddPatientPresenter {
    AddPatientModel iMoudel;
    AddPatientView iView;
    Context mContext;

    public AddPatientPresenter(AddPatientModel iMoudel, AddPatientView iView, Context mContext) {
        this.iMoudel = iMoudel;
        this.iView = iView;
        this.mContext = mContext;


    }

    /**
     * 返回上一个界面
     */
    public void goBack() {
        iView.goBack();
    }


    /**
     * 选择性别
     */
    public void selectSex() {
        iView.selectSex();
    }


    /**
     * 选择地址
     */
    public void selectAddress() {
        iView.selectAddress();
    }
    /**
     * 选择年龄
     */
    public void  selectAge(){
        iView.selectAge();
    }

    /**
     * 拍照
     */
    public void photoSelector() {
        iView.photoSelector();
    }


    /**
     * 保存信息
     */
    public void save(Patient patient) {
        iMoudel.updataPatient(patient);
    }

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
