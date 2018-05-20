package cn.android.a6doctors.view;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public interface SeePatientView {

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
     * 拍照
     */
    void photoSelector();
    /**
     * 保存信息
     */
    void save();
}
