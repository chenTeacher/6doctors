package cn.android.a6doctors.factory;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.android.a6doctors.API.APIService;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;



/**
 * Created by ChenTeacher on 2018-05-15.
 */

public class APIFactoryImpl implements APIFactory {
    private Retrofit retrofit;
    private Gson gson;
    private APIService apiService;
    private static String baseUrl = "http://192.168.199.196:8080/comet/";

    public APIFactoryImpl() {
        gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(APIService.class);
    }

    public static APIFactoryImpl getInstance() {
        return new APIFactoryImpl();
    }


    @Override
    public void getPatientList(String doctorId,final Patient.CallBack callBack) {
        Call<ResponseBody> call = apiService.getPatientList(doctorId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    List<Patient> list = new ArrayList<Patient>();
                    JsonArray array = new JsonParser().parse(response.body().string()).getAsJsonArray();
                    for (final JsonElement elem : array) {
                        list.add(gson.fromJson(elem, Patient.class));
                    }
                    callBack.onSuccess(list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                    callBack.onFailure("");
            }
        });
    }



    @Override
    public void getPatientInfo(String patientId, final Patient_Case.CallBack callBack) {
        Call<ResponseBody> call = apiService.getPatientInfo(patientId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Patient_Case patient_case = new Patient_Case();
                    JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
                    patient_case = gson.fromJson(jsonObject,Patient_Case.class);

                    callBack.onSuccess(patient_case);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onFailure("");
            }
        });
    }
}
