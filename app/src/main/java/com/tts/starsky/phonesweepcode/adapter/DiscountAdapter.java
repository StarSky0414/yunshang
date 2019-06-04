package com.tts.starsky.phonesweepcode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.db.bean.Discount;
import com.tts.starsky.phonesweepcode.view.Init;

import java.util.ArrayList;
import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder> {

    LayoutInflater inflater;
//    List<Discount> modelList;

    public DiscountAdapter(Context context, List<Discount> list) {
        inflater = LayoutInflater.from(context);
//        modelList = new ArrayList<>(list);
    }

    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_discount, parent, false);
        return new DiscountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiscountViewHolder holder, int position) {
        holder.bindData(Init.discounts.get(position));
    }

    @Override
    public int getItemCount() {
        return Init.discounts.size();
    }

    class DiscountViewHolder extends RecyclerView.ViewHolder {

        TextView mainText;

        public DiscountViewHolder(View itemView) {
            super(itemView);
            mainText = (TextView) itemView.findViewById(R.id.mainText);
        }

        public void bindData(Discount discount) {
            mainText.setText(discount.getDiscountName());
        }
    }
}
