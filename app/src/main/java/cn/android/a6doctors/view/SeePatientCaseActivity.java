package cn.android.a6doctors.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.DatePicker;
import cn.addapp.pickers.picker.SinglePicker;
import cn.android.a6doctors.R;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.bean.Therapy;
import cn.android.a6doctors.model.SeePatientCaseImpl;
import cn.android.a6doctors.presenter.SeePatientCasePresenter;
import cn.android.a6doctors.util.AppSharePreferenceMgr;
import cn.android.a6doctors.util.REQUEST_CODE;
import cn.android.a6doctors.util.WeiboDialogUtils;

/**
 * Created by ChenTeacher on 2017/12/4.
 */

public class SeePatientCaseActivity extends BaseActivity implements SeePatientCaseView, View.OnClickListener {
    @BindView(R.id.patient_name)
    TextView patientName;
    @BindView(R.id.patient_state)
    Button patientState;
    @BindView(R.id.patient_doctor)
    Button patientDoctor;
    @BindView(R.id.case_patient_time)
    Button casePatientTime;
    @BindView(R.id.case_patient_content)
    EditText casePatientContent;
    @BindView(R.id.gv)
    GridView gv;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.close_btn)
    ImageButton closeBtn;
    @BindView(R.id.see_photo)
    ImageButton seePhoto;

    private int columnWidth;
    private ArrayList<String> imagePaths = null;
    private GridAdapter gridAdapter;

    private ImageCaptureManager captureManager; // 相机拍照处理类
    private static final int REQUEST_CAMERA_CODE = 112;
    private static final int REQUEST_PREVIEW_CODE = 113;
    SeePatientCasePresenter presenter;

    private String token;
    private int therapyId;
    private Therapy therapy;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_case_patient);
        ButterKnife.bind(this);
        token = (String) AppSharePreferenceMgr.get(this, "token", "");
        therapyId = getIntent().getBundleExtra("bundle").getInt("therapyId");
        presenter = new SeePatientCasePresenter(new SeePatientCaseImpl(), this, this);
        presenter.getTherapy(token, therapyId);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void initView() {
        closeBtn.setOnClickListener(this);
        patientDoctor.setOnClickListener(this);
        patientState.setOnClickListener(this);
        casePatientTime.setOnClickListener(this);
        seePhoto.setOnClickListener(this);
        save.setOnClickListener(this);
        setImage();
        loadAdpater(new ArrayList<String>());
    }

    private void setImage() {
        //得到GridView中每个ImageView宽高
        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        gv.setNumColumns(cols);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int columnSpace = getResources().getDimensionPixelOffset(R.dimen.space_size);
        columnWidth = (screenWidth - columnSpace * (cols - 1)) / cols;
        //GridView item点击事件（浏览照片）
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == imagePaths.size()) {
                    add_case_patient_capture();
                } else {
                    PhotoPreviewIntent intent = new PhotoPreviewIntent(SeePatientCaseActivity.this);
                    intent.setCurrentItem(position);
                    intent.setPhotoPaths(imagePaths);
                    startActivityForResult(intent, REQUEST_PREVIEW_CODE);
                }

            }
        });
    }


    @Override
    public void onClick(View v) {
        Log.i("capture", "add_case_patient_capture");
        switch (v.getId()) {
            case R.id.close_btn:
                presenter.goBack();
                break;
            case R.id.patient_doctor:
                presenter.selectDoctor();
                break;
            case R.id.patient_state:
                presenter.selectState();
                break;
            case R.id.case_patient_time:
                presenter.selectTime();
                break;
            case R.id.save:
                mDialog = WeiboDialogUtils.createLoadingDialog(this, "正在修改");
                presenter.updateTherapy(token,
                        therapy.getTherapyId(),
                        imagePaths, therapy.getDoctorId(),
                        therapy.getPatientId(),
                        patientState.getText().toString(),
                        casePatientTime.getText().toString(),
                        casePatientContent.getText().toString());
                break;
            case R.id.see_photo:
                if(therapy.getPhotos() !=null&&therapy.getPhotos().size()>0){
                    Intent intent = new Intent(this,PhotoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("photos", (ArrayList<? extends Parcelable>) therapy.getPhotos());
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "暂无病例图集", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    private void add_case_patient_capture() {
        PhotoPickerIntent intent1 = new PhotoPickerIntent(SeePatientCaseActivity.this);
        intent1.setSelectModel(SelectModel.MULTI);
        intent1.setShowCarema(true); // 是否显示拍照
        intent1.setMaxTotal(4); // 最多选择照片数量，默认为9
        intent1.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
        startActivityForResult(intent1, REQUEST_CAMERA_CODE);
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

    private void loadAdpater(ArrayList<String> paths) {
        if (imagePaths == null) {
            imagePaths = new ArrayList<String>();
        }
        imagePaths.clear();
        imagePaths.addAll(paths);
        if (gridAdapter == null) {
            gridAdapter = new GridAdapter(imagePaths);
            gv.setAdapter(gridAdapter);
        } else {
            gridAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public void selectState() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("首次面诊");
        list.add("复诊");
        list.add("住院");
        list.add("出院");
        list.add("回访");
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
                patientState.setText(item);
            }
        });
        picker.show();
    }

    @Override
    public void selectDoctor() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("王医师");
        list.add("李医师");
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
                patientDoctor.setText(item);
            }
        });
        picker.show();
    }

    @Override
    public void selectTime() {
        Calendar calendar = Calendar.getInstance();
        final DatePicker picker = new DatePicker(this);
        picker.setCanLoop(false);
        picker.setWheelModeEnable(true);
        picker.setTopPadding(15);
        picker.setRangeStart(1900, 1, 1);
        picker.setRangeEnd(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));
        picker.setSelectedItem(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));
        picker.setWeightEnable(true);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                casePatientTime.setText(year + "-" + month + "-" + day);
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }


    @Override
    public void save() {

    }

    @Override
    public void saveOnSuccess() {
        WeiboDialogUtils.closeDialog(mDialog);
        Intent intent = new Intent();
        intent.putExtra("patientId", therapy.getPatientId());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void saveOnFailure() {
        Toast.makeText(this, "修改异常,请重新添加", Toast.LENGTH_LONG).show();
        WeiboDialogUtils.closeDialog(mDialog);
    }


    @Override
    public void getTherapy(Object data) {
        Gson gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        therapy = gson.fromJson((JsonObject) data, Therapy.class);
        patientName.setText(therapy.getPatientName());
        patientState.setText(therapy.getState());
        casePatientContent.setText(therapy.getRecord());
        casePatientTime.setText(therapy.getDate());
    }

    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
        }

        @Override
        public int getCount() {
            if (listUrls.size() == 4) {
                return listUrls.size();
            }
            return listUrls.size() + 1;
        }

        @Override
        public String getItem(int position) {
            if (listUrls.size() == 4) {
                return listUrls.get(position);
            } else {
                if (position == (listUrls.size())) {
                    return null;
                } else {
                    return listUrls.get(position);
                }

            }
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.item_image, null);
                imageView = (ImageView) convertView.findViewById(R.id.add_case_patient_photo);
                convertView.setTag(imageView);
            } else {
                imageView = (ImageView) convertView.getTag();
            }

            if (getItem(position) != null) {
                Glide.with(SeePatientCaseActivity.this)
                        .load(new File(getItem(position)))
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(imageView);
            } else {
                Glide.with(SeePatientCaseActivity.this)
                        .load(R.drawable.add_image)
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(imageView);
            }
            return convertView;
        }
    }
}