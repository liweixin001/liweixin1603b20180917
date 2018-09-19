package com.example.lenovo.liweixin1603b20180917.fragment;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.liweixin1603b20180917.Api;
import com.example.lenovo.liweixin1603b20180917.R;
import com.example.lenovo.liweixin1603b20180917.adapter.CartAdapter;
import com.example.lenovo.liweixin1603b20180917.bean.CartBean;
import com.example.lenovo.liweixin1603b20180917.presenter.CartPresenter;
import com.example.lenovo.liweixin1603b20180917.view.CartView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/9/17.
 */

public class CartFragment extends AppCompatActivity implements CartView{
    private CartPresenter cartPresenter;
    private Context context;

    @BindView( R.id.Xrecy )
    XRecyclerView Xrecy;
    @BindView( R.id.allCheckbox )
    CheckBox allCheckbox;
    @BindView( R.id.totalPriceTV )
    TextView totalPriceTV;
    private List<CartBean.DataBean> data;
    private CartAdapter cartAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.cart_layout );
        initView();
    }



    private void initView() {


        Xrecy.setLayoutManager( new LinearLayoutManager( this) );

        Xrecy.setLoadingMoreEnabled( true );
        Xrecy.setLoadingListener( new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        } );


        allCheckbox.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allCheckbox.isChecked()){
                    if (data!=null){
                        for (int i = 0; i < data.size(); i++) {
                            data.get( i ).setSelected( true );
                            for (int i1 = 0; i1 < data.get( i ).getList().size(); i1++) {
                                data.get( i ).getList().get( i1 ).setSelected( true );
                            }
                        }
                    }
                }else {
                    if (data!=null){
                        for (int i = 0; i < data.size(); i++) {
                            data.get( i ).setSelected( false );
                            for (int i1 = 0; i1 < data.get( i ).getList().size(); i1++) {
                                data.get( i ).getList().get( i1 ).setSelected( false );
                            }
                        }
                    }

                }
            }
        } );
    }

    private void initData() {
        HashMap<String,String>prams=new HashMap<>(  );
        prams.put( "uid","71" );
        cartPresenter=new CartPresenter( this );
        cartPresenter.getCart( prams, Api.GET_CART );
    }

    @Override
    public void Success(CartBean cartBean) {
     if (cartBean!=null){
         data = cartBean.getData();
         cartAdapter = new CartAdapter(context,data);
         Xrecy.setAdapter( cartAdapter );
//         cartAdapter.

     }
    }

    @Override
    public void Error(String msg) {

    }
}
