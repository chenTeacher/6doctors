package cn.android.a6doctors.view.label;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.R;
import cn.android.a6doctors.adapter.PatientAdapter;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Label;
import cn.android.a6doctors.bean.PatientItem;
import cn.android.a6doctors.model.Label.SeeLabelImpl;
import cn.android.a6doctors.presenter.label.SeeLabelPresenter;
import cn.android.a6doctors.ui.PatientItemTouchHelperCallback;
import cn.android.a6doctors.util.AppSharePreferenceMgr;
import cn.android.a6doctors.util.REQUEST_CODE;
import cn.android.a6doctors.util.SpacesItemDecoration;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public class SeeLabelActivity extends BaseActivity implements SeeLabelView,View.OnClickListener{
    @BindView(R.id.close_btn)
    ImageButton closeBtn;
    @BindView(R.id.label_name)
    TextView labelName;
    @BindView(R.id.updata_label)
    TextView updataLabel;
    @BindView(R.id.patient_rv)
    RecyclerView patientRv;
    private SeeLabelPresenter presenter;
    private String token;
    List<PatientItem> infolist  = new ArrayList<PatientItem>();
    PatientAdapter adapter = null;
    Label label = null;
    Doctor doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_label);
        ButterKnife.bind(this);
        presenter = new SeeLabelPresenter(new SeeLabelImpl(), this, this);
        token = (String) AppSharePreferenceMgr.get(this, "token", "");
        label = getIntent().getBundleExtra("bundle").getParcelable("label");
        doctor = getIntent().getBundleExtra("bundle").getParcelable("doctor");
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.findByLabelId(token,label.getLabelId());
    }

    @Override
    public void initView() {
        labelName.setText(label.getName());
        closeBtn.setOnClickListener(this);
        labelName.setOnClickListener(this);
        updataLabel.setOnClickListener(this);
        setlabelRvView();
    }
    /**
     * 分组列表
     */
    private void setlabelRvView(){
        //创建LinearLayoutManager 对象 这里使用LinearLayoutManager 是线性布局的意思
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        patientRv.setLayoutManager(layoutmanager);
        patientRv.addItemDecoration(new SpacesItemDecoration(1));
        //设置Adapter
        adapter = new PatientAdapter(this,infolist);
        adapter.setOnItemClickListener(new PatientAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                //查看病人详情
                Intent intent = new Intent(SeeLabelActivity.this,SeeInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("patientItem",infolist.get(data));
                bundle.putParcelable("doctor",doctor);
                intent.putExtra("bundle", bundle);
                startActivityForResult(intent, REQUEST_CODE.Patients_INFO);
            }

            @Override
            public void delLabel(int position) {
                presenter.delLabelPatient(token,label.getLabelId(),infolist.get(position).getPatientId());
            }


        });
        patientRv.setAdapter(adapter);
        //先实例化Callback
        ItemTouchHelper.Callback callback = new PatientItemTouchHelperCallback(adapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(patientRv);
    }
    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void findByLabelId(Object data) {
        this.infolist.clear();
        JsonArray array = new JsonParser().parse(data.toString()).getAsJsonArray();
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        for (final JsonElement elem : array) {
            this.infolist.add(gson.fromJson(elem, PatientItem.class));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void delLabelPatient(Object data) {
      presenter.findByLabelId(token,label.getLabelId());
    }


    @Override
    public void addLabelPatients() {
        Intent intent = new Intent(SeeLabelActivity.this,AddLabelPatientsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("label",label);
        intent.putExtra("bundle", bundle);
        startActivityForResult(intent, REQUEST_CODE.Add_Label_Patients);
    }

    @Override
    public void updateLabel(String data) {
        if(dialog!=null){
            labelName.setText(data);
            dialog.cancel();
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.close_btn:
                presenter.goBack();
                break;
            case R.id.updata_label:
                presenter.addLabelPatients();
                break;
            case R.id.label_name:
                alert_edit(labelName.getText().toString());
                break;
        }
    }

    /**
     * 对话框
     */
    AlertDialog dialog = null;
    public void alert_edit(String name){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View
                .inflate(this, R.layout.update_label_dialog, null);
        builder.setView(view);
        builder.setCancelable(true);
        final EditText input_edt=view.findViewById(R.id.dialog_edit);//输入内容
        input_edt.setText(name);
        Button btn_cancel=view.findViewById(R.id.btn_cancel);//取消按钮
        Button btn_comfirm=view.findViewById(R.id.btn_comfirm);//确定按钮
        //取消或确定按钮监听事件处理
        dialog = builder.create();
        dialog.show();
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        btn_comfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.updateLabel(token,input_edt.getText().toString(),label.getLabelId());

            }
        });

    }
}
