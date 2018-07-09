package cn.android.a6doctors.view.label;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.R;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.model.Label.AddLabelImpl;
import cn.android.a6doctors.model.Label.LabelImpl;
import cn.android.a6doctors.presenter.label.AddLabelPresenter;
import cn.android.a6doctors.presenter.label.LabelPresenter;
import cn.android.a6doctors.util.AppSharePreferenceMgr;

public class AddLabelActivity extends BaseActivity implements AddLabelView, View.OnClickListener {
    private AddLabelPresenter presenter;
    private String token;
    @BindView(R.id.close_btn)
    ImageButton closeBtn;
    @BindView(R.id.add_label_btn)
    TextView addLabelBtn;
    @BindView(R.id.add_label_text)
    EditText addLabelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_label);
        ButterKnife.bind(this);
        initView();
        presenter = new AddLabelPresenter(new AddLabelImpl(), this, this);
        token = (String) AppSharePreferenceMgr.get(this,"token","");
    }

    @Override
    public void initView() {
        closeBtn.setOnClickListener(this);
        addLabelBtn.setOnClickListener(this);
        textChangedListener();
    }
    private void textChangedListener(){
        addLabelText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 输入的内容变化的监听
                Log.e("输入过程中执行该方法", s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("afterTextChanged",editable.length()+"");
                if(editable.length()>0){
                    addLabelBtn.setTextColor(Color.parseColor("#FFFFFF"));
                    addLabelBtn.setOnClickListener(AddLabelActivity.this);
                }else{
                    addLabelBtn.setTextColor(Color.parseColor("#90FFFFFF"));
                    addLabelBtn.setOnClickListener(null);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // 输入前的监听
                Log.e("输入前确认执行该方法", "开始输入"+s.toString());

            }
    });
    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_btn:
                presenter.goBack();
                break;
            case R.id.add_label_btn:
                Log.i("add_label_btn",addLabelText.getText().toString());
                if(addLabelText.getText().toString().length()>0){
                    presenter.addLabel(token, addLabelText.getText().toString(), new CallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        finish();
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
                break;
        }
    }
}
