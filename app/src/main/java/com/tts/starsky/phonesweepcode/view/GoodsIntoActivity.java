package com.tts.starsky.phonesweepcode.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.zxing.integration.android.IntentIntegrator;
import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.controller.GoodsInfoController;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.bean.GoodsStock;
import com.tts.starsky.phonesweepcode.db.provider.GoodsStockProvider;

public class GoodsIntoActivity extends Activity implements View.OnClickListener, DialogInterface.OnClickListener {

    private Button bu_scan;
    private TextView submit_goods_info;
    private EditText et_barcode;
    private EditText et_goodsName;
    private EditText et_intoPriceAll;
    private EditText et_intoGoodsNum;
    private String theresult;
    private GoodsInfo goods_info;
    private GoodsInfoController goodsInfoController;
    private GoodsStockProvider goodsStockProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goodsInfoController = new GoodsInfoController();
        goodsStockProvider = new GoodsStockProvider();

        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        bu_scan = (Button) findViewById(R.id.bu_Scan);
        bu_scan.setOnClickListener(this);
        submit_goods_info = (TextView) findViewById(R.id.submit_goods_info);
        submit_goods_info.setOnClickListener(this);

        et_barcode = (EditText) findViewById(R.id.et_barcode);
        et_goodsName = (EditText) findViewById(R.id.et_goodsName);
        et_intoPriceAll = (EditText) findViewById(R.id.et_intoPriceAll);
        et_intoGoodsNum = (EditText) findViewById(R.id.et_intoGoodsNum);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            theresult = bundle.getString("SCAN_RESULT");
            System.out.println("==============" + theresult);
            goods_info = goodsInfoController.getGoodsInfoByDB(theresult);
            if (goods_info.getGoodsName() == null) {
                new AlertDialog.Builder(this)
                        .setTitle("无商品信息")
                        .setMessage("未进行商品录入，是否进行商品信息录入！")
                        .setPositiveButton("是", this)
                        .setNegativeButton("否", null)
                        .show();
            } else {
                et_barcode.setText(theresult);
                et_goodsName.setText(goods_info.getGoodsName());
            }

        }
    }


    /**
     * 数据入库
     */
    private void setGoodsStock() {
        // 编码号
        String etBarcodeString = et_barcode.getText().toString();
        // 进货总价
        double etInfoAllPrice = Double.valueOf(et_intoPriceAll.getText().toString());
        // 进货数量
        Integer etIntoGoodNum = Integer.valueOf(et_intoGoodsNum.getText().toString());

        GoodsStock goodsStock = new GoodsStock();
        goodsStock.setGoodsId(etBarcodeString);
        goodsStock.setIntoStockNum(etIntoGoodNum);
        goodsStock.setIntoStockPrice(etInfoAllPrice / etIntoGoodNum);
        goodsStock.setResidueGoodsNum(goodsStock.getIntoStockNum());

        goodsStockProvider.goodsStockInsert(goods_info, goodsStock);
    }


    /**
     * Dialog 弹窗 确认按钮，用于跳转商品信息添加
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {
        Intent intent = new Intent(GoodsIntoActivity.this, GoodsInfoActivity.class);
        String jsonString = JSON.toJSONString(goods_info);
        intent.putExtra("goodsInfo", jsonString);
        startActivity(intent);
        finish();
    }

    /**
     * 按钮点击事件
     *
     * @param v 点击的视图
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bu_Scan:
                new IntentIntegrator(this).initiateScan();
                break;
            case R.id.submit_goods_info:
                if (!noNullCheck()) {
                    Toast.makeText(this, "请填完信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                setGoodsStock();
                et_intoPriceAll.setText("");
                et_intoGoodsNum.setText("");
                et_barcode.setText("");
                et_goodsName.setText("");
                break;
        }
    }

    private boolean noNullCheck() {
        if (et_intoPriceAll.getText().toString().equals("") || et_intoGoodsNum.getText().toString().equals("") || et_barcode.getText().toString().equals("") || et_goodsName.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

}
