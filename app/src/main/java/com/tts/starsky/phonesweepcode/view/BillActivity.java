package com.tts.starsky.phonesweepcode.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.tts.starsky.phonesweepcode.bean.Data;
import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.bean.BillBean;
import com.tts.starsky.phonesweepcode.controller.BillController;

import java.util.List;

/**
 * 支付结算
 */
public class BillActivity extends Activity {

    private TextView tv_pipeline_id;
    private TextView tv_create_time;
    private TextView tv_original_price;
    private TextView tv_transfer_of_profits;
    private TextView tv_reality_price;
    private TextView tv_profit;
    private TextView tv_profit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Intent intent = getIntent();
        String dataList = intent.getStringExtra("dataList");
        double stopCut = intent.getDoubleExtra("stopCut",0); //实际收款
        List<Data> data = JSON.parseArray(dataList, Data.class);

        BillController billController = new BillController(data);
        final BillBean billBean = billController.makeBill();
        double effectivelyPrice = billBean.getEffectivelyPrice(); //应付
        final double settlementAndProfitConcession = effectivelyPrice - stopCut; // 结算时候让利部分
        findView();
        tv_pipeline_id.setText(billBean.getId());
        tv_create_time.setText(billBean.getCreateTime());
        tv_original_price.setText(String.format("%.2f", billBean.getPrice()));
        //让利
        tv_transfer_of_profits.setText(String.format("%.2f", billBean.getTransferOfProfits()+settlementAndProfitConcession));
        tv_reality_price.setText(String.format("%.2f", stopCut));
        tv_profit.setText("*******");

        // 结算的获利计算隐藏，长安进行查看
        tv_profit_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                Toast.makeText(BillActivity.this, "" + action, Toast.LENGTH_SHORT).show();
                if (action == MotionEvent.ACTION_DOWN) {

                    Toast.makeText(BillActivity.this, "按下", Toast.LENGTH_SHORT).show();
                    tv_profit.setText(String.format("%.2f", billBean.getProfit() - settlementAndProfitConcession));
// 按下 处理相关逻辑
                } else {
                    Toast.makeText(BillActivity.this, "松开", Toast.LENGTH_SHORT).show();
                    tv_profit.setText("*******");
//松开 处理相关逻辑
                }
                return true;
            }

        });
    }

    /**
     *  页面信息初始化
     */
    private void findView() {
        tv_pipeline_id = (TextView) findViewById(R.id.tv_pipeline_id);
        tv_create_time = (TextView) findViewById(R.id.tv_create_time);
        tv_original_price = (TextView) findViewById(R.id.tv_original_price);
        tv_transfer_of_profits = (TextView) findViewById(R.id.tv_transfer_of_profits);
        tv_reality_price = (TextView) findViewById(R.id.tv_reality_price);
        tv_profit = (TextView) findViewById(R.id.tv_profit);
        tv_profit_text = (TextView) findViewById(R.id.tv_profit_text);
    }

}
