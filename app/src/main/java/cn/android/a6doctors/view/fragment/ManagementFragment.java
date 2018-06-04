package cn.android.a6doctors.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
import cn.android.a6doctors.adapter.Patient_Case_Collection_Adapter;

import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.PatientInfo;
import cn.android.a6doctors.bean.PatientItem;
import cn.android.a6doctors.bean.TherapieItem;
import cn.android.a6doctors.model.IManagementImpl;
import cn.android.a6doctors.presenter.IManagementPresenter;
import cn.android.a6doctors.util.AppSharePreferenceMgr;
import cn.android.a6doctors.util.LogUtil;
import cn.android.a6doctors.util.REQUEST_CODE;
import cn.android.a6doctors.util.SpacesItemDecoration;
import cn.android.a6doctors.view.AddPatientActivity;
import cn.android.a6doctors.view.AddPatientCaseActivity;
import cn.android.a6doctors.view.SeePatientActivity;
import cn.android.a6doctors.view.SeePatientCaseActivity;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ManagementFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ManagementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManagementFragment extends Fragment implements IManagementView, View.OnClickListener {
    private View mCacheView;
    private Context mContext;

    @BindView(R.id.main_patient_case_collection_list_view)
    protected RecyclerView patientListView;//患者列表
    @BindView(R.id.refreshLayout)
    protected RefreshLayout refreshLayout;
    @BindView(R.id.main_patient_case_collection_info_list_view)
    protected RecyclerView patient_Case_Collection_ListView;//患者就诊信息列表
    @BindView(R.id.main_patient_add)
    protected ImageView patient_add;//添加患者
    @BindView(R.id.intent_add_case)
    protected ImageView intent_add_case;//添加病例
    @BindView(R.id.intent_see_patient)
    protected ImageView intent_see_patient;//查看编辑患者信息
    @BindView(R.id.main_scanBtn)
    protected ImageView main_scanBtn;//扫一扫

    /**
     * 患者姓名
     */
    @BindView(R.id.patient_name)
    protected TextView patient_name;
    /**
     * 患者性别
     */
    @BindView(R.id.patient_sex)
    protected TextView patient_sex;
    /**
     * 患者编号
     */
    @BindView(R.id.patient_id)
    protected TextView patient_id;
    /**
     * 患者年龄
     */
    @BindView(R.id.patient_age)
    protected TextView patient_age;
    /**
     * 患者籍贯
     */
    @BindView(R.id.patient_native_place)
    protected TextView patient_native_place;
    /**
     * 患者头像
     */
    @BindView(R.id.main_patient_image)
    protected ImageView patient_portrait;
    /**
     * 病人就诊状态
     */
    @BindView(R.id.patient_disease_state)
    protected TextView patient_disease_state;
    /**
     * 病人就诊记录
     */
    @BindView(R.id.patient_count)
    protected TextView patient_count;
    /**
     * 病人首次就诊
     */
    @BindView(R.id.first_time)
    protected TextView first_time;
    @BindView(R.id.first_doctor)
    protected TextView first_doctor;
    /**
     * 病人最后一次就诊
     */
    @BindView(R.id.last_time)
    protected TextView last_time;
    @BindView(R.id.last_doctor)
    protected TextView last_doctor;


    private IManagementPresenter presenter;
    private OnFragmentInteractionListener mListener;

    private List<PatientItem> list = new ArrayList<PatientItem>();
    private List<TherapieItem> infolist = new ArrayList<TherapieItem>();
    private PatientAdapter patientAdapter;
    private Patient_Case_Collection_Adapter patientInfoAdapter;

    private Patient patient;


    private String token;
    private Doctor doctor;

    public ManagementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ManagementFragment.
     */
    public static ManagementFragment newInstance() {
        ManagementFragment fragment = new ManagementFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        doctor = getActivity().getIntent().getBundleExtra("doctor").getParcelable("doctor");
        token = (String) AppSharePreferenceMgr.get(mContext,"token","");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mCacheView == null) {
            mCacheView = inflater.inflate(R.layout.fragment_management, container, false);
        }
        ButterKnife.bind(this, mCacheView);
        ViewGroup parent = (ViewGroup) mCacheView.getParent();
        if (parent != null) {
            parent.removeView(mCacheView);
        }

        initView(mCacheView);
        presenter = new IManagementPresenter(mContext, new IManagementImpl(), this);
        presenter.setDoctorId(doctor.getDoctorId());
        presenter.setToken(token);
        return mCacheView;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            this.mListener = (OnFragmentInteractionListener) context;
            this.mContext = context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void initView(View view) {
        patient_add.setOnClickListener(this);
        intent_add_case.setOnClickListener(this);
        intent_see_patient.setOnClickListener(this);
        main_scanBtn.setOnClickListener(this);

        setPatientListView();
        setPatientInfoListView();

    }

    /**
     * 创建患者列表
     */
    private void  setPatientListView(){
        //创建LinearLayoutManager 对象 这里使用LinearLayoutManager 是线性布局的意思
        LinearLayoutManager layoutmanager = new LinearLayoutManager(mContext);
        //设置RecyclerView 布局
        patientListView.setLayoutManager(layoutmanager);
        patientListView.addItemDecoration(new SpacesItemDecoration(5));

        //设置Adapter
        patientAdapter = new PatientAdapter(mContext,list);

        patientAdapter.setOnItemClickListener(new PatientAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                presenter.getPatientInfo(list.get(data).getPatientId());
            }
        });
        patientListView.setAdapter(patientAdapter);

        //设置 Header
        refreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        //设置 Footer 为 球脉冲 样式
        refreshLayout.setRefreshFooter(new BallPulseFooter(mContext).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setEnableAutoLoadMore(true);
        refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDecider() {
            @Override
            public boolean canRefresh(View content) {
                if (patientListView == null) return false;
                if (patientListView.computeVerticalScrollOffset()==0)
                    return true;
                return false;
            }

            @Override
            public boolean canLoadMore(View content) {
                if (patientListView == null) return false;
                //获取recyclerView的高度
                patientListView.getHeight();
                //整个View控件的高度
                int scrollRange = patientListView.computeVerticalScrollRange();
                //当前屏幕之前滑过的距离
                int scrollOffset = patientListView.computeVerticalScrollOffset();
                //当前屏幕显示的区域高度
                int scrollExtent = patientListView.computeVerticalScrollExtent();
                int height = patientListView.getHeight();
                if(height>scrollRange){
                    return false;
                }
                if (scrollRange <=scrollOffset+scrollExtent){
                    return true;
                }
                return false;
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.refreshData();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                presenter.loadMoreData();
            }
        });
        refreshLayout.autoRefresh();
    }

    /**
     * 创建患者详情
     */
    private void setPatientInfoListView(){
        //创建LinearLayoutManager 对象 这里使用LinearLayoutManager 是线性布局的意思
        LinearLayoutManager layoutmanager = new LinearLayoutManager(mContext);
        //设置RecyclerView 布局
        patient_Case_Collection_ListView.setLayoutManager(layoutmanager);
        //设置Adapter
        patientInfoAdapter = new Patient_Case_Collection_Adapter(mContext,infolist);
        patientInfoAdapter.setOnItemClickListener(new PatientAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                if(view.getId()==R.id.patient_case_collection_button) presenter.seeDiagnosisInfo(infolist.get(data).getTherapyId());
            }
        });
        patient_Case_Collection_ListView.setAdapter(patientInfoAdapter);
    }
    /**
     * 患者分组
     */
    @Override
    public void group() {
        LogUtil.I(mContext, "group");
    }




    /**
     * 显示患者的信息
     * @param data   获取的患者详细数据
     */
    @Override
    public void showPatient(Object data) {
        LogUtil.I(mContext,data.toString());
        Gson gson=  new GsonBuilder()
                        //配置你的Gson
                        .setDateFormat("yyyy-MM-dd hh:mm:ss")
                        .create();
        PatientInfo patientInfo = new Gson().fromJson((JsonObject)data, PatientInfo.class);

        patient_name.setText(patientInfo.getPatientName());
        patient_count.setText(patientInfo.getTherapyCount()+"份诊疗记录");
        patient_sex.setText(patientInfo.getGender());
        patient_age.setText(patientInfo.getAge()+"岁");
        patient_native_place.setText(patientInfo.getAddress());
        patient_id.setText(Integer.toString(patientInfo.getPatientId()));
        patient_disease_state.setText(patientInfo.getLastState());
        first_time.setText(patientInfo.getFirstTherapyDate());
        first_doctor.setText(patientInfo.getFirstTherapyDoctor());
        last_time.setText(patientInfo.getLastTherapyDate());
        last_doctor.setText(patientInfo.getLastTherapyDoctor());
        patient = new Patient(  patientInfo.getPatientId(),
                                patientInfo.getPatientName(),
                                patientInfo.getGender(),
                                patientInfo.getMobPhone(),
                                patientInfo.getAge(),
                                patientInfo.getIdentityType(),
                                patientInfo.getIdentityNum(),
                                patientInfo.getAddress(),
                                patientInfo.getPlace(),
                                patientInfo.getAvatar());

        Glide.with(mContext)
                .load(patientInfo.getAvatar())
                .placeholder(R.drawable.main_person_image)//图片加载出来前，显示的图片
                .error(R.drawable.main_person_image)//图片加载失败后，显示的图片
                .into(patient_portrait);
        this.infolist.clear();
        if(patientInfo.getTherapies() !=null){
            this.infolist.addAll(patientInfo.getTherapies());
        }
        patientInfoAdapter.notifyDataSetChanged();
    }
    /**
     * 查看患者信息
     */
    @Override
    public void seePatientInfo() {
        if(patient == null){
            Toast.makeText(mContext,"请先选择一个患者",Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(this.mContext, SeePatientActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("patient",patient);
            bundle.putParcelable("doctor",doctor);
            intent.putExtra("bundle", bundle);
            startActivityForResult(intent, REQUEST_CODE.SEE_PATIENT_ACTIVITY);
        }

    }
    /**
     * 添加患者信息
     */
    @Override
    public void addPatientInfo() {
        Intent intent = new Intent(this.mContext, AddPatientActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("doctor",doctor);
        intent.putExtra("doctor", bundle);
        startActivityForResult(intent, REQUEST_CODE.ADD_PATIENT_ACTIVITY);
    }
    /**
     *添加患者病例
     * */
    @Override
    public void addDiagnosisInfo() {
        if(patient == null){
            Toast.makeText(mContext,"请先选择一个患者",Toast.LENGTH_LONG).show();
        }else{
            Intent intent = new Intent(this.mContext, AddPatientCaseActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("patient",patient);
            bundle.putParcelable("doctor",doctor);
            intent.putExtra("bundle", bundle);
            startActivityForResult(intent, REQUEST_CODE.ADD_PATIENT_CASE_ACTIVITY);
        }

    }
    /**
     * 查看患者病例
     * */
    @Override
    public void seeDiagnosisInfo(int therapyId) {
        Intent intent = new Intent(this.mContext, SeePatientCaseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("therapyId",therapyId);
        intent.putExtra("bundle", bundle);
        startActivityForResult(intent, REQUEST_CODE.SEE_PATIENT_CASE_ACTIVITY);
    }
    /**
     * 通过搜索框搜索患者
     */
    @Override
    public void searchPatient() {
        LogUtil.I(mContext, "searchPatient");
    }

    /**
     * 通过扫描二维码搜索患者
     */
    @Override
    public void searchPatientForZXing() {
        LogUtil.I(mContext, "searchPatientForZXing");
    }

    /**
     * 下拉刷新成功的操作
     */
    @Override
    public void refreshDataOnSuccess(Object data) {
        this.list.clear();
        LogUtil.I(mContext,data.toString());
        JsonArray array = new JsonParser().parse(data.toString()).getAsJsonArray();
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        for (final JsonElement elem : array) {
            list.add(gson.fromJson(elem, PatientItem.class));
        }
        patientAdapter.notifyDataSetChanged();
        refreshLayout.finishRefresh(true);
    }
    /**
     * 下拉刷新失败的操作
     */
    @Override
    public void refreshDataOnFailure(String error) {
        refreshLayout.finishRefresh(false);
    }
    /**
     * 上拉加载成功的操作
     */
    @Override
    public void loadMoreDataOnSuccess(Object data) {
        if(data ==null){
            refreshLayout.setEnableLoadMore(false);
        }else{


        int positionStart = this.list.size();
        JsonArray array = new JsonParser().parse(data.toString()).getAsJsonArray();
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        int itemCount = array.size();
        if(itemCount == 0){
            refreshLayout.setEnableLoadMore(false);
        }else {
            for (final JsonElement elem : array) {
                list.add(gson.fromJson(elem, PatientItem.class));
            }
            patientAdapter.notifyItemRangeInserted(positionStart,itemCount);
        }
        refreshLayout.finishLoadMore(true);//传入false表示加载失败
        }
    }
    /**
     * 上拉加载失败的操作
     */
    @Override
    public void loadMoreDataOnFailure(String error) {
        refreshLayout.finishLoadMore(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //添加患者
            case R.id.main_patient_add:
                presenter.addPatientInfo();
                break;
            //添加诊疗记录
            case R.id.intent_add_case:
                presenter.addDiagnosisInfo();
                break;
            //查看编辑患者信息
            case R.id.intent_see_patient:
                presenter.seePatientInfo();
                break;
            //通过二维码搜索患者
            case R.id.main_scanBtn:
                presenter.searchPatientForZXing();
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case REQUEST_CODE.ADD_PATIENT_ACTIVITY:
                    this.presenter.refreshData();
                    if(data.getIntExtra("patientId",-1) !=-1){
                        presenter.getPatientInfo(data.getIntExtra("patientId",-1));
                    }
                    break;
                case REQUEST_CODE.ADD_PATIENT_CASE_ACTIVITY:
                    this.presenter.refreshData();
                    if(data.getIntExtra("patientId",-1) !=-1){
                        presenter.getPatientInfo(data.getIntExtra("patientId",-1));
                    }
                    break;
                case REQUEST_CODE.SEE_PATIENT_ACTIVITY:
                    this.presenter.refreshData();
                    if(data.getIntExtra("patientId",-1) !=-1){
                        presenter.getPatientInfo(data.getIntExtra("patientId",-1));
                    }
                    break;
                case REQUEST_CODE.SEE_PATIENT_CASE_ACTIVITY:
                    this.presenter.refreshData();
                    if(data.getIntExtra("patientId",-1) !=-1){
                        presenter.getPatientInfo(data.getIntExtra("patientId",-1));
                    }
                    break;
            }
        }
    }
}
