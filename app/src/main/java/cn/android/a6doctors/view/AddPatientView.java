package cn.android.a6doctors.view;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public interface AddPatientView {

    /**
     * 返回上一个界面
     */
    void  goBack();
    /**
     * 选择性别
     */
    void  selectSex();
    /**
     * 选择年龄
     */
    void selectAge();
    /**
     * 选择地址
     */
    void selectAddress();
    /**
     * 选择证件类型
     */
    void selectIdentityType();
    /**
     * 拍照
     */
    void photoSelector();
    /**
     * 保存信息
     */
    void saveOnSuccess();
    void saveOnFailure();
}
