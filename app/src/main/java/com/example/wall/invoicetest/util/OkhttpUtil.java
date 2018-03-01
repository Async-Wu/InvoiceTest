package com.example.wall.invoicetest.util;


import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.String.valueOf;

/**
 * 网络请求工具
 * Created by Wall on 2018/1/2.
 */
public class OkhttpUtil {
    private static OkHttpClient okHttpClient;
    private static Handler handler = new Handler();

    public static void initOkHttp(){
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    /**
     * post请求获取数据
     * @param url 请求地址
     * @param onDownDataListener 接口回调
     */
    public static void getRequestJson(final String url, String json, final OnDownDataListener onDownDataListener){
        okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(onDownDataListener != null){
                    onDownDataListener.onFailure(url, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String backStr = response.body().string();
                if(onDownDataListener != null){
                    onDownDataListener.onResponse(url, backStr);
                }
            }
        });
    }

    /**
     * post请求获取数据
     * @param url 请求地址
     * @param onDownDataListener 接口回调
     */
    public static void postRequestJson(final String url, String json, final OnDownDataListener onDownDataListener){
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("application/json; charset=utf-8");

        final RequestBody requestBody = RequestBody.create(MEDIA_TYPE_MARKDOWN,json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(onDownDataListener != null){
                    onDownDataListener.onFailure(url, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String backStr = response.body().string();
                if(onDownDataListener != null){
                    onDownDataListener.onResponse(url, backStr);
                }
            }
        });
    }


    public  static void postSubmitForm(final String url, Map<String,String> params, final OnDownDataListener onDownDataListener){

        if(params.size() > 0) {
            FormBody.Builder builder = new FormBody.Builder();
            for(String key : params.keySet()){
                String value = params.get(key);
                builder.add(key, value);
            }
            FormBody formBody = builder.build();

            final Request request = new Request.Builder()
                    .url(url)
                    .addHeader("content-type", "application/json;charset:utf-8")
                    .post(formBody)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if(onDownDataListener != null){
                        onDownDataListener.onFailure(url, e);
                    }
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String str = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if(onDownDataListener != null){
                                onDownDataListener.onResponse(url, str);
                            }
                        }
                    });
                }
            });
        }

    }

    public static void postFile(final String url, Map<String,Object> params, File file, final OnDownDataListener onDownDataListener){
        // form 表单形式上传
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (file != null) {
            // MediaType.parse() 里面是上传的文件类型。
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            String filename = file.getName();
            // 参数分别为， 请求key ，文件名称 ， RequestBody
            requestBody.addFormDataPart("file", filename, body);
        }
        if (params != null) {
            // map 里面是请求中所需要的 key 和 value
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry entry : entries) {
                String key = valueOf(entry.getKey());
                String value = valueOf(entry.getValue());
                Log.d("json", "上传的=="+key+"value=="+value);
                requestBody.addFormDataPart(key,value);
            }
        }
        Request request = new Request.Builder().url(url).post(requestBody.build()).build();
        // readTimeout("请求超时时间" , 时间单位);
        okHttpClient.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(onDownDataListener != null){
                    onDownDataListener.onFailure(url, e);
                }
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String backStr = response.body().string();
                if(onDownDataListener != null){
                    onDownDataListener.onResponse(url, backStr);
                }
            }
        });

    }

    public interface OnDownDataListener{
        void onResponse(String url, String json);
        void onFailure(String url, IOException error);
    }


}
