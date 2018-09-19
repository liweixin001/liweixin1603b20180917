package com.example.week3bu.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week3bu.R;
import com.example.week3bu.bean.CartBean;
import com.example.week3bu.widget.MyJaJian;
import com.facebook.drawee.view.SimpleDraweeView;


import java.util.List;

/**
 * Created by lenovo on 2018/8/23.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CartViewHolder>{

    private Context context;
    private List<CartBean.DataBean.ListBean> listBeanList;
    private CartAllCheckboxlistener cartAllCheckboxlistener;
    private CheckListener checkListener;
//暴露给调用者
    public void setCheckListener(CheckListener checkListener) {
        this.checkListener = checkListener;
    }

    public ProductAdapter(Context context, List<CartBean.DataBean.ListBean> listBeanList) {
        this.context = context;
        this.listBeanList = listBeanList;
    }

    public void setCartAllCheckboxlistener(CartAllCheckboxlistener cartAllCheckboxlistener) {
        this.cartAllCheckboxlistener = cartAllCheckboxlistener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate( R.layout.product_item_layout, parent, false);
        CartViewHolder viewHolder = new CartViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartViewHolder holder, int position) {
        final CartBean.DataBean.ListBean bean = listBeanList.get(position);
        holder.pricrTv.setText("优惠价：￥"+bean.getBargainPrice());
        holder.titleTv.setText(bean.getTitle());

//        String[] imgs=bean.getImages().split("\\|");
//        if (imgs!=null&&imgs.length>0) {
//            Glide.with(context).load(imgs[0]).into(holder.proudIv);
//        }

        Uri uri = Uri.parse(bean.getImages().split( "\\|" )[0]);
        holder.proudIv.setImageURI(uri);

        holder.checkBox.setChecked(bean.isSelected());
        holder.myJaJian.setNumEd(bean.getTotaLnum());
        holder.myJaJian.setJiajianListener(new MyJaJian.jiajianListener() {
            @Override
            public void getNum(int numl) {
                bean.setTotaLnum(numl);
                if (checkListener!=null){
                    checkListener.notifyParent();
                }
            }
        });



        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked()){
                    bean.setSelected(true);
                }else {
                    bean.setSelected(false);
                }
                if (checkListener!=null){
                    checkListener.notifyParent();//通知一级列表
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeanList.size()==0?0:listBeanList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
      private CheckBox checkBox;
      private TextView titleTv,pricrTv;
      private SimpleDraweeView proudIv;
      private MyJaJian myJaJian;
        public CartViewHolder(View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.productCheckBox);
            titleTv=itemView.findViewById(R.id.title);
            pricrTv=itemView.findViewById(R.id.price);
            proudIv=itemView.findViewById(R.id.product_icon);
            myJaJian=itemView.findViewById(R.id.jiajianqi);
        }
    }
}
