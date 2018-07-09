package cn.android.a6doctors.presenter.label;

import android.content.Context;

import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.model.Label.LabelModel;
import cn.android.a6doctors.model.Label.SeeLabelModel;
import cn.android.a6doctors.view.label.LabelView;
import cn.android.a6doctors.view.label.SeeLabelView;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public class SeeLabelPresenter {
    SeeLabelModel iMoudel;
    SeeLabelView iView;
    Context mContext;

    public SeeLabelPresenter(SeeLabelModel iMoudel, SeeLabelView iView, Context mContext) {
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
     *查看标签（分组）中的所有患者
     */
    public  void findByLabelId(String token,int labelId){
        iMoudel.findByLabelId(token, labelId, new CallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.findByLabelId(data);
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

    /**
     * 添加患者
     */
    public  void addLabelPatients(){
        iView.addLabelPatients();
    }
    /**
     * 移除患者
     */
    public  void delLabelPatient(String token,int labelId,int patientId){
        iMoudel.delLabelPatient(token, labelId,patientId, new CallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.delLabelPatient(data);
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
    /**
     * 修改标签名称
     */
    public  void updateLabel(String token, final String name, int labelId){
        iMoudel.updateLabel(token, name, labelId, new CallBack() {
            @Override
            public void onSuccess(Object data) {
                iView.updateLabel(name);
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
