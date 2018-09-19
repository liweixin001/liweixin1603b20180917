package com.example.lenovo.liweixin1603b20180917.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/9/17.
 */

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;
    private OkHttpUtils(){
        okHttpClient=new OkHttpClient.Builder()
                .build();
    }
    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if(okHttpUtils==null){
                    okHttpUtils=new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    public void postData(HashMap<String, String> prams, String url, final RequestCallback requestCallback){
        FormBody.Builder formbodyBuilder=new FormBody.Builder(  );
        if (prams!=null){

            for (Map.Entry<String, String> stringStringEntry : prams.entrySet()) {
                formbodyBuilder.add( stringStringEntry.getKey(),stringStringEntry.getValue() );
            }
        }
        Request request = new Request.Builder()
                .url( url )
                .post( formbodyBuilder.build() )
                .build();

        Call call = okHttpClient.newCall( request );
        okHttpClient.newCall( request ).enqueue( new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback!=null){
                    requestCallback.error( call, e );
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallback!=null){
                    requestCallback.success( call, response );
                }
            }
        } );


    }

    public interface RequestCallback {
        void success(Call call, Response response);
        void error(Call call,IOException e);
    }
}
