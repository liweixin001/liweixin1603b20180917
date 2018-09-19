package com.example.week3bu.presenter;


import com.example.week3bu.bean.CartBean;
import com.example.week3bu.model.CartModel;
import com.example.week3bu.view.IcartView;

import java.util.HashMap;

/**
 * Created by lenovo on 2018/8/22.
 */

public class CartPresenter {
    private IcartView icartView;
    private CartModel cartModel;

    public CartPresenter(IcartView icartView) {
        this.icartView = icartView;
        cartModel=new CartModel();
    }
    public void getCart(HashMap<String,String>params,String url){
        cartModel.getCart(params, url, new CartModel.CartCallBack() {
            @Override
            public void success(CartBean cartBean) {
                icartView.success(cartBean);
            }

            @Override
            public void fail(String msg) {
                icartView.failure(msg);
            }
        });
}

}
