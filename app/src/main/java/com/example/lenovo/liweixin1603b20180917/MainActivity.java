package com.example.lenovo.liweixin1603b20180917;

import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.lenovo.liweixin1603b20180917.fragment.AFragment;
import com.example.lenovo.liweixin1603b20180917.fragment.BFragment;
import com.example.lenovo.liweixin1603b20180917.fragment.CFragment;
import com.example.lenovo.liweixin1603b20180917.fragment.CartFragment;
import com.example.lenovo.liweixin1603b20180917.fragment.DFragment;
import com.gyf.barlibrary.ImmersionBar;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {
    @BindView( R.id. tab_var)
    BottomTabBar tab_var;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){
            Window window=getWindow();
            window.addFlags( WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS );
            ActionBar actionBar=getSupportActionBar();
            actionBar.hide();
        }
        tab_var=findViewById( R.id.tab_var );
        initView();
    }

    private void initView() {
        ImmersionBar.with( this ).init();
        tab_var.init( getSupportFragmentManager() )
                .setFontSize( 30 )
                .setImgSize( 50,50 )
                .setTabPadding( 5,1,1 )
                .addTabItem( "购物车",R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,CartFragment.class )
                .addTabItem( "首页",R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,AFragment.class )
                .addTabItem( "分类",R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,BFragment.class )
                .addTabItem( "发现",R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,CFragment.class )
                .addTabItem( "我的",R.drawable.ic_launcher_foreground,R.drawable.ic_launcher_foreground,DFragment.class )
                .isShowDivider( false );
    }


}
