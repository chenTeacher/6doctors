package cn.android.a6doctors.presenter;

import android.content.Context;

import cn.android.a6doctors.model.AddPatientModel;
import cn.android.a6doctors.model.LoginModel;
import cn.android.a6doctors.view.LoginView;

/**
 * Created by ChenTeacher on 2018/5/20.
 */

public class LoginPresenter {
    LoginView loginView;
    LoginModel loginModel;
    Context mContext;

    public LoginPresenter(LoginModel iMoudel, LoginView iView, Context mContext) {
        this.loginModel = iMoudel;
        this.loginView = iView;
        this.mContext = mContext;
    }

    public void login(){
        loginView.login();
    }

}
