package com.example.cxx.jinjie_week1.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cxx.jinjie_week1.R;
import com.example.cxx.jinjie_week1.adapter.XRecyclerAdapter;
import com.example.cxx.jinjie_week1.bean.ByKeywordBean;
import com.example.cxx.jinjie_week1.presenter.PresenterImpl;
import com.example.cxx.jinjie_week1.units.MvPInterface;
import com.example.cxx.jinjie_week1.units.MyServerApi;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowFragment<T> extends Fragment implements MvPInterface.MyView<T> {


    @BindView(R.id.scan)
    ImageView mScan;
    @BindView(R.id.ed_title)
    EditText mEdTitle;
    @BindView(R.id.search)
    TextView mSearch;
    @BindView(R.id.x_rv)
    XRecyclerView mXRv;
    private View view;
    private Unbinder unbinder;
    private PresenterImpl presenter;
    private String keyword="板鞋";
    private int page=1;
    private int count=5;
    private List<ByKeywordBean.ResultBean> mList=new ArrayList<>();
    private XRecyclerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new PresenterImpl(this);
        //开始请求数据
        startRequestData();
        //设置适配器
        adapter = new XRecyclerAdapter(getActivity().getApplicationContext(),mList);
        mXRv.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        mXRv.setAdapter(adapter);
        // 可以设置是否开启加载更多/下拉刷新
        mXRv.setLoadingMoreEnabled(true);
        // 可以设置加载更多的样式，很多种
        mXRv.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        // 如果设置上这个，下拉刷新的时候会显示上次刷新的时间
        mXRv.getDefaultRefreshHeaderView() // get default refresh header view
                .setRefreshTimeVisible(true);  // make refresh time visible,false means hiding
        setListener();
        return view;
    }

    private void setListener(){
        //上下拉
        mXRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                page=1;
                count=5;
                startRequestData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXRv.refreshComplete();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                page++;
                startRequestData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mXRv.loadMoreComplete();
                    }
                },2000);
            }
        });

    }

    private void startRequestData() {
        Map<String,String> headmap=new HashMap<>();
        Map<String,String> bodymap=new HashMap<>();
        bodymap.put("keyword",keyword);
        bodymap.put("page",page+"");
        bodymap.put("count",count+"");
        //开始请求数据
        presenter.startRequestDataget(MyServerApi.BASE_BYKEYWORD,headmap,bodymap,ByKeywordBean.class);
    }

    @OnClick({R.id.scan, R.id.search})
    public void onViewClicked() {
        String shopname = mEdTitle.getText().toString().trim();
        keyword=shopname;
        if (shopname.equals("")){
            Toast.makeText(getActivity(),"请输入关键词",Toast.LENGTH_SHORT).show();
        }else{
            mList.clear();
            Map<String,String> headmap=new HashMap<>();
            Map<String,String> bodymap=new HashMap<>();
            bodymap.put("keyword",keyword);
            bodymap.put("page",page+"");
            bodymap.put("count",count+"");
            presenter.startRequestDataget(MyServerApi.BASE_BYKEYWORD,headmap,bodymap,ByKeywordBean.class);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(T data) {
        if (data instanceof ByKeywordBean){
            ByKeywordBean byKeywordBean= (ByKeywordBean) data;
            if (byKeywordBean.getStatus().equals("0000")){
                if (byKeywordBean.getResult().size()==0){
                    Toast.makeText(getActivity(),"没有找到你想要的商品",Toast.LENGTH_SHORT).show();
                }else{
                    mList.addAll(byKeywordBean.getResult());
                    adapter.notifyDataSetChanged();
                }
            }else {
                Toast.makeText(getActivity(),byKeywordBean.getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void error(T error) {
        Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.OnDestroys();
        if (presenter!=null){
            presenter=null;
        }
    }
}
