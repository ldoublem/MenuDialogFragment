package com.ldoublem.MenuDialogFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    MenuDialogFragment mMenuDialogFragment;
    FragmentTransaction transaction;

    Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.activity_main);
//        showmenudialog();

        btn_click=(Button)findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showmenudialog();
            }
        });

    }


    public void showmenudialog() {

        transaction = getSupportFragmentManager().beginTransaction();
        if (mMenuDialogFragment == null) {
            mMenuDialogFragment = MenuDialogFragment.newInstance();
        }

        // // 指定一个过渡动画
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        // 作为全屏显示,使用“content”作为fragment容器的基本视图,这始终是Activity的基本视图
        transaction.replace(android.R.id.content, mMenuDialogFragment).commit();

    }

}



