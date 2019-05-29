package com.tts.starsky.phonesweepcode.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tts.starsky.phonesweepcode.bean.Data;
import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.utile.DiscountUtile;

import java.util.List;

public class AccountsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final TextView tv_balance_base;
    private ArrayAdapter<String> adapter;
    private List<Data> itemDataList;

    private View.OnClickListener clickListener;

    // 刷新标记
    public static boolean flashSign = false;

    public AccountsAdapter(List<Data> dataList, View.OnClickListener clickListener, ArrayAdapter<String> adapter, TextView tv_balance_base) {
        this.clickListener = clickListener;
        this.itemDataList = dataList;
        this.adapter = adapter;
        this.tv_balance_base = tv_balance_base;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {

        final ViewHolder viewH = (ViewHolder) viewHolder;
        final Data data = itemDataList.get(i);

        // 设定标记 用于列表寻找数据
        viewH.iv_goods_num_add.setTag(i);
        viewH.iv_goods_num_add.setOnClickListener(clickListener);
        viewH.iv_goods_num_cut.setTag(i);
        viewH.iv_goods_num_cut.setOnClickListener(clickListener);

        viewH.et_goods_num.setText(String.valueOf(data.getGoodsNum()));
        viewH.tv_goods_name.setText(data.getGoodsName());
        if (viewH.tv_goods_price.getText().toString().equals("")) {
            viewH.tv_goods_price.setText(String.valueOf(data.getGoodsPrice()));
        }

        viewH.s_discount.setAdapter(adapter);


        // 判断是否是刷新，刷新将重新执行 setOnItemSelectedListener
//        if (flashSign) {
        int discountSpinnerListNum = data.getDiscountSpinnerListNum();
        viewH.s_discount.setSelection(discountSpinnerListNum);
//        }

        viewH.s_discount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 设定选中的位置
                data.setDiscountSpinnerListNum(position);

                // 优惠计算
                double goodsPrice = data.getGoodsPrice();
                double countReslut = DiscountUtile.getCountReslut(goodsPrice, position);
                data.setGoodsDisconutPrice(countReslut);
                viewH.tv_goods_price.setText(String.valueOf(countReslut));

                // 更新金额
                tv_balance_base.setText(getExpression());
                // 引用数据类型无需set
                // itemDataList.set(i, data);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    public int getItemCount() {
        return itemDataList.size();
    }

    public int addGoodsNum(int tag) {
        int i = itemDataList.get(tag).addNum();
        return i;
    }

    public int cutGoodsNum(int tag) {
        int i = itemDataList.get(tag).cutNum();
        return i;
    }

    public void removeGoods(int tag) {
        itemDataList.remove(tag);
        notifyDataSetChanged();
    }

    public void addItemList(String goodsName, double nowPrice, String goodsBarCode,int maxNum) {
        for(int i=0; i<itemDataList.size() ;i++){
            if (itemDataList.get(i).getGoodsBarCode().equals(goodsBarCode)) {
                itemDataList.get(i).addNum();
                notifyItemChanged(i);
                return;
            }
        }
        Data data = new Data();
        data.setGoodsName(goodsName);
        data.setGoodsPrice(nowPrice);
        data.setGoodsBarCode(goodsBarCode);
        data.setMaxNum(maxNum);
        itemDataList.add(data);
        notifyItemInserted(itemDataList.size()-1);
    }

    public int getGoodsNum(int tag) {
        
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_goods_num_cut;
        ImageView iv_goods_num_add;
        EditText et_goods_num;
        TextView tv_goods_price;
        TextView tv_goods_name;
        Spinner s_discount;

        public ViewHolder(View itemView) {
            super(itemView);

            // 增加、减少按钮
            iv_goods_num_cut = (ImageView) itemView.findViewById(R.id.iv_goods_num_cut);
            iv_goods_num_add = (ImageView) itemView.findViewById(R.id.iv_goods_num_add);
            et_goods_num = (EditText) itemView.findViewById(R.id.et_goods_num);
            tv_goods_price = (TextView) itemView.findViewById(R.id.tv_goods_price);
            tv_goods_name = (TextView) itemView.findViewById(R.id.tv_goods_name);
            s_discount = (Spinner) itemView.findViewById(R.id.s_discount);

        }
    }

    public String getExpression(){
        double goodsPriceSum=0;
        double goodsDisconutPriceSum=0;


        for (Data data:itemDataList){
            double goodsPrice = data.getGoodsPrice();  // 总金额
            double goodsDisconutPrice = data.getGoodsDisconutPrice();  // 实际应收
            int goodsNum = data.getGoodsNum();
            goodsPriceSum += goodsPrice * goodsNum;
            goodsDisconutPriceSum += (goodsDisconutPrice != -1 ? goodsDisconutPrice : goodsPrice) * goodsNum;
        }
//        String s = String.format("%.1f - %.1f = %.1f", goodsPriceSum, goodsPriceSum - goodsDisconutPriceSum, goodsDisconutPriceSum);
        String s = String.format("%.1f - %.1f = %.1f", goodsPriceSum, goodsPriceSum - goodsDisconutPriceSum, goodsDisconutPriceSum);
        return s;
    }

    public List<Data> getItemDataList() {
        return itemDataList;
    }



}

