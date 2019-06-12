package com.tts.starsky.phonesweepcode.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.zxing.integration.android.IntentIntegrator;
import com.tts.starsky.phonesweepcode.R;
import com.tts.starsky.phonesweepcode.controller.GoodsInfoController;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.bean.GoodsStock;
import com.tts.starsky.phonesweepcode.db.provider.GoodsStockProvider;
import com.tts.starsky.phonesweepcode.http.Goods;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    private ImageView iv_close_good_into;
    private Button bu_getGoodsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        goodsInfoController = new GoodsInfoController();
        goodsStockProvider = new GoodsStockProvider();
        setContentView(R.layout.activity_good_into);
        initView();

        String goodsInfoString = getIntent().getStringExtra("goodsInfo");
        if (goodsInfoString !=null && !goodsInfoString.equals("")){
            GoodsInfo goodsInfo = JSON.parseObject(goodsInfoString, GoodsInfo.class);
            et_barcode.setText(goodsInfo.getGoodsBarCode());
        }
    }


    private void initView() {
        bu_scan = (Button) findViewById(R.id.bu_Scan);
        bu_scan.setOnClickListener(this);
        bu_getGoodsInfo = (Button) findViewById(R.id.bu_getGoodsInfo);
        bu_getGoodsInfo.setOnClickListener(this);
        submit_goods_info = (TextView) findViewById(R.id.submit_goods_info);
        submit_goods_info.setOnClickListener(this);

        et_barcode = (EditText) findViewById(R.id.et_barcode);
        et_goodsName = (EditText) findViewById(R.id.et_goodsName);
        et_intoPriceAll = (EditText) findViewById(R.id.et_intoPriceAll);
        et_intoGoodsNum = (EditText) findViewById(R.id.et_intoGoodsNum);
        iv_close_good_into = (ImageView) findViewById(R.id.iv_close_good_into);
        iv_close_good_into.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            theresult = bundle.getString("SCAN_RESULT");
            System.out.println("==============" + theresult);
            et_barcode.setText(theresult);
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
        Intent intent = new Intent(GoodsIntoActivity.this, AddGoodInfoActivity.class);
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
            case R.id.iv_close_good_into:
                finish();
                break;
            case R.id.bu_getGoodsInfo:
                goodsInfoController.queryGoodsInfoName(theresult);
                break;
        }
    }

    private boolean noNullCheck() {
        if (et_intoPriceAll.getText().toString().equals("") || et_intoGoodsNum.getText().toString().equals("") || et_barcode.getText().toString().equals("") || et_goodsName.getText().toString().equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 网络数据请求回调
     *
     * @param goods 商品编码
     */
    @Subscribe
    public void queryResult(Goods goods) {
        String s = goods.toString();
        System.out.println("==========" + s);
        et_goodsName.setText(goods.getGoodsName());
    }

}
