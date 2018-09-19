package com.example.week3bu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.week3bu.adapter.CartAdapter;
import com.example.week3bu.adapter.CartAllCheckboxlistener;
import com.example.week3bu.bean.CartBean;
import com.example.week3bu.presenter.CartPresenter;
import com.example.week3bu.view.IcartView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements IcartView,CartAllCheckboxlistener{

//
//    @BindView( R.id.allCheckbox )
//    CheckBox allcheckBox;
//    @BindView( R.id.caetRv )
//    XRecyclerView xrecyclerView;

//    @BindView( R.id.totalPriceTV )
//    TextView totalPricetv;


    private List<CartBean.DataBean> list;
    private CartPresenter cartPresenter;
    private CartAdapter cartAdapter;
    private XRecyclerView xRecyclerView;
    private CheckBox allcheckBox;
    private TextView totalPricetv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        initView();
        initData();

    }

    private void initView() {
        xRecyclerView = findViewById( R.id.caetRv );
        list=new ArrayList<>();

        allcheckBox=findViewById(R.id.allCheckbox);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        totalPricetv=findViewById(R.id.totalPriceTV);


        xRecyclerView.setLoadingMoreEnabled(true);//支持加载更多
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

                initData();
            }
        });

        allcheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(allcheckBox.isChecked()){
                    if (list!=null&&list.size()>0){
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setSelected(true);
                            for (int i1 = 0; i1 < list.get(i).getList().size(); i1++) {
                                list.get(i).getList().get(i1).setSelected(true);
                            }
                        }
                    }
                }else {
                    if (list!=null&&list.size()>0){
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setSelected(false);
                            for (int i1 = 0; i1 < list.get(i).getList().size(); i1++) {
                                list.get(i).getList().get(i1).setSelected(false);
                            }
                        }
                    }
//                    totalPrice=0;
                }
                cartAdapter.notifyDataSetChanged();
                totalPrice();
            }


        });
    }

    private void initData() {
        HashMap<String,String> params=new HashMap<>();
        params.put("uid","71");
        cartPresenter = new CartPresenter(this);
        cartPresenter.getCart(params,Api.GETCARTS);
    }
    @Override
    public void success(CartBean cartBean) {
        if (cartBean!=null&&cartBean.getData()!=null) {
            list = cartBean.getData();
            cartAdapter = new CartAdapter(this, list);
            xRecyclerView.setAdapter(cartAdapter);
            cartAdapter.setAllCheckboxlistener(this);
        }
    }

    @Override
    public void failure(String msg) {

    }

    @Override
    public void notifyAllCheckboxStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        if (cartAdapter!=null){
            for (int i = 0; i < cartAdapter.getCartList().size(); i++) {
                stringBuilder.append(cartAdapter.getCartList().get(i).isSelected());
                for (int i1 = 0; i1 < cartAdapter.getCartList().get(i).getList().size(); i1++) {
                    stringBuilder.append(cartAdapter.getCartList().get(i).getList().get(i1).isSelected());
                }
            }
        }
        if (stringBuilder.toString().contains("false")){
            allcheckBox.setChecked(false);
//                totlPrice=0;
        }else {
            allcheckBox.setChecked(true);
        }

        totalPrice();//计算总价

    }

    //计算总价
    private void totalPrice() {
        double totalPrice=0;
        for (int i = 0; i < cartAdapter.getCartList().size(); i++) {
            for (int i1 = 0; i1 < cartAdapter.getCartList().get(i).getList().size(); i1++) {
                if (cartAdapter.getCartList().get(i).getList().get(i1).isSelected()){
                    CartBean.DataBean.ListBean listBean = cartAdapter.getCartList().get(i).getList().get(i1);

                    totalPrice+=listBean.getBargainPrice()*listBean.getTotaLnum();
                }

            }

        }

        totalPricetv.setText("总价"+totalPrice);
    }
}
