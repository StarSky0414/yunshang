package com.tts.starsky.phonesweepcode.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;


import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.adapter.ViewPagerAdapter;
import com.tts.starsky.phonesweepcode.controller.GoodsInfoController;
import com.tts.starsky.phonesweepcode.db.bean.GoodsTypeInfo;
import com.tts.starsky.phonesweepcode.db.provider.GoodsTypeInfoProvider;
import com.tts.starsky.phonesweepcode.utile.NavitationLayout;

import java.util.ArrayList;
import java.util.List;

public class GoodsInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private NavitationLayout nl_goods_info;
    private ViewPager vp_goods_info;
    private ViewPagerAdapter pagerAdapter;
    private List<TypeFragment> goodsInfoFragmentList;
    private ImageView iv_close_goods_info;
    private int type;
    private ImageView iv_add;
    private GoodsTypeInfoProvider goodsTypeInfoProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goodsTypeInfoProvider = new GoodsTypeInfoProvider();
        setContentView(R.layout.activity_goods_info);
        initView();
        initData();
        setOnClickListener();
        }

    private void setOnClickListener() {
        iv_close_goods_info.setOnClickListener(this);
        iv_add.setOnClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        type = intent.getIntExtra("type", 0);

        List<GoodsTypeInfo> goodsTypeInfos = goodsTypeInfoProvider.queryGoodsTypeListRoot();
        goodsInfoFragmentList =  new ArrayList<TypeFragment>();
        String[] strings = new String[goodsTypeInfos.size()];

        for (int i=0;i<goodsTypeInfos.size();i++){
            TypeFragment typeFragment = new TypeFragment();
            typeFragment.setFirstType(i+1);
            goodsInfoFragmentList.add(typeFragment);
            strings[i]= goodsTypeInfos.get(i).getType_concrete_name();
        }
        nl_goods_info.setViewPager(this,strings, vp_goods_info, R.color.Gray, R.color.AppColor, 16, 16, 0, 0, true);
        nl_goods_info.setBgLine(this, 1, R.color.line);
        nl_goods_info.setNavLine(this, 2, R.color.AppColor, 0);
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), goodsInfoFragmentList);
        vp_goods_info.setAdapter(pagerAdapter);
        vp_goods_info.setCurrentItem(type);
    }

    private void initView() {
        nl_goods_info = (NavitationLayout) findViewById(R.id.nl_goods_info);
        vp_goods_info = (ViewPager) findViewById(R.id.vp_goods_info);
        iv_close_goods_info = (ImageView) findViewById(R.id.iv_close_goods_info);
        iv_add = (ImageView) findViewById(R.id.iv_add);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_close_goods_info:
                finish();
                break;
            case R.id.iv_add:
                Intent intent = new Intent(this, AddGoodInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
