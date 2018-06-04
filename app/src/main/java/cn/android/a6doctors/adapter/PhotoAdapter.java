package cn.android.a6doctors.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.R;
import cn.android.a6doctors.bean.Photo;

/**
 * Created by ChenTeacher on 2018/6/4.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private List<Photo> data;
    private Context mContext;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.photo_item)
        ImageView photoItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public PhotoAdapter(List<Photo> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext)
                .load(data.get(position).getCasePath())
                .placeholder(R.drawable.main_person_image)//图片加载出来前，显示的图片
                .error(R.drawable.main_person_image)//图片加载失败后，显示的图片
                .into(holder.photoItem);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);

    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
