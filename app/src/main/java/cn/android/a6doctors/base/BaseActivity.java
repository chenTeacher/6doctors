package cn.android.a6doctors.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by ChenTeacher on 2018/5/13.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 判断是否登录
    }

    public abstract void initView();
//    public static Boolean hideInputMethod(Context context, View v) {
//        InputMethodManager imm = (InputMethodManager) context
//                .getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm != null) {
//            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
//        }
//        return false;
//    }
//    public static boolean isShouldHideInput(View v, MotionEvent event) {
//        if (v != null && (v instanceof EditText)) {
//                v.setFocusable(false);
//                v.setFocusableInTouchMode(true);
//                int[] leftTop = { 0, 0 };
//                v.getLocationInWindow(leftTop);
//                int left = leftTop[0], top = leftTop[1], bottom = top + v.getHeight(), right = left
//                        + v.getWidth();
//                if (event.getX() > left && event.getX() < right
//                        && event.getY() > top && event.getY() < bottom) {
//                    // 保留点击EditText的事件
//                    return false;
//                } else {
//                    return true;
//                }
//
//
//        }
//        return false;
//    }
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
//            View v = getCurrentFocus();
//                if (isShouldHideInput(v, ev)) {
//                    if(hideInputMethod(this, v)) {
//                        return true; //隐藏键盘时，其他控件不响应点击事件==》注释则不拦截点击事件
//                    }
//                }
//        }
//        return super.dispatchTouchEvent(ev);
//    }

}
