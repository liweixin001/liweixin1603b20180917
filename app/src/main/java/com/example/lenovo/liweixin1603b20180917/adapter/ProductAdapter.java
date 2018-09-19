package com.example.lenovo.liweixin1603b20180917.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.liweixin1603b20180917.Myjiajian;
import com.example.lenovo.liweixin1603b20180917.R;
import com.example.lenovo.liweixin1603b20180917.bean.CartBean;

import java.util.List;

/**
 * Created by lenovo on 2018/9/17.
 */

class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{

    private Context context;
    private List<CartBean.DataBean.ListBean>listBeanList;

    public ProductAdapter(Context context, List<CartBean.DataBean.ListBean> listBeanList) {
        this.context = context;
        this.listBeanList = listBeanList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( context ).inflate( R.layout.cart_child,parent,false );
        ProductHolder productHolder = new ProductHolder( view );
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        CartBean.DataBean.ListBean bean=listBeanList.get( position );
        holder.child_price.setText( "优惠价:￥"+bean.getBargainPrice() );
        holder.child_tv.setText( bean.getTitle(  ));
        holder.child_cb.setChecked( bean.isSelected() );
        holder.myjiajian.setNuml( bean.getTotaLnum() );


    }

    @Override
    public int getItemCount() {
        return listBeanList.size();
    }

    public class ProductHolder extends RecyclerView.ViewHolder {
private CheckBox child_cb;
private TextView child_price,child_tv;
private Myjiajian myjiajian;
private ImageView child_image;

        public ProductHolder(View itemView) {
            super( itemView );
            child_cb=itemView.findViewById( R.id.cart_item_cb );

            child_price=itemView.findViewById( R.id.child_price );

            child_tv=itemView.findViewById( R.id.child_tv );
            myjiajian=itemView.findViewById( R.id.jiajian );

            child_image=itemView.findViewById( R.id.child_image );

        }
    }
}
