package com.example.week3bu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;


import com.example.week3bu.R;
import com.example.week3bu.bean.CartBean;

import java.util.List;

/**
 * Created by lenovo on 2018/8/23.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>implements CheckListener {

    private CheckListener checkListener;
    private Context context;
    private List<CartBean.DataBean> cartList;
    private CartAllCheckboxlistener allCheckboxlistener;


    public CartAdapter(Context context, List<CartBean.DataBean> cartList) {
        this.context = context;
        this.cartList = cartList;
    }
    public void addPageData(List<CartBean.DataBean>list){
        if (cartList!=null){
            cartList.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setAllCheckboxlistener(CartAllCheckboxlistener allCheckboxlistener) {
        this.allCheckboxlistener = allCheckboxlistener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.cart_item_layout, parent, false);
        CartViewHolder viewHolder = new CartViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position) {
        final CartBean.DataBean bean = cartList.get(position);
        holder.checkBox.setChecked(bean.isSelected());
        holder.nameTv.setText(bean.getSellerName());
        holder.proudXRV.setLayoutManager(new LinearLayoutManager(context));

        ProductAdapter productAdapter = new ProductAdapter(context, bean.getList());
        holder.proudXRV.setAdapter(productAdapter);
        productAdapter.setCheckListener(this);
//

        for (int i = 0; i < bean.getList().size(); i++) {
            if (!bean.getList().get(i).isSelected()){
                holder.checkBox.setChecked(false);
                break;
            }else {
                holder.checkBox.setChecked(true);
            }
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked()){
                    bean.setSelected(true);
                    for (int i = 0; i < bean.getList().size(); i++) {
                        bean.getList().get(i).setSelected(true);
                    }
                }else {
                    bean.setSelected(false);
                    for (int i = 0; i < bean.getList().size(); i++) {
                        bean.getList().get(i).setSelected(false);
                    }
                }
                notifyDataSetChanged();


            if (allCheckboxlistener!=null){
                allCheckboxlistener.notifyAllCheckboxStatus();
            }
            }
        });
    }

    public List<CartBean.DataBean> getCartList() {
        return cartList;
    }

    @Override
    public int getItemCount() {
        return cartList.size()==0?0:cartList.size();
    }

    @Override
    public void notifyParent() {
        notifyDataSetChanged();
        if (allCheckboxlistener!=null);
        allCheckboxlistener.notifyAllCheckboxStatus();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
      private CheckBox checkBox;
      private TextView nameTv;
      private RecyclerView proudXRV;
        public CartViewHolder(View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.sellerCheckbox);
            nameTv=itemView.findViewById(R.id.sellerNameTv);
            proudXRV=itemView.findViewById(R.id.productXRV);
        }
    }
}
