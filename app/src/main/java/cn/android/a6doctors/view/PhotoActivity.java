package cn.android.a6doctors.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.R;
import cn.android.a6doctors.adapter.PhotoAdapter;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.bean.Photo;

public class PhotoActivity extends BaseActivity implements View.OnClickListener,PhotoView{
    @BindView(R.id.close_btn)
    ImageButton closeBtn;
    @BindView(R.id.photo_recycler)
    RecyclerView photoRecycler;
    @BindView(R.id.photo_info)
    uk.co.senab.photoview.PhotoView photoInfo;

    private List<Photo> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        photos = (List<Photo>) getIntent().getBundleExtra("bundle").get("photos");
        initView();
    }

    @Override
    public void initView() {
        closeBtn.setOnClickListener(this);
        setPhotoRecycler();
    }

    private void  setPhotoRecycler(){
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        layoutmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置RecyclerView 布局
        photoRecycler.setLayoutManager(layoutmanager);
        //设置Adapter
        PhotoAdapter adapter = new PhotoAdapter(photos,this);
        photoRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new PhotoAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                photos.get(data).getCasePath();
                Glide.with(PhotoActivity.this)
                        .load(photos.get(data).getCasePath())
                        .placeholder(R.mipmap.default_error)//图片加载出来前，显示的图片
                        .error(R.mipmap.default_error)//图片加载失败后，显示的图片
                        .into(photoInfo);
            }
        });
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.close_btn:
                this.goBack();
                break;
        }
    }

    @Override
    public void goBack() {
        finish();
    }

    /**
     * 手指按下时的坐标
     */
    float downX, downY;

    /**
     * 手机屏幕的宽度和高度
     */
    float screenWidth, screenHeight;

}

