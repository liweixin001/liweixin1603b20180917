package com.example.week3bu.view;


import com.example.week3bu.bean.CartBean;

/**
 * Created by lenovo on 2018/8/22.
 */

public interface IcartView {
void success(CartBean cartBean);
void  failure(String msg);
}

