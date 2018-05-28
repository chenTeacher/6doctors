package cn.android.a6doctors.API;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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
         * 查询所有的患者
         * @param token
         * @param doctorId 医生的id
         * @return
         */
        @GET("api/getPatients?")
        Call<ResponseBody> getPatients(@Query("token")String token,
                                      @Query("doctorId") int doctorId);
        @Multipart
        @POST("api/createPatient?")
        Call<ResponseBody> createPatient(@QueryMap Map<String,Object> params, @Part MultipartBody.Part file);
//        @GET("api/getPatientList.do?")
//        Call<ResponseBody> getPatientList(@Query("doctorId") String doctorId);
//
//        @GET("service/getPatientInfo.do?")
//        Call<ResponseBody> getPatientInfo(@Query("doctorId") String doctorId);

}
