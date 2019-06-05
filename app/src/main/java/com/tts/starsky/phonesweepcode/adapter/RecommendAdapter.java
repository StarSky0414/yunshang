package com.tts.starsky.phonesweepcode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.utile.OnItemClickListener;

import java.io.File;
import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    private Context context;
    private List<GoodsInfo> data;
    private OnItemClickListener onItemClickListener;

    public RecommendAdapter(Context context, List<GoodsInfo> data) {
        this.context = context;
        this.data = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<GoodsInfo> getData() {
        return data;
    }

    public void setData(List<GoodsInfo> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_recommend, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate, onItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final ViewHolder holder = viewHolder;
        holder.tv_good_name.setText(data.get(i).getGoodsName());
        holder.tv_price.setText(String.valueOf(data.get(i).getNowPrice()));
        /*File file = new File(data.get(i).getCommodity_image());
        if (file.length() > 0) {
            Glide.with(context).load(data.get(i).getCommodity_image()).into(holder.iv_recommend);
        }else{
            OssHandler ossHandler = new OssHandler(context, holder.iv_recommend);
            DownloadUtils.downloadFileFromOss(file,ossHandler,ProjectProperties.BUCKET_NAME,"xuzhanxin/"+data.get(i).getCommodity_image());
        }*/
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_recommend;
        private RelativeLayout rl_recommend;
        private TextView tv_good_name;
        private TextView tv_unit;
        private TextView tv_price;

        private OnItemClickListener onItemClickListener;
        private Button bt_collection;

        public ViewHolder(@NonNull View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            initView(itemView);
        }

        private void initView(View itemView) {
            iv_recommend = itemView.findViewById(R.id.iv_recommend);
            iv_recommend.setScaleType(ImageView.ScaleType.CENTER_CROP);
            rl_recommend = itemView.findViewById(R.id.rl_recommend);
            tv_good_name = itemView.findViewById(R.id.tv_good_name);
            tv_unit = itemView.findViewById(R.id.tv_unit);
            tv_price = itemView.findViewById(R.id.tv_price);
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(v, getPosition());
        }
    }
}
