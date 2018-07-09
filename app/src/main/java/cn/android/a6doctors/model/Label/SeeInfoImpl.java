package cn.android.a6doctors.model.Label;

import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.factory.APIFactoryImpl;
import cn.android.a6doctors.factory.DoctorApi.DoctorAPIFactory;
import cn.android.a6doctors.factory.DoctorApi.DoctorAPIFactoryImpl;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public class SeeInfoImpl implements SeeInfoModel{
    private APIFactoryImpl apiFactory;
    private DoctorAPIFactoryImpl doctorAPIFactory;
    public SeeInfoImpl(){
        doctorAPIFactory= DoctorAPIFactoryImpl.getInstance();
        apiFactory = APIFactoryImpl.getInstance();
    }

    @Override
    public void getPatientInfo(String token, int patientId, CallBack callBack) {
        apiFactory.getPatientInfo(token,patientId,callBack);
    }
}
