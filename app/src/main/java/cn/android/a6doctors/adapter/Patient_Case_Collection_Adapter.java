package cn.android.a6doctors.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.android.a6doctors.R;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case_Collection;

/**
 * Created by ChenTeacher on 2018/5/13.
 */

public class Patient_Case_Collection_Adapter extends RecyclerView.Adapter<Patient_Case_Collection_Adapter.ViewHolder>{

    private int resourceId;
    private List<Patient_Case_Collection> data;
    private Context mContext;
    private PatientAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public Patient_Case_Collection_Adapter(Context context, List<Patient_Case_Collection> data){
        this.mContext = context;
        this.data = data;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.patient_case_collection_button)
        ImageButton patient_case_collection_button;
        @BindView(R.id.patient_case_collection_time)
        TextView patient_case_collection_time;
        @BindView(R.id.patient_case_collection_number)
        TextView patient_case_collection_number;
        @BindView(R.id.patient_case_collection_doctor)
        TextView patient_case_collection_doctor;
        @BindView(R.id.patient_case_collection_content)
        TextView patient_case_collection_cotent;
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
    public Patient_Case_Collection_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_patient_case_collection_item,parent,false);
        Patient_Case_Collection_Adapter.ViewHolder holder = new Patient_Case_Collection_Adapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Patient_Case_Collection_Adapter.ViewHolder holder, final int position) {

        Patient_Case_Collection patient_case_collection = data.get(position);
        holder.patient_case_collection_doctor.setText(patient_case_collection.getDoctor());
        holder.patient_case_collection_time.setText(patient_case_collection.getTime());
        holder.patient_case_collection_number.setText(String.valueOf(position+1));
        holder.patient_case_collection_cotent.setText(patient_case_collection.getDescribe());
        holder.patient_case_collection_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(view, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);

    }

    public void setOnItemClickListener(PatientAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}