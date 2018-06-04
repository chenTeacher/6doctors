package cn.android.a6doctors.model;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;
import cn.android.a6doctors.bean.Patient_Case_Collection;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.factory.APIFactoryImpl;

/**
 * Created by ChenTeacher on 2018/5/13.
 */

public class IManagementImpl implements IManagementModel{
    private APIFactoryImpl apiFactory;
    public IManagementImpl(){
        apiFactory = APIFactoryImpl.getInstance();
    }
    @Override
    public void getPatients(String token,int doctorId,CallBack callback) {
        apiFactory.getPatients(token,doctorId,callback);
    }

    @Override
    public void getPatientInfo(String token, int patientId, CallBack callBack) {
        apiFactory.getPatientInfo(token,patientId,callBack);
    }
}
