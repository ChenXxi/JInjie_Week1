package com.example.cxx.jinjie_week1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cxx.jinjie_week1.R;
import com.example.cxx.jinjie_week1.bean.ByKeywordBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XRecyclerAdapter extends RecyclerView.Adapter<XRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<ByKeywordBean.ResultBean> mData;

    public XRecyclerAdapter(Context context, List<ByKeywordBean.ResultBean> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.mShopIcon.setImageURI(mData.get(i).getMasterPic());
        viewHolder.mTvShopName.setText(mData.get(i).getCommodityName());
        viewHolder.mTvShopPrice.setText("￥"+mData.get(i).getPrice());
        viewHolder.mTvShopNum.setText("已售"+mData.get(i).getSaleNum()+"件");
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.shop_icon)
        SimpleDraweeView mShopIcon;
        @BindView(R.id.tv_shop_name)
        TextView mTvShopName;
        @BindView(R.id.tv_shop_price)
        TextView mTvShopPrice;
        @BindView(R.id.tv_shop_num)
        TextView mTvShopNum;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
