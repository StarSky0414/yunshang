package com.tts.starsky.phonesweepcode.view;

import android.app.Activity;
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
import com.tts.starsky.phonesweepcode.http.Goods;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 *  商品信息类
 */
public class AddGoodInfoActivity extends Activity implements View.OnClickListener {

    private Button bu_scan;
    private Button bu_getGoodsInfo;
    private TextView submit_goods_info;
    private EditText et_barcode;
    private EditText et_goodsName;
    private EditText et_nowPriceAll;
    private EditText et_goods_stock_num;
    private String theresult;
    private GoodsInfo goods_info;
    private GoodsInfoController goodsInfoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);//订阅
        super.onCreate(savedInstanceState);
        goodsInfoController = new GoodsInfoController();
        setContentView(R.layout.activity_add_good_info);
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
        et_nowPriceAll = (EditText) findViewById(R.id.et_nowPriceAll);
        et_goods_stock_num = (EditText) findViewById(R.id.et_goods_stock_num);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            theresult = bundle.getString("SCAN_RESULT");
            System.out.println("==============" + theresult);
            et_barcode.setText(theresult);

            goods_info = goodsInfoController.getGoodsInfoByDB(theresult);
            if (goods_info.getGoodsName()==null) {
                et_goodsName.setHint("未添加数据");
                et_goods_stock_num.setHint("0");
            } else {
                et_goodsName.setText(goods_info.getGoodsName());
                et_nowPriceAll.setText(String.valueOf(goods_info.getNowPrice()));
                et_goods_stock_num.setText(String.valueOf(goods_info.getNewStockNum()));
            }
            System.out.println("===========" + goods_info);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bu_Scan:
                new IntentIntegrator(this).initiateScan();
                break;
            case R.id.bu_getGoodsInfo:
                goodsInfoController.queryGoodsInfoName(theresult);
                break;
            case R.id.submit_goods_info:
                String etGoodsName = et_goodsName.getText().toString();
                // 现在售价
                double etNowAllPrice = Double.valueOf(et_nowPriceAll.getText().toString());
                // 库存数量
                Integer goodsStockNum =  et_goods_stock_num.getText().toString().equals("")?0:Integer.valueOf(et_goods_stock_num.getText().toString());

                goods_info.setNowPrice(etNowAllPrice);
                goods_info.setGoodsName(etGoodsName);
                goods_info.setNewStockNum(goodsStockNum);
                goodsInfoController.save(goods_info);

                Toast.makeText(this, "信息入库完毕！", Toast.LENGTH_SHORT).show();
                clean();
                break;
        }
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

    /**
     *  入库完数据清理
     */
    private void clean(){
        goods_info = null;
        et_goods_stock_num.setText("");
        et_nowPriceAll.setText("");
        et_barcode.setText("");
        et_goodsName.setText("");
    }
}
