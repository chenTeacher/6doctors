package cn.android.a6doctors.API;

import android.support.annotation.Nullable;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;

/**
 * Created by ChenTeacher on 2018/7/7.
 */

public interface DoctorSrevice {
    /**
     * 更新病例
     * @param params
     */
    @POST("/doctor/findLabelsByDoctorId?")
    Call<ResponseBody> findLabelsByDoctorId(@QueryMap Map<String,Object> params);
    /**
     * 更新病例
     * @param params
     */
    @POST("/doctor/addLabel?")
    Call<ResponseBody> addLabel(@QueryMap Map<String,Object> params);
}
