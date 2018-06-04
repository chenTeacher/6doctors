package cn.android.a6doctors.view;

/**
 * Created by ChenTeacher on 2018/5/14.
 */

public interface AddPatientCaseView {

    /**
     * 返回上一个界面
     */
    void  goBack();
    /**
     * 选择当前状态
     */
    void  selectState();
    /**
     * 选择医生
     */
    void selectDoctor();
    /**
     * 选择时间
     */
    void selectTime();

    /**
     * 保存信息
     */
    void save();
    /**
     * 保存信息
     */
    void saveOnSuccess();
    void saveOnFailure();
}

