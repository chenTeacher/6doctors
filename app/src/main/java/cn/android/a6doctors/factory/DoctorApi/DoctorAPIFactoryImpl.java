package cn.android.a6doctors.factory.DoctorApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import cn.android.a6doctors.API.APIService;
import cn.android.a6doctors.API.DoctorSrevice;
import cn.android.a6doctors.BuildConfig;
import cn.android.a6doctors.callback.CallBack;
import cn.android.a6doctors.factory.APIFactoryImpl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ChenTeacher on 2018/7/7.
 */

public class DoctorAPIFactoryImpl implements DoctorAPIFactory {
    private Retrofit retrofit;
    private Gson gson;
    private DoctorSrevice apiService;
    private static String baseUrl = "http://api.6doctors.cn/";

    public DoctorAPIFactoryImpl() {
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
        apiService = retrofit.create(DoctorSrevice.class);
    }

    public static DoctorAPIFactoryImpl getInstance() {
        return new DoctorAPIFactoryImpl();
    }
    /**
     * 查看自己创建的所有标签（分组）（主治医生显示全部）
     * @param token
     */
    @Override
    public void findLabelsByDoctorId(String token,final CallBack callBack) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("token",token);
        Call call = apiService.findLabelsByDoctorId(params);
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
                    }else{
                        callBack.onError();
                    }
                } catch (IOException e) {
                    callBack.onError();
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
    public void addLabel(String token, String name,final CallBack callBack) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("token",token);
        params.put("name",name);
        Call call = apiService.addLabel(params);
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
                        }else{
                            callBack.onFailure(msg);
                        }
                    }else{
                        callBack.onError();
                    }
                } catch (IOException e) {
                    callBack.onError();
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

}
