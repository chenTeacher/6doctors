package cn.android.a6doctors.model;

import java.util.List;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.factory.APIFactoryImpl;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public class SeePatientCaseImpl implements SeePatientCaseModel {
    private APIFactoryImpl apiFactory;

    public SeePatientCaseImpl(){
        apiFactory = APIFactoryImpl.getInstance();
    }


    @Override
    public void updateTherapy(String token, int therapyId, List<String> photos, int doctorId, int patientId, String state, String date, String record, CallBack callBack) {
        apiFactory.updateTherapy(token,therapyId,photos,doctorId,patientId,state,date,record,callBack);
    }

    @Override
    public void getTherapy(String token, int therapyId, CallBack callBack) {
        apiFactory.getTherapy(token,therapyId,callBack);
    }
}
