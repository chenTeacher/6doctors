package cn.android.a6doctors.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.R;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.model.LoginImpl;
import cn.android.a6doctors.presenter.LoginPresenter;
import cn.android.a6doctors.util.EditTextClearTools;


public class LoginActivity extends BaseActivity implements LoginView,View.OnClickListener{

    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.cb_checkbox)
    CheckBox cbCheckbox;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.iv_userIconName)
    ImageView ivUserIconName;
    @BindView(R.id.iv_unameClear)
    ImageView ivUnameClear;
    @BindView(R.id.iv_userIconPwd)
    ImageView ivUserIconPwd;
    @BindView(R.id.iv_pwdClear)
    ImageView ivPwdClear;


    LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        presenter = new LoginPresenter(new LoginImpl(),this,this);
    }

    @Override
    public void initView() {
        EditTextClearTools.addClearListener(etUserName, ivUnameClear);
        EditTextClearTools.addClearListener(etPassword, ivUserIconPwd);
        btnLogin.setOnClickListener(this);
    }



    @Override
    public void login() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                presenter.login();
                break;
        }
    }
}

