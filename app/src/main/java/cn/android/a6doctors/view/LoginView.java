package cn.android.a6doctors.view;

/**
 * Created by ChenTeacher on 2018/5/20.
 */

public interface LoginView {
    /**
     * 登录成功的操作
     */
    void success(Object data);
    /**
     * 登录失败的操作
     */
    void failure(String msg);
    /**
     * 登录异常的操作
     */
    void error();
    /**
     * 显示loadling
     */
    void showLoading();
    /**
     * 关闭loading
     */
    void closeLoading();
}
