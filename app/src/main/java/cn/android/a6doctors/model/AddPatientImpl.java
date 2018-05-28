package cn.android.a6doctors.model;

import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.factory.APIFactoryImpl;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public class AddPatientImpl implements AddPatientModel {
    private APIFactoryImpl apiFactory;

    public AddPatientImpl(){
        apiFactory = APIFactoryImpl.getInstance();
    }

    @Override
    public void createPatient(Doctor doctor, String token, Patient patient) {
        apiFactory.createPatient(doctor,token,patient);
    }
}
