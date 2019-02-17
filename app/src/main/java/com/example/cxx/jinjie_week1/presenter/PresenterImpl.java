package com.example.cxx.jinjie_week1.presenter;

import com.example.cxx.jinjie_week1.moudel.MoudelImpl;
import com.example.cxx.jinjie_week1.units.MvPInterface;

import java.util.Map;

public class PresenterImpl implements MvPInterface.IPresenter {

    private MvPInterface.MyView myView;
    private MoudelImpl moudel;

    public PresenterImpl(MvPInterface.MyView myView) {
        this.myView = myView;
        moudel = new MoudelImpl();
    }

    @Override
    public void startRequestDataget(String url, Map<String, String> headmap, Map<String, String> bodymap, Class aClass) {
        moudel.getdata(url, headmap, bodymap, aClass, new MvPInterface.MyCallBack() {
            @Override
            public void setData(Object data) {
                myView.success(data);
            }

            @Override
            public void setError(Object error) {
                myView.error(error);
            }
        });
    }

    @Override
    public void startRequestDataput(String url, Map<String, String> headmap, Map<String, String> bodymap, Class aClass) {
        moudel.putdata(url, headmap, bodymap, aClass, new MvPInterface.MyCallBack() {
            @Override
            public void setData(Object data) {
                myView.success(data);
            }

            @Override
            public void setError(Object error) {
                myView.error(error);
            }
        });
    }
    //MVP优化处理，防止内存泄漏
    public void OnDestroys(){
        if (myView!=null){
            myView=null;
        }
        if (moudel!=null){
            moudel=null;
        }
    }
}
