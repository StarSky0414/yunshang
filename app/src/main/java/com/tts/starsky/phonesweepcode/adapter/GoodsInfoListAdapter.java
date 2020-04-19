package com.tts.starsky.phonesweepcode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;

import java.util.List;

public class GoodsInfoListAdapter extends RecyclerView.Adapter<GoodsInfoListAdapter.GoodsInfoListViewHolder> {

    LayoutInflater inflater;
    public static List<GoodsInfo> goodsInfos;

    public GoodsInfoListAdapter(Context context, List<GoodsInfo> list) {
        inflater = LayoutInflater.from(context);
        goodsInfos = list;
    }

    @NonNull
    @Override
    public GoodsInfoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_discount, parent, false);
        return new GoodsInfoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodsInfoListViewHolder holder, int position) {
        holder.bindData(goodsInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return goodsInfos.size();
    }

    class GoodsInfoListViewHolder extends RecyclerView.ViewHolder {

        TextView mainText;

        public GoodsInfoListViewHolder(View itemView) {
            super(itemView);
            mainText = (TextView) itemView.findViewById(R.id.mainText);
        }

        public void bindData(GoodsInfo goodsInfo) {
            mainText.setText(goodsInfo.getGoodsName());
        }
    }
}
