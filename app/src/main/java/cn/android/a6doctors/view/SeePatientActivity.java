package cn.android.a6doctors.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.model.SeePatientImpl;
import cn.android.a6doctors.presenter.SeePatientPresenter;
import cn.android.a6doctors.util.AddressPickTask;

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

    private SeePatientPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_patient);
        ButterKnife.bind(this);
        presenter = new SeePatientPresenter(new SeePatientImpl(), this, this);
        initView();
    }

    /*初始化组件*/
    @Override
    public void initView() {
        closeBtn.setOnClickListener(this);
        patientAge.setOnClickListener(this);
        patientSex.setOnClickListener(this);
        patientSelectLocalBtn.setOnClickListener(this);
    }

    /**
     * 关闭activity
     */
    private void closeActivty() {
        finish();
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
                Toast.makeText(getApplication(), s, Toast.LENGTH_LONG).show();
                patientLocal.setText(s);
            }
        });
        task.execute("北京", "北京", "东城");
    }

    @Override
    public void photoSelector() {

    }

    @Override
    public void save() {

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
            case R.id.save:
                presenter.save(new Patient());
                break;
        }
    }
}
