package com.example.week3bu.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week3bu.R;


/**
 * Created by lenovo on 2018/8/23.
 */

public class MyJaJian extends LinearLayout{
    private TextView jiaTV,jianTV;
    private EditText numEt;
    private int numl=1;
    public MyJaJian(Context context) {
       this(context,null);
    }

    public MyJaJian(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyJaJian(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view= LayoutInflater.from(context).inflate(R.layout.jia_jian_layout,this,false);
        addView(view);
        jiaTV=findViewById( R.id.jia);
        jianTV=findViewById(R.id.jian);
        numEt=findViewById(R.id.num);

        numEt.setText(numl+"");
        jiaTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                numl++;
                numEt.setText(numl+"");
                if (jiajianListener!=null){
                    jiajianListener.getNum(numl);
                }
            }
        });
        jianTV.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                numl--;
                if (numl<=0){
                    Toast.makeText(getContext(),"穷鬼" , Toast.LENGTH_SHORT).show();
                    numl=1;
                }

                numEt.setText(numl+"");

                    if (jiajianListener!=null){
                        jiajianListener.getNum(numl);
                    }
            }
        });
    }

    public void setNumEd(int n) {
        numEt.setText(n+"");
        numl=Integer.parseInt(numEt.getText().toString());
    }

    private  jiajianListener jiajianListener;

    public void setJiajianListener(MyJaJian.jiajianListener jiajianListener) {
        this.jiajianListener = jiajianListener;

         }

    public interface jiajianListener{

        void getNum(int numl);
    }
}
