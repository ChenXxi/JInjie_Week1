package com.example.cxx.jinjie_week1.activity;


import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.cxx.jinjie_week1.R;
import com.example.cxx.jinjie_week1.base.BaseActivity;
import com.example.cxx.jinjie_week1.fragment.BlankFragment;
import com.example.cxx.jinjie_week1.fragment.ShowFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private FragmentManager manager =getSupportFragmentManager();

    private BlankFragment blankFragment =new BlankFragment();
    private ShowFragment showFragment = new ShowFragment();

    private FrameLayout show_frame;
    private RadioButton main_rg_show;
    private RadioButton main_rg_circle;
    private RadioButton main_rg_shop;
    private RadioButton main_rg_order;
    private RadioButton main_rg_my;
    private RadioGroup main_rg;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void findByid() {
        show_frame = findViewById(R.id.show_frame);
        main_rg_show = findViewById(R.id.main_rg_show);
        main_rg_circle = findViewById(R.id.main_rg_circle);
        main_rg_shop = findViewById(R.id.main_rg_shop);
        main_rg_order = findViewById(R.id.main_rg_order);
        main_rg_my = findViewById(R.id.main_rg_my);
        main_rg = findViewById(R.id.main_rg);
    }

    @Override
    protected void setOnclick() {
        main_rg.setOnCheckedChangeListener(this);
    }

    @Override
    protected void proacess() {
        manager.beginTransaction().replace(R.id.show_frame,showFragment).commit();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 这是RadioGroup点击事件
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.main_rg_show:
                manager.beginTransaction().replace(R.id.show_frame,showFragment).commit();
                break;
            case R.id.main_rg_circle:
                manager.beginTransaction().replace(R.id.show_frame,blankFragment).commit();
                break;
            case R.id.main_rg_shop:
                manager.beginTransaction().replace(R.id.show_frame,blankFragment).commit();
                break;
            case R.id.main_rg_order:
                manager.beginTransaction().replace(R.id.show_frame,blankFragment).commit();
                break;
            case R.id.main_rg_my:
                manager.beginTransaction().replace(R.id.show_frame,blankFragment).commit();
                break;
        }
    }
}