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
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;
import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.adapter.DiscountAdapter;
import com.tts.starsky.phonesweepcode.controller.DiscountController;
import com.tts.starsky.phonesweepcode.db.bean.Discount;
import java.util.List;

public class DiscountActivity extends Activity implements RecyclerTouchListener.RecyclerTouchListenerHelper,View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;
    private DiscountController discountController;
    private DiscountAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        initRecyclerView();

        ImageView iv_add_discount = (ImageView) findViewById(R.id.iv_add_discount);
        iv_add_discount.setOnClickListener(this);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        discountController = new DiscountController();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mAdapter = new DiscountAdapter(this, getData());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        onTouchListener = new RecyclerTouchListener(this, mRecyclerView);
        onTouchListener.setSwipeOptionViews(R.id.edit, R.id.dele)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        String message = "";
                        if (viewID == R.id.edit) {
                            message += "Edit";
                        } else if (viewID == R.id.dele) {
                            message += "Change";
                        }
                        message += " clicked for row " + (position + 1);
                        Toast.makeText(DiscountActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private List<Discount> getData() {
        List<Discount> allDiscount = discountController.getAllDiscount();
        return allDiscount;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.addOnItemTouchListener(onTouchListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRecyclerView.removeOnItemTouchListener(onTouchListener);
    }

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
            dialogAdd();
        }
    }

    private void dialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("商品结算");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_discount, null);
        builder.setView(view);
        final EditText et_discount_name = (EditText) view.findViewById(R.id.et_discount_name);
        final EditText et_discount_num = (EditText) view.findViewById(R.id.et_discount_num);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String discountName = et_discount_name.getText().toString();
                String discountNum = et_discount_num.getText().toString();
                Discount discount = new Discount();
                discount.setDiscountName(discountName);
                discount.setDiscountNum(Integer.valueOf(discountNum));
                discountController.addDiscount(discount);
                Init.discounts.add(discount);
                mAdapter.notifyItemChanged(Init.discounts.size()-1);
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
