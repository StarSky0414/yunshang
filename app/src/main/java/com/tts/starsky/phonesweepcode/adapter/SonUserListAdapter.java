package com.tts.starsky.phonesweepcode.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.db.bean.UserInfo;

import java.util.List;

public class SonUserListAdapter extends RecyclerView.Adapter<SonUserListAdapter.SonUserInfoListViewHolder> {

    LayoutInflater inflater;
    public static List<UserInfo> userInfos;

    public SonUserListAdapter(Context context, List<UserInfo> list) {
        inflater = LayoutInflater.from(context);
        userInfos = list;
    }

    @NonNull
    @Override
    public SonUserInfoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_discount, parent, false);
        return new SonUserInfoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SonUserInfoListViewHolder holder, int position) {
        holder.bindData(userInfos.get(position));
    }

    @Override
    public int getItemCount() {
        return userInfos.size();
    }

    class SonUserInfoListViewHolder extends RecyclerView.ViewHolder {

        TextView mainText;

        public SonUserInfoListViewHolder(View itemView) {
            super(itemView);
            mainText = (TextView) itemView.findViewById(R.id.mainText);
        }

        public void bindData(UserInfo SonUserInfo) {
            mainText.setText(SonUserInfo.getUserName());
        }
    }
}
