package cn.android.a6doctors.API;



import android.support.annotation.Nullable;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ChenTeacher on 2018-05-15.
 */

public interface APIService {
        /**
         * 登录app
         * @param account 账号
         * @param password 密码
         * @return
         */
        @FormUrlEncoded
        @POST("api/login?")
        Call<ResponseBody> login(@Field("account") String account,
                                 @Field("password") String password);

        /**
         * 添加患者
         * @param params
         * @param file
         * @return
         */
        @Multipart
        @POST("api/createPatient?")
        Call<ResponseBody> createPatient(@QueryMap Map<String,Object> params,@Nullable @Part MultipartBody.Part file);
        /**
         * 获取患者的详情
         * */
        @GET("api/getPatient?")
        Call<ResponseBody> getPatient(@Query("token")String token,@Query("patientId")int patientId);
        /**
         * 查询所有的患者
         * @param token
         * @param doctorId 医生的id
         * @return
         */
        @GET("api/getPatients?")
        Call<ResponseBody> getPatients(@Query("token")String token,
                                       @Query("doctorId") int doctorId);

        /**
         * 获取患者详情
         *
         */
        @GET("api/getPatientInfo?")
        Call<ResponseBody> getPatientInfo(@Query("token")String token,@Query("patientId")int patientId);
        /**
         * 更新患者信息
         */
        @Multipart
        @POST("/api/updatePatient?")
        Call<ResponseBody> updatePatient(@QueryMap Map<String,Object> params,@Nullable @Part MultipartBody.Part file);
        /**
         * 创建病例
         *
         * @param params
         * @param files
         */
        @Multipart
        @POST("/api/createTherapy?")
        Call<ResponseBody> createTherapy(@QueryMap Map<String,Object> params,@Nullable @PartMap Map<String, RequestBody> files);
        /**
         * 获取病例列表
         * @param token
         * @param patientId
         */
        @GET("api/getTherapies?")
        Call<ResponseBody> getTherapies(@Query("token")String token,@Query("patientId")int patientId);
        /**
         *  获取病例
         * @param token
         * @param therapyId
         */
        @GET("api/getTherapy?")
        Call<ResponseBody> getTherapy(@Query("token")String token,@Query("therapyId")int therapyId);

        /**
         * 更新病例
         * @param params
         * @param files
         */
        @Multipart
        @POST("/api/updateTherapy?")
        Call<ResponseBody> updateTherapy(@QueryMap Map<String,Object> params,@Nullable @PartMap Map<String, RequestBody> files);



}
