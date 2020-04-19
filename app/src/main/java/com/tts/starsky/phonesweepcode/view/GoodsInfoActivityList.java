package com.tts.starsky.phonesweepcode.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;
import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.adapter.GoodsInfoListAdapter;
import com.tts.starsky.phonesweepcode.controller.GoodsInfoLIstController;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.provider.GoodsStockProvider;

import java.util.List;

/**
 *  商品信息类
 */
public class GoodsInfoActivityList extends Activity implements RecyclerTouchListener.RecyclerTouchListenerHelper,View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;
    private GoodsInfoListAdapter mAdapter;
    private EditText et_discount_name;
//    private EditText et_discount_num;
    private GoodsStockProvider  goodsStockProvider;
    private GoodsInfoLIstController  goodsInfoLIstController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info_list);
        goodsStockProvider = new GoodsStockProvider();

        initRecyclerView();

        // 初始化弹窗信息，用于添加数据
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_discount, null);
        et_discount_name = (EditText) view.findViewById(R.id.et_discount_name);
//        et_discount_num = (EditText) view.findViewById(R.id.et_discount_num);

        // 设定添加按钮的监听
        ImageView iv_add_discount = (ImageView) findViewById(R.id.iv_add_discount);
        iv_add_discount.setOnClickListener(this);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {

        goodsInfoLIstController = new GoodsInfoLIstController();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        List<GoodsInfo> allGoodsInfo = goodsInfoLIstController.getAllGoodsInfo();
        if (allGoodsInfo != null && allGoodsInfo.size()!=0){

        }

        mAdapter = new GoodsInfoListAdapter(this,getData() );
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 滑动监听
        onTouchListener = new RecyclerTouchListener(this, mRecyclerView);
        // 设定划开的样式以及点击事件
        onTouchListener.setSwipeOptionViews(R.id.edit, R.id.dele)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        String message = "";
                        GoodsInfo goodsInfo = GoodsInfoListAdapter.goodsInfos.get(position);
                        if (viewID == R.id.edit) {
                            message += "Edit";

                            dialogChange(goodsInfo,position);

                        } else if (viewID == R.id.dele) {
                            message += "Dele";
                            GoodsInfoListAdapter.goodsInfos.remove(position);
                            mAdapter.notifyItemRemoved(position);
                            String goodsId =goodsInfo .getGoodsId();
                            goodsInfoLIstController.deleGoodsInfo(goodsId);
                        }
                        message += " clicked for row " + (position + 1);
                        Toast.makeText(GoodsInfoActivityList.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 获取数据信息
    private List<GoodsInfo> getData() {
        List<GoodsInfo> allDiscount = goodsInfoLIstController.getAllGoodsInfo();
        return allDiscount;
    }

    // 添加一条
    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.addOnItemTouchListener(onTouchListener);
    }

    // 移除一条
    @Override
    protected void onPause() {
        super.onPause();
        mRecyclerView.removeOnItemTouchListener(onTouchListener);
    }

    //
    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchListener != null) touchListener.getTouchCoordinates(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_add_discount){
//            dialogAdd();
            startActivity(new Intent(GoodsInfoActivityList.this,AddGoodInfoActivity.class));

        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mAdapter = new GoodsInfoListAdapter(this,getData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void dialogChange(final GoodsInfo goodsInfo, final int position){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("商品管理");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_goods_info, null);
        builder.setView(view);
        final EditText et_discount_name = (EditText) view.findViewById(R.id.et_discount_name);
//        final EditText et_discount_num = (EditText) view.findViewById(R.id.et_discount_num);

        if (goodsInfo != null) {
//            int newStockNum = goodsInfo.getNewStockNum();
            double nowPrice = goodsInfo.getNowPrice();
            et_discount_name.setText(String.valueOf(nowPrice));
//            et_discount_num.setText(String.valueOf(newStockNum));

        }
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String discountNum = et_discount_name.getText().toString();
//                String discountNum = et_discount_num.getText().toString();

//                goodsInfo.setNewStockNum(Double.valueOf(newStockNum));
                goodsInfo.setNowPrice(Double.valueOf(discountNum));
                goodsInfoLIstController.changeGoodsInfo(goodsInfo);

                if (position == -1){
                    GoodsInfoListAdapter.goodsInfos.add(goodsInfo);
                    mAdapter.notifyItemChanged(Init.discounts.size()-1);
                }else {
                    GoodsInfoListAdapter.goodsInfos.set(position,goodsInfo);
                    mAdapter.notifyItemChanged(position);
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }



}
