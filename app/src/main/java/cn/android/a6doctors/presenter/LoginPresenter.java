package cn.android.a6doctors.presenter;

import android.content.Context;

import cn.android.a6doctors.callback.CallBack;
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

    public void login(String account, String password){
        loginView.showLoading();
        loginModel.login(account, password, new CallBack() {
            @Override
            public void onSuccess(Object data) {
                loginView.success(data);
            }

            @Override
            public void onFailure(String msg) {
                loginView.failure(msg);
            }

            @Override
            public void onError() {
                loginView.error();
            }

            @Override
            public void onComplete() {
                loginView.closeLoading();
            }
        });
    }

}
