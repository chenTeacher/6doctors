package cn.android.a6doctors.factory.DoctorApi;


import java.util.List;

import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.callback.CallBack;


/**
 * Created by ChenTeacher on 2018-05-15.
 */

public interface DoctorAPIFactory {
    /**
     * 查询分组
     */
     void findLabelsByDoctorId(String token,CallBack callBack);
    /**
     * 添加分组
     */
    void addLabel(String token,String name,CallBack callBack);
    /**
     * 添加分组
     */
    void delLabel(String token,int labelId,CallBack callBack);


}
