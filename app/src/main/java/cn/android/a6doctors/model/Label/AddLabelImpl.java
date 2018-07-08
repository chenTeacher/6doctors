package cn.android.a6doctors.model.Label;


import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.factory.DoctorApi.DoctorAPIFactory;
import cn.android.a6doctors.factory.DoctorApi.DoctorAPIFactoryImpl;

/**
 * Created by ChenTeacher on 2018/7/7.
 */

public class AddLabelImpl implements AddLabelModel{
    private DoctorAPIFactory apiFactory;

    public AddLabelImpl( ) {
        this.apiFactory = DoctorAPIFactoryImpl.getInstance();
    }

    @Override
    public void addLabel(String token,String name,CallBack callBack) {
        this.apiFactory.addLabel(token,name,callBack);
    }
}
