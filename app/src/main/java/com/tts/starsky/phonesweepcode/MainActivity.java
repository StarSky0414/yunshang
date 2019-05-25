package com.tts.starsky.phonesweepcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
//import com.tts.starsky.phonesweepcode.db.DBBase;
//import com.tts.starsky.phonesweepcode.db.DaoSession;
//import com.tts.starsky.phonesweepcode.db.GoodsInfo;
//import com.tts.starsky.phonesweepcode.db.GoodsInfoDao;
import com.tts.starsky.phonesweepcode.controller.GoodsInfoController;
import com.tts.starsky.phonesweepcode.db.DBBase;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.bean.GoodsStock;
import com.tts.starsky.phonesweepcode.db.dao.DaoSession;
import com.tts.starsky.phonesweepcode.db.dao.GoodsInfoDao;
import com.tts.starsky.phonesweepcode.db.provider.GoodsStockProvider;
import com.tts.starsky.phonesweepcode.http.Goods;
import com.tts.starsky.phonesweepcode.utile.OkHttpUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button bu_scan;
    private Button bu_getGoodsInfo;
    private Button submit_goods_info;
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
        EventBus.getDefault().register(this);//订阅
        super.onCreate(savedInstanceState);
        goodsInfoController = new GoodsInfoController();
        goodsStockProvider = new GoodsStockProvider();

        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        bu_scan = (Button) findViewById(R.id.bu_Scan);
        bu_scan.setOnClickListener(this);
        bu_getGoodsInfo = (Button) findViewById(R.id.bu_getGoodsInfo);
        bu_getGoodsInfo.setOnClickListener(this);
        submit_goods_info = (Button) findViewById(R.id.submit_goods_info);
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
            Toast.makeText(this, theresult, Toast.LENGTH_SHORT).show();
            System.out.println("==============" + theresult);
            et_barcode.setText(theresult);
            Toast.makeText(this, "数据请求中，请稍后。", Toast.LENGTH_SHORT).show();
            queryGoodsInfoByDB(theresult);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bu_Scan:
                new IntentIntegrator(this).initiateScan();
                break;
            case R.id.bu_getGoodsInfo:
                OkHttpUtil okHttpUtil = new OkHttpUtil();
//                okHttpUtil.init(theresult);
                break;
            case R.id.submit_goods_info:

                // 编码号
                String etBarcodeString = et_barcode.getText().toString();
                // 进货总价
                double etInfoAllPrice = Double.valueOf(et_intoPriceAll.getText().toString());
                // 进货数量
                Integer etIntoGoodNum = Integer.valueOf(et_intoGoodsNum.getText().toString());

                GoodsStock goodsStock = new GoodsStock();
                goodsStock.setGoodsId(etBarcodeString);
                goodsStock.setIntoStockNum(etIntoGoodNum);
                goodsStock.setIntoStockPrice(etInfoAllPrice/etIntoGoodNum);
                goodsStock.setResidueGoodsNum(goodsStock.getIntoStockNum());

                goodsStockProvider.goodsStockInsert(goods_info, goodsStock);
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

    private void queryGoodsInfoByDB(String barcode) {
        DBBase dbBase = DBBase.getDBBase();
        DaoSession dbSession = dbBase.getDBSession();
        goods_info = dbSession.getGoodsInfoDao().queryBuilder().where(GoodsInfoDao.Properties.GoodsBarCode.eq(barcode)).unique();
        if (goods_info == null) {
            et_goodsName.setHint("未添加数据");
            goods_info.setGoodsBarCode(barcode);
        } else {
            et_goodsName.setText(goods_info.getGoodsName());
        }
        System.out.println("===========" + goods_info);
    }
}
