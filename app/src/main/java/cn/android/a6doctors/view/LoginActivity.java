package cn.android.a6doctors.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import cn.android.a6doctors.R;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient_Case;
import cn.android.a6doctors.model.LoginImpl;
import cn.android.a6doctors.presenter.LoginPresenter;
import cn.android.a6doctors.util.AppSharePreferenceMgr;
import cn.android.a6doctors.util.DialogThridUtils;
import cn.android.a6doctors.util.EditTextClearTools;
import cn.android.a6doctors.util.LogUtil;
import cn.android.a6doctors.util.WeiboDialogUtils;


public class LoginActivity extends BaseActivity implements LoginView,View.OnClickListener, CompoundButton.OnCheckedChangeListener{

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

    private Dialog mDialog;
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
        EditTextClearTools.addClearListener(etPassword, ivPwdClear);
        if((Boolean)AppSharePreferenceMgr.get(this, "remember_password", false)){
            cbCheckbox.setChecked(true);
            etUserName.setText((String)AppSharePreferenceMgr.get(this,"account",""));
            etPassword.setText((String)AppSharePreferenceMgr.get(this,"password",""));
        }
        btnLogin.setOnClickListener(this);
        cbCheckbox.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                presenter.login(etUserName.getText().toString(),etPassword.getText().toString());
                if(cbCheckbox.isChecked()){
                    AppSharePreferenceMgr.put(this,"remember_password",true);
                    AppSharePreferenceMgr.put(this,"account",etUserName.getText().toString());
                    AppSharePreferenceMgr.put(this,"password",etPassword.getText().toString());
                }
                break;
        }
    }

    @Override
    public void success(Object data) {
        LogUtil.I(this,data.toString());
        JsonObject data1 = (JsonObject) data;
        String token = data1.get("token").getAsString();
        AppSharePreferenceMgr.put(this,"token",token);
        Doctor doctor = new Gson().fromJson(data1.get("data").getAsJsonObject(), Doctor.class);
        AppSharePreferenceMgr.put(this,"doctorId",doctor.getDoctorId());
        Intent intent = new Intent(this,MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("doctor",doctor);
        intent.putExtra("doctor",bundle);
        startActivity(intent);
    }

    @Override
    public void failure(String msg) {
        LogUtil.I(this,msg);
    }

    @Override
    public void error() {
        LogUtil.I(this,"error");
    }

    @Override
    public void showLoading() {
        mDialog = WeiboDialogUtils.createLoadingDialog(this, "正在登陆");
    }

    @Override
    public void closeLoading() {
        WeiboDialogUtils.closeDialog(mDialog);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked){
            AppSharePreferenceMgr.put(this,"remember_password",true);
            AppSharePreferenceMgr.put(this,"account",etUserName.getText().toString());
            AppSharePreferenceMgr.put(this,"password",etPassword.getText().toString());
        }else {
            //从数组中移除
            AppSharePreferenceMgr.put(this,"remember_password",false);
            AppSharePreferenceMgr.remove(this,"account");
            AppSharePreferenceMgr.remove(this,"password");
        }
    }
}

