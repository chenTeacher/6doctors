package cn.android.a6doctors.view.label;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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
import cn.android.a6doctors.adapter.LabelAdapter;
import cn.android.a6doctors.adapter.PatientAdapter;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.bean.Label;

import cn.android.a6doctors.model.Label.LabelImpl;
import cn.android.a6doctors.presenter.label.LabelPresenter;
import cn.android.a6doctors.ui.SimpleItemTouchHelperCallback;
import cn.android.a6doctors.util.AppSharePreferenceMgr;

import cn.android.a6doctors.util.REQUEST_CODE;
import cn.android.a6doctors.util.SpacesItemDecoration;

public class LabelActivity extends BaseActivity implements LabelView,View.OnClickListener {
    List<Label> infolist  = new ArrayList<Label>();
    LabelAdapter labelAdapter = null;
    private LabelPresenter presenter;

    @BindView(R.id.close_btn)
    ImageButton closeBtn;
    @BindView(R.id.add_label)
    TextView addLabel;
    @BindView(R.id.label_rv)
    RecyclerView labelRv;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_label);
        ButterKnife.bind(this);
        initView();
        presenter = new LabelPresenter(new LabelImpl(), this, this);
        token = (String) AppSharePreferenceMgr.get(this,"token","");
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.findLabelsByDoctorId(token);
    }

    @Override
    public void initView() {
        addLabel.setOnClickListener(this);
        closeBtn.setOnClickListener(this);
        setlabelRvView();

    }
    /**
     * 分组列表
     */
    private void setlabelRvView(){
        //创建LinearLayoutManager 对象 这里使用LinearLayoutManager 是线性布局的意思
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        labelRv.setLayoutManager(layoutmanager);
        labelRv.addItemDecoration(new SpacesItemDecoration(1));
        //设置Adapter
        labelAdapter = new LabelAdapter(this,infolist);
        labelAdapter.setOnItemClickListener(new PatientAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
            }
        });
        labelRv.setAdapter(labelAdapter);
        //先实例化Callback
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(labelAdapter);
        //用Callback构造ItemtouchHelper
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        //调用ItemTouchHelper的attachToRecyclerView方法建立联系
        touchHelper.attachToRecyclerView(labelRv);
    }
    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void addLabel() {
        Intent intent = new Intent(this, AddLabelActivity.class);
        Bundle bundle = new Bundle();
        intent.putExtra("doctor", bundle);
        startActivityForResult(intent, REQUEST_CODE.PATIENT_LABEL_ADD);
    }

    @Override
    public void findLabelsByDoctorId(Object data) {
        this.infolist.clear();
        JsonArray array = new JsonParser().parse(data.toString()).getAsJsonArray();
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        for (final JsonElement elem : array) {
            infolist.add(gson.fromJson(elem, Label.class));
        }
        labelAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.close_btn:
                presenter.goBack();
                break;
            case R.id.add_label:
                presenter.addLable();
                break;
        }
    }

}
