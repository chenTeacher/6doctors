package cn.android.a6doctors.presenter.label;

import android.content.Context;

import java.util.List;

import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.model.Label.AddLabelModel;
import cn.android.a6doctors.model.Label.AddLabelPatientsModel;
import cn.android.a6doctors.view.label.AddLabelPatientsView;
import cn.android.a6doctors.view.label.AddLabelView;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public class AddLabelPatientsPresenter {
    AddLabelPatientsModel iMoudel;
    AddLabelPatientsView iView;
    Context mContext;

    public AddLabelPatientsPresenter(AddLabelPatientsModel iMoudel, AddLabelPatientsView iView, Context mContext) {
        this.iMoudel = iMoudel;
        this.iView = iView;
        this.mContext = mContext;
    }

    /**
     * 返回上一个界面
     */
    public void goBack() {
        iView.goBack();
    }

    ;

    /**
     * 获取患者列表
     */
    public void getPatients(String token, int doctorId) {
        iMoudel.getPatients(token, doctorId, new CallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.getPatients(data);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void addLabelPatients(String token, List<Integer> patientsId,int labelId){
        iMoudel.addLabelPatients(token, patientsId, labelId, new CallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.addLabelPatients(data);
            }

            @Override
            public void onFailure(String msg) {

            }

            @Override
            public void onError() {

            }

            @Override
            public void onComplete() {

            }
        });
    }


}
