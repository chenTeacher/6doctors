package cn.android.a6doctors.model;

import java.util.List;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.factory.APIFactoryImpl;
import cn.android.a6doctors.view.AddPatientCaseActivity;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public class AddPatientCaseImpl implements AddPatientCaseModel {
    private APIFactoryImpl apiFactory;

    public AddPatientCaseImpl(){
        apiFactory = APIFactoryImpl.getInstance();
    }


    @Override
    public void createTherapy(String token, List<String> photos, int doctorId, int patientId, String state, String date, String record, CallBack callBack) {
        apiFactory.createTherapy(token, photos, doctorId, patientId, state, date, record, callBack);
    }
}
