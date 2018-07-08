package cn.android.a6doctors.view.label;

import android.os.Bundle;

import butterknife.ButterKnife;
import cn.android.a6doctors.R;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.model.Label.AddLabelImpl;
import cn.android.a6doctors.presenter.label.AddLabelPresenter;
import cn.android.a6doctors.util.AppSharePreferenceMgr;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public class SeeLabelActivity extends BaseActivity implements SeeLabelView {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_label);
        ButterKnife.bind(this);
        initView();
//        presenter = new AddLabelPresenter(new AddLabelImpl(), this, this);
//        token = (String) AppSharePreferenceMgr.get(this,"token","");
    }
    @Override
    public void initView() {

    }
    @Override
    public void goBack() {

    }

    @Override
    public void findByLabelId() {

    }

    @Override
    public void delLabelPatient() {

    }


}
