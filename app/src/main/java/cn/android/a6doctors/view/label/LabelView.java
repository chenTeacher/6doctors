package cn.android.a6doctors.view.label;

import cn.android.a6doctors.bean.Label;
import cn.android.a6doctors.callback.CallBack;

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
    /**
     * 删除分组
     */
    void delLabel(Label label);
}
