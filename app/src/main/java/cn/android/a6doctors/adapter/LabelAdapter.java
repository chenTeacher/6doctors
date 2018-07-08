package cn.android.a6doctors.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.API.DoctorSrevice;
import cn.android.a6doctors.R;
import cn.android.a6doctors.bean.Label;
import cn.android.a6doctors.view.label.LabelActivity;

/**
 * Created by ChenTeacher on 2018/7/7.
 */

public class LabelAdapter extends RecyclerView.Adapter<LabelAdapter.ViewHolder> implements ItemTouchHelperAdapter {


    private List<Label> data;
    private Context mContext;
    private LabelAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public LabelAdapter(Context context, List<Label> data) {
        this.mContext = context;
        this.data = data;

    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

//        Collections.swap(data,fromPosition,toPosition);
//        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDissmiss(int position) {
        if(mOnItemClickListener != null){
            mOnItemClickListener.delLabel(position);
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.label_name)
        public TextView labelName;
        @BindView(R.id.tv_text)
        public TextView tvText;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_label, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.labelName.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);
        void delLabel(int position);

    }


    public void setOnItemClickListener(LabelAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}

interface ItemTouchHelperAdapter {
    //数据交换
    void onItemMove(int fromPosition, int toPosition);

    //数据删除
    void onItemDissmiss(int position);
}