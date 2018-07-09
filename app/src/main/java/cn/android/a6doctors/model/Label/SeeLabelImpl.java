package cn.android.a6doctors.model.Label;

import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.factory.DoctorApi.DoctorAPIFactory;
import cn.android.a6doctors.factory.DoctorApi.DoctorAPIFactoryImpl;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public class SeeLabelImpl implements SeeLabelModel {
    private DoctorAPIFactory apiFactory;

    public SeeLabelImpl( ) {
        this.apiFactory = DoctorAPIFactoryImpl.getInstance();
    }
    /**
     *查看标签（分组）中的所有患者
     */
    @Override
    public void findByLabelId(String token, int labelId, CallBack callback) {
        apiFactory.findByLabelId(token,labelId,callback);
    }

    @Override
    public void delLabelPatient(String token, int labelId, int patientId, CallBack callback) {
        apiFactory.delLabelPatient(token,labelId,patientId,callback);
    }

    @Override
    public void updateLabel(String token, String name, int labelId, CallBack callback) {
        apiFactory.updateLabel(token,name,labelId,callback);
    }


}
