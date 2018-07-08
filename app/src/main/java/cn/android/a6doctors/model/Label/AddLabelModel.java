package cn.android.a6doctors.model.Label;

import cn.android.a6doctors.callback.CallBack;

/**
 * Created by ChenTeacher on 2018/7/7.
 */

public interface AddLabelModel {
    /**
     * 添加分组
     */
    void addLabel(String token,String name,CallBack callBack);
}
