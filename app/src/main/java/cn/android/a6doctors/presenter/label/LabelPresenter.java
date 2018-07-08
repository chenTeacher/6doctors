package cn.android.a6doctors.presenter.label;

import android.content.Context;

import java.util.List;

import cn.android.a6doctors.bean.Label;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.model.AddPatientCaseModel;
import cn.android.a6doctors.model.Label.LabelModel;
import cn.android.a6doctors.view.AddPatientCaseView;
import cn.android.a6doctors.view.label.LabelView;

/**
 * Created by ChenTeacher on 2018/5/16.
 */

public class LabelPresenter {
    LabelModel iMoudel;
    LabelView iView;
    Context mContext;

    public LabelPresenter(LabelModel iMoudel, LabelView iView, Context mContext) {
        this.iMoudel = iMoudel;
        this.iView = iView;
        this.mContext = mContext;
    }

    /**
     * 返回上一个界面
     */
     public void  goBack(){
        iView.goBack();
     };
    /**
     *
     */
    public void addLable(){
        iView.addLabel();
    };
    /**
     * 查询分组
     */
    public void findLabelsByDoctorId(String token){
        iMoudel.findLabelsByDoctorId(token,new CallBack(){
            @Override
            public void onSuccess(Object data) {
                iView.findLabelsByDoctorId(data);
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
    public void delLabel(String token, final Label label){
        iMoudel.delLabel(token, label, new CallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.delLabel(label);
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
    };


}
