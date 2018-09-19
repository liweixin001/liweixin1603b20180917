package com.example.lenovo.liweixin1603b20180917.model;

import android.text.TextUtils;

import com.example.lenovo.liweixin1603b20180917.bean.CartBean;
import com.example.lenovo.liweixin1603b20180917.utils.OkHttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/9/17.
 */

public class CartMmodel {
    public void getCart(HashMap<String,String>prams, String url, final CartCallback cartCallback){
        OkHttpUtils.getInstance().postData( prams, url, new OkHttpUtils.RequestCallback() {
            @Override
            public void success(Call call, Response response) {
                try {
                    String s = response.body().string();
                    if (!TextUtils.isEmpty( s )){
                        jiexi(s,cartCallback);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(Call call, IOException e) {
                if (cartCallback!=null){
                    cartCallback.error( "网络延迟，稍后再试" );
                }
            }
        } );
    }

    private void jiexi(String s, CartCallback cartCallback) {
        CartBean cartBean=new Gson().fromJson( s,CartBean.class );
            if (cartCallback!=null){
                cartCallback.success( cartBean );
            }
        }


    public interface CartCallback {
        void success(CartBean cartBean);
        void error(String msg);

    }
}
