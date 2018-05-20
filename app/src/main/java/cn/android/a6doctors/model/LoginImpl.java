package cn.android.a6doctors.model;

import cn.android.a6doctors.factory.APIFactoryImpl;

/**
 * Created by ChenTeacher on 2018/5/20.
 */

public class LoginImpl implements LoginModel {
    private APIFactoryImpl apiFactory;
    public LoginImpl(){
        apiFactory = APIFactoryImpl.getInstance();
    }
    @Override
    public void login() {

    }
}
