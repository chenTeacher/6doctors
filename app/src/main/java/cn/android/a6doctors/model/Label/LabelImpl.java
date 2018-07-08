package cn.android.a6doctors.model.Label;

import cn.android.a6doctors.bean.Label;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.factory.APIFactoryImpl;
import cn.android.a6doctors.factory.DoctorApi.DoctorAPIFactory;
import cn.android.a6doctors.factory.DoctorApi.DoctorAPIFactoryImpl;

/**
 * Created by ChenTeacher on 2018/7/7.
 */

public class LabelImpl implements LabelModel {
    private DoctorAPIFactory apiFactory;

    public LabelImpl( ) {
        this.apiFactory = DoctorAPIFactoryImpl.getInstance();
    }

    @Override
    public void findLabelsByDoctorId(String token,CallBack callBack) {
        apiFactory.findLabelsByDoctorId(token,callBack);
    }

    @Override
    public void delLabel(String token, Label label, CallBack callBack) {
        apiFactory.delLabel(token,label.getLabelId(),callBack);
    }
}
