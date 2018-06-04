package cn.android.a6doctors.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.model.SeePatientModel;
import cn.android.a6doctors.util.LogUtil;
import cn.android.a6doctors.view.SeePatientView;

/**
 * Created by ChenTeacher on 2018/5/16.
 */

public class SeePatientPresenter {
    SeePatientModel iMoudel;
    SeePatientView iView;
    Context mContext;

    public SeePatientPresenter(SeePatientModel iMoudel, SeePatientView iView, Context mContext) {
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
     * 选择证件类型
     */
    public void selectIdentityType(){
        iView.selectIdentityType();
    };
    /**
     * 拍照
     */
    public void photoSelector() {
        iView.photoSelector();
    }


    /**
     * 保存信息
     */
    public void save(String photo,String token,int patientId,String patientName,String gender,String mobPhone,int age,String identityType,String identityNum,String address,String place) {
        iMoudel.updatePatient(photo,token,patientId,patientName, gender, mobPhone,age, identityType,identityNum, address, place ,new CallBack() {
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
//                iView.saveOnFailure();
            }
        });
    }
    /**
     *  获取患者信息
     */
    public  void  getPatient(String token,int patientId){
        iMoudel.getPatient(token, patientId, new CallBack() {
            @Override
            public void onSuccess(Object data) {
                LogUtil.I(mContext,data.toString());
                Gson gson=  new GsonBuilder()
                        //配置你的Gson
                        .setDateFormat("yyyy-MM-dd hh:mm:ss")
                        .create();
                Patient patient = new Gson().fromJson((JsonObject)data, Patient.class);
                iView.getPatient(patient);
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
