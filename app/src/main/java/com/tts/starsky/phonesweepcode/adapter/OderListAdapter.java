package com.tts.starsky.phonesweepcode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.db.bean.Sales;
import com.tts.starsky.phonesweepcode.utile.OnItemClickListener;

import java.util.List;

public class OderListAdapter extends RecyclerView.Adapter<OderListAdapter.ViewHolder> {

    private Context context;
    private List<Sales> data;
    private OnItemClickListener onItemClickListener;

    public OderListAdapter(Context context, List<Sales> data) {
        this.context = context;
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_oder_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
//        File file = new File(data.get(i));
//        if (file.length() > 0){
//            Glide.with(context).load(data.get(i)).into(viewHolder.iv_photo_wall);
//        }
//        viewHolder.iv_good_photo.(data.get(i));
        viewHolder.tv_good_name.setText(data.get(i).getSalesToGoods().get(0).getGoodsInfo().getGoodsName());
        viewHolder.tv_money.setText(String.valueOf(data.get(i).getRealityPrice()));
        viewHolder.tv_time.setText(data.get(i).getCreateTime());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_good_photo;
        private TextView tv_good_name;
        private TextView tv_money;
        private TextView tv_time;
        private OnItemClickListener onItemClickListener;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            initView(itemView);
        }

        private void initView(View itemView) {
            iv_good_photo = itemView.findViewById(R.id.iv_good_photo);
            tv_good_name = itemView.findViewById(R.id.tv_good_name);
            tv_money = itemView.findViewById(R.id.tv_money);
            tv_time = itemView.findViewById(R.id.tv_time);
//            iv_photo_wall.setText();
        }

        @Override
        public void onClick(View v) {
        }
    }
}
