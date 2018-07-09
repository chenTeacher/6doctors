package cn.android.a6doctors.API;

import android.support.annotation.Nullable;

import java.util.List;
import java.util.Map;


import okhttp3.ResponseBody;
import retrofit2.Call;

import retrofit2.http.POST;

import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ChenTeacher on 2018/7/7.
 */

public interface DoctorSrevice {
    /**
     * 查询分组
     * @param params
     */
    @POST("/doctor/findLabelsByDoctorId?")
    Call<ResponseBody> findLabelsByDoctorId(@QueryMap Map<String,Object> params);
    /**
     * 添加分组
     * @param params
     */
    @POST("/doctor/addLabel?")
    Call<ResponseBody> addLabel(@QueryMap Map<String,Object> params);

    /**
     * 删除分组
     * @param params
     */
    @POST("/doctor/delLabel?")
    Call<ResponseBody> delLabel(@QueryMap Map<String,Object> params);
    /**
     * 删除分组
     * @param params
     */
    @POST("/doctor/findByLabelId?")
    Call<ResponseBody> findByLabelId(@QueryMap Map<String,Object> params);
    /**
     * 给病人添加已经存在标签（分组）
     */
    @POST("/doctor/addLabelPatients?")
    Call<ResponseBody> addLabelPatients(@Query("token") String token,@Query("labelId") int labelId,  @Query("patientsId")List<Integer> patientsId);
    /**
     * 移除患者
     */
    @POST("/doctor/delLabelPatient?")
    Call<ResponseBody> delLabelPatient(@QueryMap Map<String,Object> params);

    /**
     * 修改标签名称
     */
    @POST("/doctor/updateLabel?")
    Call<ResponseBody> updateLabel(@QueryMap Map<String,Object> params);
}
