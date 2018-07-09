package cn.android.a6doctors.view.label;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.api.ScrollBoundaryDecider;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.R;

import cn.android.a6doctors.adapter.PatientAdapter;
import cn.android.a6doctors.base.BaseActivity;

import cn.android.a6doctors.bean.Label;
import cn.android.a6doctors.bean.PatientItem;
import cn.android.a6doctors.model.Label.AddLabelPatientsImpl;
import cn.android.a6doctors.presenter.label.AddLabelPatientsPresenter;

import cn.android.a6doctors.util.AppSharePreferenceMgr;

import cn.android.a6doctors.util.SpacesItemDecoration;

public class AddLabelPatientsActivity extends BaseActivity implements AddLabelPatientsView, View.OnClickListener{

    @BindView(R.id.close_btn)
    ImageButton closeBtn;
    @BindView(R.id.add_btn)
    TextView addBtn;
    @BindView(R.id.patient_rv)
    RecyclerView patientRv;

    private AddLabelPatientsPresenter presenter;
    private String token;
    private int doctorId;
    private List<PatientItem> list = new ArrayList<PatientItem>();
    private List<Integer> addlist = new ArrayList<Integer>();
    private PatientAdapter patientAdapter;
    private Label label = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_label_patients);
        ButterKnife.bind(this);
        presenter = new AddLabelPatientsPresenter(new AddLabelPatientsImpl(),this,this);
        token = (String) AppSharePreferenceMgr.get(this,"token","");
        doctorId = (Integer) AppSharePreferenceMgr.get(this,"doctorId",0);
        label = getIntent().getBundleExtra("bundle").getParcelable("label");
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getPatients(token,doctorId);
    }
    /**
     * 列表
     */
    /**
     * 创建患者列表
     */
    private void  setPatientListView(){
        //创建LinearLayoutManager 对象 这里使用LinearLayoutManager 是线性布局的意思
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        patientRv.setLayoutManager(layoutmanager);
        patientRv.addItemDecoration(new SpacesItemDecoration(1));

        //设置Adapter
        patientAdapter = new PatientAdapter(this,list);

        patientAdapter.setOnItemClickListener(new PatientAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                if(addlist.indexOf(list.get(data).getPatientId())==-1){
                    view.setBackgroundColor(Color.parseColor("#501296db"));
                    addlist.add(list.get(data).getPatientId());
                }else{
                    view.setBackgroundColor(Color.parseColor("#90ffffff"));
                    addlist.remove((Object)list.get(data).getPatientId());
                }
                if(addlist.size()>0){
                    addBtn.setTextColor(Color.parseColor("#FFFFFF"));
                    addBtn.setOnClickListener(AddLabelPatientsActivity.this);
                }else{
                    addBtn.setTextColor(Color.parseColor("#90FFFFFF"));
                    addBtn.setOnClickListener(null);
                }
            }

            @Override
            public void delLabel(int position) {

            }
        });
        patientRv.setAdapter(patientAdapter);
    }
    @Override
    public void initView() {
        closeBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        setPatientListView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.close_btn:
                presenter.goBack();
                break;
            case R.id.add_btn:
                presenter.addLabelPatients(token,addlist,label.getLabelId());
                break;
        }
    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void getPatients(Object data) {
        this.list.clear();

        JsonArray array = new JsonParser().parse(data.toString()).getAsJsonArray();
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, PatientItem.class));
        }
        patientAdapter.notifyDataSetChanged();
    }

    @Override
    public void addLabelPatients(Object object) {
        finish();
    }
}
