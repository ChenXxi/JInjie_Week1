package com.example.cxx.jinjie_week1.moudel;

import com.example.cxx.jinjie_week1.units.MvPInterface;
import com.example.cxx.jinjie_week1.units.RetrofitUnits;
import com.google.gson.Gson;

import java.util.Map;

public class MoudelImpl implements MvPInterface.IMoudel {
    private MvPInterface.MyCallBack myCallBack;

    @Override
    public void getdata(String url, Map<String, String> headmap, Map<String, String> bodymap, final Class aClass, final MvPInterface.MyCallBack myCallBack) {
        this.myCallBack=myCallBack;
        RetrofitUnits.getInstance().get(url,headmap,bodymap).setHttpListener(new RetrofitUnits.HttpListener() {
            @Override
            public void success(String jsonStr) {
                Gson gson = new Gson();
                Object fromJson = gson.fromJson(jsonStr, aClass);
                myCallBack.setData(fromJson);
            }

            @Override
            public void error(String error) {
                myCallBack.setError(error);
            }
        });
    }

    @Override
    public void putdata(String url, Map<String, String> headmap, Map<String, String> bodymap, final Class aClass, final MvPInterface.MyCallBack myCallBack) {
        this.myCallBack=myCallBack;
        RetrofitUnits.getInstance().put(url,headmap,bodymap).setHttpListener(new RetrofitUnits.HttpListener() {
            @Override
            public void success(String jsonStr) {
                Gson gson = new Gson();
                Object fromJson = gson.fromJson(jsonStr,aClass);
                myCallBack.setData(fromJson);
            }

            @Override
            public void error(String error) {
                myCallBack.setError(error);
            }
        });
    }
}
