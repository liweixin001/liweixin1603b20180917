package com.example.lenovo.liweixin1603b20180917.view;

import com.example.lenovo.liweixin1603b20180917.bean.CartBean;

/**
 * Created by lenovo on 2018/9/17.
 */

public interface CartView {
    void Success(CartBean cartBean);
    void Error(String msg);
}
