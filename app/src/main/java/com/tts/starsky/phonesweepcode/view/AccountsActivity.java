package com.tts.starsky.phonesweepcode.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.zxing.integration.android.IntentIntegrator;
import com.tts.starsky.phonesweepcode.bean.Data;
import com.tts.starsky.phonesweepcode.Init;
import com.tts.starsky.phonesweepcode.adapter.AccountsAdapter;
import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.controller.PlaceOrderController;
import com.tts.starsky.phonesweepcode.db.bean.Discount;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.provider.GoodsInfoProvider;

import java.util.ArrayList;
import java.util.List;

public class AccountsActivity extends Activity implements View.OnClickListener{

    private AccountsAdapter accountsAdapter;
    private PlaceOrderController placeOrderController;
    private ImageView iv_goto_balance;
    private ImageView iv_scan;
    private TextView tv_balance_base;
    private String theresult;
    private EditText et_goods_num;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        placeOrderController = new PlaceOrderController();
        setContentView(R.layout.recycler_view_fragment);
        findView();
        RecyclerView view = findViewById(R.id.recycle_view_item);
        ((DefaultItemAnimator) view.getItemAnimator()).setSupportsChangeAnimations(false);
        view.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Data> list = new ArrayList<>();

        // 下拉列表的arrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,
                getData());

        accountsAdapter = new AccountsAdapter(list, this, adapter,tv_balance_base);
        view.setAdapter(accountsAdapter);

        tv_balance_base.setText(accountsAdapter.getExpression());
    }

    private void findView(){
        iv_goto_balance = (ImageView) findViewById(R.id.iv_goto_balance);
        iv_goto_balance.setOnClickListener(this);

        iv_scan = (ImageView) findViewById(R.id.iv_scan);
        iv_scan.setOnClickListener(this);

        tv_balance_base = (TextView) findViewById(R.id.tv_balance_base);

    }

    /**
     *  下拉列表数据转换
     * @return  arrayAdapter使用的List
     */
    private List<String> getData() {
        List<Discount> discountData = Init.discounts;
        List<String> dataList = new ArrayList<String>();

        for (Discount discount : discountData) {
            String discountName = discount.getDiscountName();
            dataList.add(discountName);
        }
        return dataList;
    }


    @Override
    public void onClick(View v) {
        int tag = 0;
        try {
            // 当前第几个
            tag = (int) v.getTag();
        }catch (NullPointerException n){ }
        // 哪个控件点击了
        switch (v.getId()) {
            case R.id.iv_goods_num_add:
                int oldNum = accountsAdapter.getGoodsNum(tag);
                int num = accountsAdapter.addGoodsNum(tag);
                if (oldNum == num){
                    Toast.makeText(this, "请检查库存数量", Toast.LENGTH_SHORT).show();
                    return;
                }
                accountsAdapter.notifyItemChanged(tag);
                AccountsAdapter.flashSign=true;
                tv_balance_base.setText(accountsAdapter.getExpression());
                break;
            case R.id.iv_goods_num_cut:
                int i = accountsAdapter.cutGoodsNum(tag);
                // 小于0 移除元素
                if (i <= 0) {
                    accountsAdapter.removeGoods(tag);
                } else {
                    accountsAdapter.notifyItemChanged(tag);
                    AccountsAdapter.flashSign=true;
                }
                tv_balance_base.setText(accountsAdapter.getExpression());
                break;
            case R.id.iv_scan:
                new IntentIntegrator(this).initiateScan();
                break;
            case R.id.iv_goto_balance:
                String jsonString = JSON.toJSONString(accountsAdapter.getItemDataList());
                Intent intent = new Intent(this, BillActivity.class);
                intent.putExtra("dataList",jsonString);
                startActivity(intent);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            String theresult = bundle.getString("SCAN_RESULT");
            Toast.makeText(this, theresult, Toast.LENGTH_SHORT).show();
            System.out.println("==============" + theresult);
//            et_barcode.setText(theresult);
            Toast.makeText(this, "数据请求中，请稍后。", Toast.LENGTH_SHORT).show();
//            queryGoodsInfoByDB(theresult);
//            GoodsInfo goodsInfo = new GoodsInfo(theresult, theresult, "冰红茶", 10, 20);
            GoodsInfoProvider goodsInfoProvider = new GoodsInfoProvider();
            GoodsInfo goodsInfo = goodsInfoProvider.goodsQueryByBrCode(theresult);
            accountsAdapter.addItemList(goodsInfo.getGoodsName(),goodsInfo.getNowPrice(),goodsInfo.getGoodsBarCode(),goodsInfo.getNewStockNum());

        }
    }

}
