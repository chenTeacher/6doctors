package cn.android.a6doctors.factory;


import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.android.a6doctors.API.APIService;
import cn.android.a6doctors.BuildConfig;
import cn.android.a6doctors.bean.Doctor;
import cn.android.a6doctors.bean.Patient;
import cn.android.a6doctors.bean.Patient_Case;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.util.LogUtil;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
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
    private static String baseUrl = "http://api.6doctors.cn/";

    public APIFactoryImpl() {
        gson = new GsonBuilder()
                //配置你的Gson
                .setDateFormat("yyyy-MM-dd hh:mm:ss")
                .create();

        OkHttpClient httpClient = new OkHttpClient();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
        apiService = retrofit.create(APIService.class);
    }

    public static APIFactoryImpl getInstance() {
        return new APIFactoryImpl();
    }


    @Override
    public void login(String account, String password, final CallBack callBack) {
        Call<ResponseBody> call = apiService.login(account, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    int code = response.code();
                    if (code == 200) {
                        JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
                        int status = jsonObject.get("status").getAsInt();
                        String msg = jsonObject.get("msg").getAsString();
                        JsonObject data = jsonObject.get("data").getAsJsonObject();
                        if (status == 200) {
                            callBack.onSuccess(data);
                        }
                        if (status == 401) {
                            //账号密码错误
                            callBack.onFailure(msg);
                        }

                    }else if(code ==404){
                        callBack.onFailure("账号不存在");

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    callBack.onComplete();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callBack.onError();
                callBack.onComplete();
            }

        });
    }

    @Override
    public void getPatientList(String token , int doctorId, final CallBack callBack) {
        Call<ResponseBody> call = apiService.getPatients(token,doctorId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    int code = response.code();
                    if (code == 200) {
                        JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
                        int status = jsonObject.get("status").getAsInt();
                        String msg = jsonObject.get("msg").getAsString();
                        JsonArray data = jsonObject.get("data").getAsJsonArray();
                        if (status == 200) {
                            callBack.onSuccess(data);
                        }else{
                            callBack.onFailure(msg);
                        }

                    }else if(code ==404){
                        callBack.onFailure("请求异常");
                    }
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
//        Call<ResponseBody> call = apiService.getPatientInfo(patientId);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    Patient_Case patient_case = new Patient_Case();
//                    JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();
//                    patient_case = gson.fromJson(jsonObject, Patient_Case.class);
//
//                    callBack.onSuccess(patient_case);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                callBack.onFailure("");
//            }
//        });
    }

    @Override
    public void createPatient(Doctor doctor, String token, Patient patient) {
        doctor.getDoctorId();
        String path =patient.getPhotoPath(); // "/storage/emulated/0/Pictures/1477553156332.jpg";
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("img", file.getName(), requestFile);

        Map<String,Object> params = new HashMap<String,Object>();
        params.put("token",token);
        params.put("doctorId",doctor.getDoctorId());
        params.put("patientName",patient.getPatientName() );
        params.put("gender",patient.getGender());
        params.put("mobPhone",patient.getMobPhone());
        params.put("age", patient.getAge());
        params.put("identityType",patient.getIdentityType());
        params.put("identityNum",patient.getIdentityNum());
        params.put("address",patient.getAddress());
        params.put("place",patient.getPlace());
        Call<ResponseBody> call = apiService.createPatient(params, body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    System.out.println(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
