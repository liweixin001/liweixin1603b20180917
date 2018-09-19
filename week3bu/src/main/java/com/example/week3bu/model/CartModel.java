package com.example.week3bu.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.week3bu.bean.CartBean;
import com.example.week3bu.utils.OkhttpUtils;
import com.example.week3bu.utils.RequestCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lenovo on 2018/8/22.
 */

public class CartModel {
    private Handler handler=new Handler();
    public void getCart(HashMap<String,String>prams, String url, final CartCallBack cartCallBack){
        OkhttpUtils.getInstace().postData(url, prams, new RequestCallBack() {
            @Override
            public void failure(Call call, IOException e) {
                cartCallBack.fail("有错");
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String jsonResult=response.body().string();
                    if (!TextUtils.isEmpty(jsonResult)){
                        jiexi(jsonResult,cartCallBack);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void jiexi(String jsonResult, final CartCallBack cartCallBack) {
        final CartBean cartBean = new Gson().fromJson(jsonResult, CartBean.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                cartCallBack.success(cartBean);
            }
        });
    }

    public interface CartCallBack {
    void success(CartBean cartBean);
    void fail(String msg);
    }
}
