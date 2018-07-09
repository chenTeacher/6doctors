package cn.android.a6doctors.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.foamtrace.photopicker.ImageCaptureManager;
import com.foamtrace.photopicker.PhotoPickerActivity;
import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.SelectModel;
import com.foamtrace.photopicker.intent.PhotoPickerIntent;
import com.foamtrace.photopicker.intent.PhotoPreviewIntent;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.addapp.pickers.entity.City;
import cn.addapp.pickers.entity.County;
import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.NumberPicker;
import cn.addapp.pickers.picker.SinglePicker;
import cn.android.a6doctors.R;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.model.SeePatientImpl;
import cn.android.a6doctors.presenter.AddPatientPresenter;
import cn.android.a6doctors.presenter.SeePatientPresenter;
import cn.android.a6doctors.util.AddressPickTask;
import cn.android.a6doctors.util.AppSharePreferenceMgr;
import cn.android.a6doctors.util.LogUtil;
import cn.android.a6doctors.util.RESULT_CODE;
import cn.android.a6doctors.util.WeiboDialogUtils;

/**
 * Created by ChenTeacher on 2017/12/4.
 */

public class SeePatientActivity extends BaseActivity implements SeePatientView, View.OnClickListener {
    @BindView(R.id.close_btn)
    ImageButton closeBtn;
    @BindView(R.id.patient_name)
    EditText patientName;
    @BindView(R.id.patient_sex)
    Button patientSex;
    @BindView(R.id.patient_phone)
    EditText patientPhone;
    @BindView(R.id.patient_age)
    Button patientAge;
    @BindView(R.id.patient_card)
    EditText patientCard;
    @BindView(R.id.patient_select_local_btn)
    Button patientSelectLocalBtn;
    @BindView(R.id.patient_local)
    TextView patientLocal;
    @BindView(R.id.patient_local_info)
    EditText patientLocalInfo;
    @BindView(R.id.patient_portrait)
    ImageView patientPortrait;
    @BindView(R.id.add_patient_portrait_btn)
    Button addPatientPortraitBtn;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.identityType)
    Button identityType;

    private SeePatientPresenter presenter;


    private ArrayList<String> imagePaths = null;
    private static final int REQUEST_CAMERA_CODE = 116;
    private static final int REQUEST_PREVIEW_CODE = 117;
    private ImageCaptureManager captureManager; // 相机拍照处理类

    private String token;
    private Patient patient;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_patient);
        ButterKnife.bind(this);
        presenter = new SeePatientPresenter(new SeePatientImpl(), this, this);
        token = (String) AppSharePreferenceMgr.get(this,"token","");
        patient = getIntent().getBundleExtra("bundle").getParcelable("patient");
        initView();
        presenter.getPatient(token,patient.getPatientId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    /*初始化组件*/
    @Override
    public void initView() {

        closeBtn.setOnClickListener(this);
        patientAge.setOnClickListener(this);
        patientSex.setOnClickListener(this);
        addPatientPortraitBtn.setOnClickListener(this);
        patientSelectLocalBtn.setOnClickListener(this);
        save.setOnClickListener(this);
        identityType.setOnClickListener(this);



    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void selectSex() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("男");
        list.add("女");
        SinglePicker<String> picker = new SinglePicker<String>(this, list);
        picker.setCanLoop(false);//不禁用循环
        picker.setLineVisible(true);
        picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setSelectedIndex(0);
        picker.setWheelModeEnable(true);
        //启用权重 setWeightWidth 才起作用
        picker.setLabel("");
        picker.setWeightEnable(true);
        picker.setWeightWidth(1);
        picker.setSelectedTextColor(0xFF279BAA);//前四位值是透明度
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setOnSingleWheelListener(new OnSingleWheelListener() {
            @Override
            public void onWheeled(int index, String item) {
            }
        });
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                patientSex.setText(item);
                patient.setGender(item);
            }
        });
        picker.show();
    }

    @Override
    public void selectAge() {
        NumberPicker picker = new NumberPicker(this);
        picker.setWidth(picker.getScreenWidthPixels());
        picker.setCanLoop(false);
        picker.setLineVisible(false);
        picker.setWheelModeEnable(true);
        picker.setOffset(2);//偏移量
        picker.setRange(1, 200, 1);//数字范围
        picker.setSelectedItem(1);
        picker.setLabel("岁");
        picker.setOnNumberPickListener(new NumberPicker.OnNumberPickListener() {
            @Override
            public void onNumberPicked(int index, Number item) {
//                showToast("index=" + index + ", item=" + item.intValue());
                patientAge.setText(Integer.toString(item.intValue()));
                patient.setAge(item.intValue());

            }
        });
        picker.show();
    }

    @Override
    public void selectAddress() {
        AddressPickTask task = new AddressPickTask(this);
        task.setHideProvince(false);
        task.setHideCounty(false);
        task.setCallback(new AddressPickTask.Callback() {
            @Override
            public void onAddressInitFailed() {

                Toast.makeText(getApplication(), "数据初始化失败", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAddressPicked(Province province, City city, County county) {
                String s = "";
                if (county == null) {
                    s = province.getAreaName() + city.getAreaName();
                } else {
                    s = province.getAreaName() + city.getAreaName() + county.getAreaName();
                }
//                Toast.makeText(getApplication(), s, Toast.LENGTH_LONG).show();
                patientLocal.setText(s);
                patient.setAddress(s);
            }
        });
        task.execute("北京", "北京", "东城");
    }

    @Override
    public void selectIdentityType() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("身份证");
        SinglePicker<String> picker = new SinglePicker<String>(this, list);
        picker.setCanLoop(false);//不禁用循环
        picker.setLineVisible(true);
        picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setSelectedIndex(0);
        picker.setWheelModeEnable(true);
        //启用权重 setWeightWidth 才起作用
        picker.setLabel("");
        picker.setWeightEnable(true);
        picker.setWeightWidth(1);
        picker.setSelectedTextColor(0xFF279BAA);//前四位值是透明度
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setOnSingleWheelListener(new OnSingleWheelListener() {
            @Override
            public void onWheeled(int index, String item) {
            }
        });
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                identityType.setText(item);
                patient.setIdentityType(item);
            }
        });
        picker.show();
    }

    @Override
    public void photoSelector() {
        if(imagePaths == null){
            imagePaths = new ArrayList<String>();
        }
        if (imagePaths.size()==0) {
            add_patient_capture();
        } else {
            PhotoPreviewIntent intent = new PhotoPreviewIntent(SeePatientActivity.this);
            intent.setCurrentItem(0);
            intent.setPhotoPaths(imagePaths);
            startActivityForResult(intent, REQUEST_PREVIEW_CODE);
        }
    }

    @Override
    public void saveOnSuccess() {
        WeiboDialogUtils.closeDialog(mDialog);
        Intent intent = new Intent();
        intent.putExtra("patientId",patient.getPatientId());
        setResult(RESULT_OK,intent );
        finish();
    }

    @Override
    public void saveOnFailure() {
        Toast.makeText(this,"修改异常,请重新添加",Toast.LENGTH_LONG).show();
        WeiboDialogUtils.closeDialog(mDialog);
    }

    @Override
    public void getPatient(Patient patient) {
        this.patient = patient;
        patientName.setText(patient.getPatientName());
        patientAge.setText(patient.getAge().toString());
        patientSex.setText(patient.getGender());
        patientPhone.setText(patient.getMobPhone());
        patientCard.setText(patient.getIdentityNum());
        identityType.setText(patient.getIdentityType());
        patientAge.setText(patient.getAge().toString());
        patientLocal.setText(patient.getAddress());
        patientLocalInfo.setText(patient.getPlace());
        Glide.with(SeePatientActivity.this)
                .load(patient.getPhotoPath())
                .placeholder(R.drawable.main_person_image)
                .error(R.drawable.main_person_image)
                .centerCrop()
                .crossFade()
                .into(patientPortrait);
    }

    private void add_patient_capture() {
        PhotoPickerIntent intent1 = new PhotoPickerIntent(SeePatientActivity.this);
        intent1.setSelectModel(SelectModel.MULTI);
        intent1.setShowCarema(true); // 是否显示拍照
        intent1.setMaxTotal(1); // 最多选择照片数量，默认为9
        intent1.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
        startActivityForResult(intent1, REQUEST_CAMERA_CODE);
    }
    private void loadAdpater(ArrayList<String> paths) {
        if (imagePaths == null) {
            imagePaths = new ArrayList<String>();
        }
        imagePaths.clear();
        imagePaths.addAll(paths);
        patient.setPhotoPath("");
        File file =null;
        if (imagePaths.size()>0){
            file = new File(imagePaths.get(0));
            patient.setPhotoPath(imagePaths.get(0));
        }
        Glide.with(SeePatientActivity.this)
                .load(file)
                .placeholder(R.drawable.main_person_image)
                .error(R.drawable.main_person_image)
                .centerCrop()
                .crossFade()
                .into(patientPortrait);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close_btn:
                presenter.goBack();
                break;
            case R.id.patient_age:
                presenter.selectAge();
                break;
            case R.id.patient_sex:
                presenter.selectSex();
                break;
            case R.id.patient_select_local_btn:
                presenter.selectAddress();
                break;
            case R.id.add_patient_portrait_btn:
                presenter.photoSelector();
                break;
            case R.id.identityType:
                presenter.selectIdentityType();
                break;
            case R.id.save:

                patient.setPatientName(patientName.getText().toString());
                patient.setMobPhone(patientPhone.getText().toString());
                patient.setIdentityNum(patientCard.getText().toString());
                patient.setPlace(patientLocalInfo.getText().toString());
                if(patient.hasNull()==null){
                    mDialog = WeiboDialogUtils.createLoadingDialog(this, "正在修改");
                    presenter.save(patient.getPhotoPath(),token,patient.getPatientId(),patient.getPatientName(),patient.getGender(),patient.getMobPhone(),patient.getAge(),patient.getIdentityType(),patient.getIdentityNum(),patient.getAddress(),patient.getPlace());
                }else {
                    Toast.makeText(this,patient.hasNull(),Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                // 选择照片
                case REQUEST_CAMERA_CODE:
                    loadAdpater(data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT));
                    break;
                //浏览照片
                case REQUEST_PREVIEW_CODE:
                    loadAdpater(data.getStringArrayListExtra(PhotoPreviewActivity.EXTRA_RESULT));
                    break;
                // 调用相机拍照
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
                    if (captureManager.getCurrentPhotoPath() != null) {
                        captureManager.galleryAddPic();
                        ArrayList<String> paths = new ArrayList<String>();
                        paths.add(captureManager.getCurrentPhotoPath());
                        loadAdpater(paths);
                    }
                    break;

            }
        }
    }
}
