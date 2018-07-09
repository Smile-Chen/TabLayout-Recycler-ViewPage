package co.example.hp.chenjia20180709.http;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by hp on 2018/6/21.
 */

public class HttpUtils {
    private static HttpUtils httpUtils=new HttpUtils();
    private OkHttpClient mOkHttpClien;
    private final Handler handler;
    private static final String TAG = "HttpUtils";
    private HttpUtils() {

        //创建一个主线程的handler
        handler = new Handler(Looper.getMainLooper());
        mOkHttpClien = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
    }

    public static HttpUtils getHttpUtils() {
        if (httpUtils == null) {
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    return httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }

    //get封装
    public void get(String url, final InOkCallback inokCallback) {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        final Call call = mOkHttpClien.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                 if (inokCallback!=null){
                     handler.post(new Runnable() {
                         @Override
                         public void run() {
                             inokCallback.getError(e);
                         }
                     });
                 }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (inokCallback!=null){
                    final String json = response.body().string();
                        if (response!=null&&response.isSuccessful()){
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    inokCallback.getSuccess(json);
                                    Log.d(TAG, "run: httputils===="+json);
                                }
                            });
                        }
                }
            }
        });
    }
    //post封装
    public void post(String url,Map<String,String>map, final InOkCallback inokCallback) {
        FormBody.Builder formbody = new FormBody.Builder();
        for (String key:map.keySet()){
            formbody.add(key,map.get(key));
        }
        FormBody build = formbody.build();
        Request request = new Request.Builder()
                .post(build)
                .url(url)
                .build();
        final Call call = mOkHttpClien.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {
                if (inokCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            inokCallback.getError(e);
                        }
                    });
                }
            }
        @Override
        public void onResponse(Call call, Response response) throws IOException {
                if (inokCallback!=null){
                    final String json = response.body().string();
                    if (response!=null&&response.isSuccessful()){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                inokCallback.getSuccess(json);
                                Log.d(TAG, "run: ppppppppppppppppppppppppppp====================="+json);
                            }
                        });
                    }
                }
            }
        });
    }



   //创建一个接口
    public interface InOkCallback {
        void getSuccess(String json);
        void getError(Exception error);
    }


}
