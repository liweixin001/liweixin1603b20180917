package com.example.lenovo.liweixin1603b20180917;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;

/**
 * Created by lenovo on 2018/9/17.
 */

public class Myjiajian extends LinearLayout{
    @BindView( R.id.jia )
    TextView jia;
    @BindView( R.id.jian )
    TextView jian;
    @BindView( R.id.num )
    EditText numEt;
    private int numl=1;


    public Myjiajian(Context context) {
        super( context,null );
    }

    public Myjiajian(Context context, @Nullable AttributeSet attrs) {
        super( context, attrs,0 );
    }

    public Myjiajian(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super( context, attrs, defStyleAttr );
        init(context);
    }

    private void init(Context context) {
        View view= LayoutInflater.from( context ).inflate( R.layout.ja_jian_layout,this,false );
        addView( view );
        numEt.setText( numl+"" );

        jia.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                numl++;
                numEt.setText( numl+"" );
                if (jiajianListener!=null){
                    jiajianListener.getNum( numl );
                }
            }
        } );
        jian.setOnClickListener( new OnClickListener() {
            @Override
            public void onClick(View view) {
                numl--;
                if (numl<=0){
                    Toast.makeText(getContext(),"不小于0" , Toast.LENGTH_SHORT).show();
                numl=1;
                }
                numEt.setText( numl+"" );
                if (jiajianListener!=null){
                    jiajianListener.getNum( numl );
                }
            }
        } );


    }

    public void setNuml(int n) {
        numEt.setText(n+""  );
    numl=Integer.parseInt( numEt.getText().toString() );
    }

    private  jiajianListener jiajianListener;

    public void setJiajianListener(jiajianListener jiajianListener) {
        this.jiajianListener = jiajianListener;

    }

    public interface jiajianListener{

        void getNum(int numl);
    }
}
