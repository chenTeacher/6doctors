package cn.android.a6doctors.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.R;
import cn.android.a6doctors.bean.Patient;

/**
 * Created by ChenTeacher on 2018/5/13.
 */

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.ViewHolder> {
    private int resourceId;
    private List<Patient> data;
    private Context mContext;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public PatientAdapter(Context context, List<Patient> data) {
        this.mContext = context;
        this.data = data;
//        this.mOnItemClickListener = mListener;

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.patient_disease_state)
        TextView patient_disease_state;
        @BindView(R.id.patient_intention)
        TextView patient_intention;
        @BindView(R.id.patient_name)
        TextView patient_name;
        @BindView(R.id.person_image)
        ImageView personImage;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.patient_name.setText(data.get(position).getPatient_name());
        holder.patient_disease_state.setText(data.get(position).getPatient_disease()+"-"+data.get(position).getPatient_state());
        holder.patient_intention.setText(data.get(position).getPatient_intention());
        Glide.with(mContext)
                .load(data.get(position).getPatient_portrait())
                .placeholder(R.drawable.main_person_image)//图片加载出来前，显示的图片
                .error(R.drawable.main_person_image)//图片加载失败后，显示的图片
                .into(holder.personImage);
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
