package cn.android.a6doctors.model;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;
import cn.android.a6doctors.bean.Patient_Case_Collection;
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
    public void getPatientList(final String doctorId, final Patient.CallBack callback) {
        apiFactory.getPatientList(doctorId,callback);
    }

    @Override
    public void getPatientInfo(final String patientId,final Patient_Case.CallBack callback) {
        apiFactory.getPatientInfo(patientId,callback);
    }

}
