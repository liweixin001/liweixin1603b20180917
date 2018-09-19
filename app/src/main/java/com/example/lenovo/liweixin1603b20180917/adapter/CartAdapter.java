package com.example.lenovo.liweixin1603b20180917.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.lenovo.liweixin1603b20180917.R;
import com.example.lenovo.liweixin1603b20180917.bean.CartBean;

import java.util.List;

/**
 * Created by lenovo on 2018/9/17.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<CartBean.DataBean>cartList;
    private ProductAdapter productAdapter;

    public CartAdapter(Context context, List<CartBean.DataBean> cartList) {
        this.context = context;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.cart_item, parent,false);
        CartViewHolder cartViewHolder = new CartViewHolder( view );
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartBean.DataBean bean=cartList.get( position );

        holder.checkBox.setChecked( bean.isSelected() );
        holder.nameTv.setText( bean.getSellerName() );
        holder.proudXRv.setLayoutManager( new LinearLayoutManager( context ) );

        productAdapter = new ProductAdapter( context, bean.getList() );
        holder.proudXRv.setAdapter( productAdapter );

    }

    public List<CartBean.DataBean> getCartList() {
        return cartList;
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView nameTv;
        private RecyclerView proudXRv;
        public CartViewHolder(View itemView) {
            super( itemView );
            checkBox=itemView.findViewById( R.id.cart_item_cb );
            nameTv=itemView.findViewById( R.id.cart_item_name );
            proudXRv=itemView.findViewById( R.id.proudXRV );
        }
    }
}
