package cn.android.a6doctors.model;

import cn.android.a6doctors.bean.Patient;
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
    public void updataPatient(Patient patient) {

    }
}
