package com.tts.starsky.phonesweepcode.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.tts.starsky.phonesweepcode.adapter.DiscountAdapter;
import com.tts.starsky.phonesweepcode.controller.DiscountController;
import com.tts.starsky.phonesweepcode.controller.UserController;
import com.tts.starsky.phonesweepcode.db.bean.Discount;
import java.util.List;

/**
 *  折扣视图
 */
public class DiscountActivity extends Activity implements RecyclerTouchListener.RecyclerTouchListenerHelper,View.OnClickListener {

    private RecyclerView mRecyclerView;
    private RecyclerTouchListener onTouchListener;
    private OnActivityTouchListener touchListener;
    private DiscountController discountController;
    private DiscountAdapter mAdapter;
    private EditText et_discount_name;
    private EditText et_discount_num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount);

        initRecyclerView();

        // 初始化弹窗信息，用于添加数据
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_discount, null);
        et_discount_name = (EditText) view.findViewById(R.id.et_discount_name);
        et_discount_num = (EditText) view.findViewById(R.id.et_discount_num);

        // 设定添加按钮的监听
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

        // 滑动监听
        onTouchListener = new RecyclerTouchListener(this, mRecyclerView);
        // 设定划开的样式以及点击事件
        onTouchListener.setSwipeOptionViews(R.id.edit, R.id.dele)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        String message = "";
                        Discount discount = Init.discounts.get(position);
                        if (viewID == R.id.edit) {
                            message += "Edit";

                            dialogChange(discount,position);
                        } else if (viewID == R.id.dele) {
                            message += "Dele";
                            Init.discounts.remove(position);
                            mAdapter.notifyItemRemoved(position);
                            Long discountId =discount .getDiscountId();
                            discountController.deleDiscount(discountId);
                        }
                        message += " clicked for row " + (position + 1);
                        Toast.makeText(DiscountActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // 获取数据信息
    private List<Discount> getData() {
        List<Discount> allDiscount = discountController.getAllDiscount();
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
            dialogAdd();
        }
    }

    private void dialogChange(Discount discount, final int position){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("商品结算");
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_discount, null);
        builder.setView(view);
        final EditText et_discount_name = (EditText) view.findViewById(R.id.et_discount_name);
        final EditText et_discount_num = (EditText) view.findViewById(R.id.et_discount_num);

        if (discount != null) {
            String discountName = discount.getDiscountName();
            int discountNum = discount.getDiscountNum();
            et_discount_name.setText(discountName);
            et_discount_num.setText(String.valueOf(discountNum));

        }else {
            discount = new Discount();
        }

        final Discount finalDiscount = discount;
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String discountName = et_discount_name.getText().toString();
                String discountNum = et_discount_num.getText().toString();

                finalDiscount.setDiscountName(discountName);
                finalDiscount.setDiscountNum(Integer.valueOf(discountNum));
                finalDiscount.setUserId(UserController.getUserId());
                discountController.changeDiscount(finalDiscount);

                if (position == -1){
                    Init.discounts.add(finalDiscount);
                    mAdapter.notifyItemChanged(Init.discounts.size()-1);
                }else {
                    Init.discounts.set(position,finalDiscount);
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

    private void dialogAdd(){
        dialogChange(null, -1);
    }
}
