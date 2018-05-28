package cn.android.a6doctors.model;


import cn.android.a6doctors.callback.CallBack;

/**
 * Created by ChenTeacher on 2018/5/20.
 */

public interface LoginModel {

    /**
     * 登录
     */
    void login(String account, String password, CallBack callBack);
}
