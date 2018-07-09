package cn.android.a6doctors.view.label;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.R;
import cn.android.a6doctors.adapter.PatientAdapter;
import cn.android.a6doctors.adapter.Patient_Case_Collection_Adapter;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.PatientInfo;
import cn.android.a6doctors.bean.PatientItem;
import cn.android.a6doctors.bean.TherapieItem;
import cn.android.a6doctors.model.Label.SeeInfoImpl;
import cn.android.a6doctors.presenter.label.SeeInfoPresenter;
import cn.android.a6doctors.util.AppSharePreferenceMgr;
import cn.android.a6doctors.util.REQUEST_CODE;
import cn.android.a6doctors.view.AddPatientCaseActivity;
import cn.android.a6doctors.view.SeePatientActivity;
import cn.android.a6doctors.view.SeePatientCaseActivity;

public class SeeInfoActivity extends BaseActivity implements SeeInfoView,View.OnClickListener{

    @BindView(R.id.close_btn)
    ImageButton closeBtn;
    @BindView(R.id.main_patient_image)
    ImageView mainPatientImage;
    @BindView(R.id.patient_name)
    TextView patientName;
    @BindView(R.id.patient_disease_state)
    TextView patientDiseaseState;
    @BindView(R.id.patient_count)
    TextView patientCount;
    @BindView(R.id.patient_case)
    LinearLayout patientCase;
    @BindView(R.id.intent_add_case)
    ImageButton intentAddCase;
    @BindView(R.id.intent_see_patient)
    ImageButton intentSeePatient;
    @BindView(R.id.patient_id)
    TextView patientId;
    @BindView(R.id.patient_sex)
    TextView patientSex;
    @BindView(R.id.patient_age)
    TextView patientAge;
    @BindView(R.id.patient_native_place)
    TextView patientNativePlace;
    @BindView(R.id.first_time)
    TextView firstTime;
    @BindView(R.id.first_doctor)
    TextView firstDoctor;
    @BindView(R.id.last_time)
    TextView lastTime;
    @BindView(R.id.last_doctor)
    TextView lastDoctor;
    @BindView(R.id.patient_case2)
    TableLayout patientCase2;
    @BindView(R.id.card_view)
    CardView cardView;
    @BindView(R.id.main_patient_case_collection_info_list_view)
    RecyclerView mainPatientCaseCollectionInfoListView;
    private SeeInfoPresenter presenter;
    private String token;
    private PatientItem patientItem;

    private List<TherapieItem> infolist = new ArrayList<TherapieItem>();
    private Patient_Case_Collection_Adapter patientInfoAdapter;

    private Patient patient;
    private Doctor doctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_info);
        ButterKnife.bind(this);
        presenter = new SeeInfoPresenter(new SeeInfoImpl(),this,this);
        token = (String) AppSharePreferenceMgr.get(this, "token", "");
        doctor =  getIntent().getBundleExtra("bundle").getParcelable("doctor");
        patientItem = getIntent().getBundleExtra("bundle").getParcelable("patientItem");
        initView();
    }

    @Override
    public void initView() {
        closeBtn.setOnClickListener(this);
        intentAddCase.setOnClickListener(this);
        intentSeePatient.setOnClickListener(this);
        setPatientInfoListView();
    }
    /**
     * 创建患者详情
     */
    private void setPatientInfoListView(){
        //创建LinearLayoutManager 对象 这里使用LinearLayoutManager 是线性布局的意思
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        //设置RecyclerView 布局
        mainPatientCaseCollectionInfoListView.setLayoutManager(layoutmanager);
        //设置Adapter
        patientInfoAdapter = new Patient_Case_Collection_Adapter(this,infolist);
        patientInfoAdapter.setOnItemClickListener(new PatientAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                if(view.getId()==R.id.patient_case_collection_button) presenter.seeDiagnosisInfo(infolist.get(data).getTherapyId());
            }

            @Override
            public void delLabel(int position) {

            }
        });
        mainPatientCaseCollectionInfoListView.setAdapter(patientInfoAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        presenter.getPatientInfo(token,patientItem.getPatientId());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.close_btn:
                presenter.goBack();
                break;
            //添加诊疗记录
            case R.id.intent_add_case:
                presenter.addDiagnosisInfo();
                break;
            //查看编辑患者信息
            case R.id.intent_see_patient:
                presenter.seePatientInfo();
                break;
        }

    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void showPatient(Object object) {
        PatientInfo patientInfo = new Gson().fromJson((JsonObject)object, PatientInfo.class);
        patientName.setText(patientInfo.getPatientName());
        patientCount.setText(patientInfo.getTherapyCount()+"份诊疗记录");
        patientSex.setText(patientInfo.getGender());
        patientAge.setText(patientInfo.getAge()+"岁");
        patientNativePlace.setText(patientInfo.getAddress());
        patientId.setText("No."+Integer.toString(patientInfo.getPatientId()));
        patientDiseaseState.setText(patientInfo.getLastState());
        firstTime.setText(patientInfo.getFirstTherapyDate());
        firstDoctor.setText(patientInfo.getFirstTherapyDoctor());
        lastTime.setText(patientInfo.getLastTherapyDate());
        lastDoctor.setText(patientInfo.getLastTherapyDoctor());
        patient = new Patient(  patientInfo.getPatientId(),
                patientInfo.getPatientName(),
                patientInfo.getGender(),
                patientInfo.getMobPhone(),
                patientInfo.getAge(),
                patientInfo.getIdentityType(),
                patientInfo.getIdentityNum(),
                patientInfo.getAddress(),
                patientInfo.getPlace(),
                patientInfo.getAvatar(),
                null);

        Glide.with(this)
                .load(patientInfo.getAvatar())
                .placeholder(R.drawable.main_person_image)//图片加载出来前，显示的图片
                .error(R.drawable.main_person_image)//图片加载失败后，显示的图片
                .into(mainPatientImage);
        this.infolist.clear();
        if(patientInfo.getTherapies() !=null){
            this.infolist.addAll(patientInfo.getTherapies());
        }
        patientInfoAdapter.notifyDataSetChanged();
    }

    @Override
    public void seePatientInfo() {
        if(patient == null){
            Toast.makeText(this,"请先选择一个患者",Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(this, SeePatientActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("patient",patient);
            intent.putExtra("bundle", bundle);
            startActivityForResult(intent, REQUEST_CODE.SEE_PATIENT_ACTIVITY);
        }
    }

    @Override
    public void addDiagnosisInfo() {
        if(patient == null){
            Toast.makeText(this,"请先选择一个患者",Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(this, AddPatientCaseActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("patient",patient);
            bundle.putParcelable("doctor",doctor);
            intent.putExtra("bundle", bundle);
            startActivityForResult(intent, REQUEST_CODE.ADD_PATIENT_CASE_ACTIVITY);
        }
    }

    @Override
    public void seeDiagnosisInfo(int therapyId) {
        Intent intent = new Intent(this, SeePatientCaseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("therapyId",therapyId);
        intent.putExtra("bundle", bundle);
        startActivityForResult(intent, REQUEST_CODE.SEE_PATIENT_CASE_ACTIVITY);
    }
}
