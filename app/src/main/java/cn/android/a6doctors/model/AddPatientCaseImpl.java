package cn.android.a6doctors.model;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;
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
    public void updataPatient(Patient_Case patient_case) {

    }
}
