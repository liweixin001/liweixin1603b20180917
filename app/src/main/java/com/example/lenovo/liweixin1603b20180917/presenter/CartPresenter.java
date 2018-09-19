package com.example.lenovo.liweixin1603b20180917.presenter;

import com.example.lenovo.liweixin1603b20180917.bean.CartBean;
import com.example.lenovo.liweixin1603b20180917.fragment.CartFragment;
import com.example.lenovo.liweixin1603b20180917.model.CartMmodel;
import com.example.lenovo.liweixin1603b20180917.view.CartView;

import java.util.HashMap;

/**
 * Created by lenovo on 2018/9/17.
 */

public class CartPresenter {
    private CartView cartView;
    private CartMmodel cartMmodel;

    public CartPresenter(CartFragment cartView) {

        cartMmodel = new CartMmodel();
        attachView( cartView );
    }

    //绑定数据
    private void attachView(CartView cartView) {
        this.cartView = cartView;
    }

    public void getCart(HashMap<String, String> prams, String url) {
        cartMmodel.getCart( prams, url, new CartMmodel.CartCallback() {
            @Override
            public void success(CartBean cartBean) {
                if (cartView != null) {
                    cartView.Success( cartBean );
                }
            }

            @Override
            public void error(String msg) {
                if (cartView != null) {
                    cartView.Error( msg );
                }
            }
        } );

    }

    //解除绑定
    public void detachView() {
        this.cartView = null;
    }
}
