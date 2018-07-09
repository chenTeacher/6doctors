package cn.android.a6doctors.adapter;

/**
 * Created by ChenTeacher on 2018/7/9.
 */

public interface ItemTouchHelperAdapter {
        //数据交换
        void onItemMove(int fromPosition, int toPosition);

        //数据删除
        void onItemDissmiss(int position);
}
