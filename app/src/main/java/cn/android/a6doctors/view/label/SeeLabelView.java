package cn.android.a6doctors.view.label;

/**
 * Created by ChenTeacher on 2018/7/7.
 */

public interface SeeLabelView {
    /**
     * 返回上一个界面
     */
    void  goBack();
    /**
     * 查看标签（分组）中的所有患者
     */
    void findByLabelId();
    /**
     *移除病人的标签（分组）
     */
    void delLabelPatient();
}
