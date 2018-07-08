package cn.android.a6doctors.view;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.R;
import cn.android.a6doctors.adapter.PhotoAdapter;
import cn.android.a6doctors.base.BaseActivity;
import cn.android.a6doctors.bean.Photo;
import cn.android.a6doctors.ui.PhotoViewPager;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoActivity extends BaseActivity implements View.OnClickListener, PhotoActivityView {
    @BindView(R.id.close_btn)
    ImageButton closeBtn;
    @BindView(R.id.photo_recycler)
    RecyclerView photoRecycler;

    @BindView(R.id.viewPager)
    PhotoViewPager viewPager;

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
        viewPager.setAdapter(new MyAdapter());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {

                int childCount = viewPager.getChildCount();//viewPager得到页面的数量

                // 遍历当前所有加载过的PhotoView，恢复所有图片的默认状态

                for (int i = 0; i < childCount; i++) {
                    View childAt = viewPager.getChildAt(i);

                    try {
                        if (childAt != null && childAt instanceof PhotoView) {
                            PhotoView  photoView = (PhotoView) childAt;//得到viewPager里面的页面
                            PhotoViewAttacher mAttacher = new PhotoViewAttacher(photoView);//把得到的photoView放到这个负责变形的类当中
//                            mAttacher.setScaleType(ImageView.ScaleType.FIT_XY);//设置充满全屏
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }


    private void setPhotoRecycler() {
        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        layoutmanager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //设置RecyclerView 布局
        photoRecycler.setLayoutManager(layoutmanager);
        //设置Adapter
        PhotoAdapter adapter = new PhotoAdapter(photos, this);
        photoRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new PhotoAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                viewPager.setCurrentItem(data);
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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
     * 自定义pagerAdapter
     */
    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return photos.size();
        }
        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            Glide.with(PhotoActivity.this)
                    .load(photos.get(position).getCasePath())
                    .placeholder(R.mipmap.default_error)//图片加载出来前，显示的图片
                    .error(R.mipmap.default_error)//图片加载失败后，显示的图片
                    .into(photoView);
//            photoView.setScaleType(ImageView.ScaleType.FIT_XY);//设置图片显示为充满全屏
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}

