package cn.android.a6doctors.view.label;

/**
 * Created by ChenTeacher on 2018/7/7.
 */

public interface LabelView {
    /**
     * 返回上一个界面
     */
    void  goBack();
    /**
     * 添加分组
     */
    void addLabel();
    /**
     * 查询分组
     */
    void findLabelsByDoctorId(Object data);
}
