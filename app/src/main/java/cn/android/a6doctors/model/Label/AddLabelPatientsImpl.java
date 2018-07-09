package cn.android.a6doctors.model.Label;

import java.util.List;

import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.factory.APIFactoryImpl;
import cn.android.a6doctors.factory.DoctorApi.DoctorAPIFactory;
import cn.android.a6doctors.factory.DoctorApi.DoctorAPIFactoryImpl;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public class AddLabelPatientsImpl implements AddLabelPatientsModel {
    private APIFactoryImpl apiFactory;
    private DoctorAPIFactoryImpl doctorAPIFactory;
    public AddLabelPatientsImpl(){
        doctorAPIFactory= DoctorAPIFactoryImpl.getInstance();
        apiFactory = APIFactoryImpl.getInstance();
    }
    @Override
    public void getPatients(String token, int doctorId, CallBack callBack) {
        apiFactory.getPatients(token,doctorId,callBack);
    }

    @Override
    public void addLabelPatients(String token, List<Integer> patientsId, int labelId, CallBack callBack) {
        doctorAPIFactory.addLabelPatients(token,patientsId,labelId,callBack);
    }
}
