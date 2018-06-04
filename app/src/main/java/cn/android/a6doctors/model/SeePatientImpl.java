package cn.android.a6doctors.model;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.factory.APIFactoryImpl;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public class SeePatientImpl implements SeePatientModel {
    private APIFactoryImpl apiFactory;

    public SeePatientImpl(){
        apiFactory = APIFactoryImpl.getInstance();
    }

    @Override
    public void getPatient(String token, int patinetId, CallBack callback) {
        apiFactory.getPatient(token,patinetId,callback);
    }

    @Override
    public void updatePatient(String photo,String token,int patientId,String patientName,String gender,String mobPhone,int age,String identityType,String identityNum,String address,String place,CallBack callBack){
        apiFactory.updatePatient(photo,token,patientId,patientName,gender,mobPhone,age,identityType,identityNum,address,place,callBack);
    }
}
