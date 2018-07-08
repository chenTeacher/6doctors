package cn.android.a6doctors.presenter.label;

import android.content.Context;

import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.model.Label.AddLabelModel;
import cn.android.a6doctors.model.Label.LabelModel;
import cn.android.a6doctors.view.label.AddLabelView;
import cn.android.a6doctors.view.label.LabelView;

/**
 * Created by ChenTeacher on 2018/5/16.
 */

public class AddLabelPresenter {
    AddLabelModel iMoudel;
    AddLabelView iView;
    Context mContext;

    public AddLabelPresenter(AddLabelModel iMoudel, AddLabelView iView, Context mContext) {
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
     * 添加分组
     */
    public void addLabel(String token,String name,CallBack callBack){
        iMoudel.addLabel(token,name,callBack);
    }
}
