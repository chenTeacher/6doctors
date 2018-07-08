package cn.android.a6doctors.model.Label;

import cn.android.a6doctors.bean.Label;
import cn.android.a6doctors.callback.CallBack;

public interface LabelModel {
    /**
     * 查询分组信息
     */
    void findLabelsByDoctorId(String token, CallBack callBack);
    /**
    * 删除分组
    */
     void delLabel(String token, Label label, CallBack callBack);

}
