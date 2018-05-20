package cn.android.a6doctors.util;

import android.content.Context;
import android.util.Log;

/**
 * Created by ChenTeacher on 2018/5/13.
 */

public class LogUtil {
    private static boolean isShow = true;
    public static void I(Context context,String msg){
        if(isShow){
            Log.i(context.getClass().getSimpleName(),msg);
        }
    }
    public static void E(Context context,String msg){
        if(isShow){
            Log.e(context.getClass().getSimpleName(),msg);
        }
    }
    public static void W(Context context,String msg){
        if(isShow){
            Log.w(context.getClass().getSimpleName(),msg);
        }
    }
    public static void D(Context context,String msg){
        if(isShow){
            Log.d(context.getClass().getSimpleName(),msg);
        }
    }
}
