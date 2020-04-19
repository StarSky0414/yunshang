package com.tts.starsky.phonesweepcode.view.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.adapter.OderListAdapter;
import com.tts.starsky.phonesweepcode.controller.SalesController;
import com.tts.starsky.phonesweepcode.controller.UserController;
import com.tts.starsky.phonesweepcode.db.bean.Sales;
import com.tts.starsky.phonesweepcode.view.AccountsActivity;
import com.tts.starsky.phonesweepcode.view.GoodsInfoActivityList;
import com.tts.starsky.phonesweepcode.view.GoodsIntoActivity;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FirstPageFragment extends Fragment  implements View.OnClickListener{

    private View fragment_firstpage;
    private RecyclerView rv_photo_wall;
    private SalesController salesController;
    private TextView tv_good_manage;
    private TextView tv_good_into_manage;
    private TextView tv_accounts_manage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragment_firstpage = inflater.inflate(R.layout.fragment_firstpage, null);
        salesController = new SalesController();
        init();
        show();
        return fragment_firstpage;
    }

    public void init(){
        rv_photo_wall = fragment_firstpage.findViewById(R.id.rv_photo_wall);
        tv_good_manage = fragment_firstpage.findViewById(R.id.tv_good_manage);
        tv_good_into_manage = fragment_firstpage.findViewById(R.id.tv_good_into_manage);
        tv_accounts_manage = fragment_firstpage.findViewById(R.id.tv_accounts_manage);

        tv_good_manage.setOnClickListener(this);
        tv_good_into_manage.setOnClickListener(this);
        tv_accounts_manage.setOnClickListener(this);

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getActivity());
        rv_photo_wall.setLayoutManager(gridLayoutManager);
//        DatabaseOP databaseOP = new DatabaseOP(context);
//        submitImageList = databaseOP.getSubmitImageInfoList(data.get(position).getSubmit_id());
        List<Sales> sales = salesController.showSalesAll();
        rv_photo_wall.setAdapter(new OderListAdapter(getActivity(),sales));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_good_manage:
                startActivity(new Intent(getActivity(), GoodsInfoActivityList.class));
                break;
            case R.id.tv_good_into_manage:
                startActivity(new Intent(getActivity(), GoodsIntoActivity.class));
                break;
            case R.id.tv_accounts_manage:
                startActivity(new Intent(getActivity(), AccountsActivity.class));
                break;
        }
    }

    private void show(){
        if (!UserController.isAdmin()) {
            tv_good_manage.setVisibility(View.GONE);
            tv_good_into_manage.setVisibility(View.GONE);
        }
    }
}
