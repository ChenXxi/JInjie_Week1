package com.example.cxx.jinjie_week1.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * 基类抽取
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }
    protected abstract int getLayout();

    protected abstract void findByid();

    protected abstract void setOnclick();

    protected abstract void proacess();

    void init(){
        if(getLayout()!=0){
            setContentView(getLayout());
            findByid();
            setOnclick();
            proacess();
        }else{
            throw new IllegalStateException("请关联布局文件");
        }
    }
}
