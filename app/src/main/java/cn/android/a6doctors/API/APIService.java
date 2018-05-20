package cn.android.a6doctors.API;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ChenTeacher on 2018-05-15.
 */

public interface APIService {
        @GET("service/getPatientList.do?")
        Call<ResponseBody> getPatientList(@Query("doctorId") String doctorId);

        @GET("service/getPatientInfo.do?")
        Call<ResponseBody> getPatientInfo(@Query("doctorId") String doctorId);

}
