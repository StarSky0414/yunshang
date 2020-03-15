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
import com.tts.starsky.phonesweepcode.db.bean.GoodsStock;
import com.tts.starsky.phonesweepcode.db.provider.GoodsStockProvider;
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
    private EditText et_intoPriceAll;
    private EditText et_intoGoodsNum;
    private GoodsStockProvider goodsStockProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);//订阅
        super.onCreate(savedInstanceState);
        goodsInfoController = new GoodsInfoController();
        setContentView(R.layout.activity_add_good_info);   // 设定页面布局
        initView();  // 初始化页面信息

        // 尝试获取goodsInfo信息
        String goodsInfoString = getIntent().getStringExtra("goodsInfo");
        if (goodsInfoString !=null && !goodsInfoString.equals("")){
            GoodsInfo goodsInfo = JSON.parseObject(goodsInfoString, GoodsInfo.class);
            et_barcode.setText(goodsInfo.getGoodsBarCode());   // 设定编码信息
        }
    }


    /**
     *  初始化页面信息
     */
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

        et_intoPriceAll = (EditText) findViewById(R.id.et_intoPriceAll);
        et_intoGoodsNum = (EditText) findViewById(R.id.et_intoGoodsNum);

    }

    /**
     *  调取扫码的信息
     *  扫码组件是GooGle的原生提供
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle bundle = data.getExtras();
            theresult = bundle.getString("SCAN_RESULT");
            System.out.println("==============" + theresult);   // 获取到的编码信息
            et_barcode.setText(theresult);

            // 通过编码查询数据库中的商品信息
            goods_info = goodsInfoController.getGoodsInfoByDB(theresult);
            // 如果数据为空，那就是没添加过数据，进行数据录入
            if (goods_info.getGoodsName()==null) {
                et_goodsName.setHint("未添加数据");
                et_goods_stock_num.setHint("0");
            } else {
                // 数据库信息添加到页面上
                et_goodsName.setText(goods_info.getGoodsName());
                et_nowPriceAll.setText(String.valueOf(goods_info.getNowPrice()));
                et_goods_stock_num.setText(String.valueOf(goods_info.getNewStockNum()));
            }
            // 查看商品信息
            System.out.println("===========" + goods_info);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 调取扫码的信息
            case R.id.bu_Scan:
                new IntentIntegrator(this).initiateScan();
                break;
                // 获取商品信息 通过网络接口
            case R.id.bu_getGoodsInfo:
                goodsInfoController.queryGoodsInfoName(theresult);
                break;
                // 提交商品信息
            case R.id.submit_goods_info:
                setGoodsStock();
                String etGoodsName = et_goodsName.getText().toString();
                // 现在售价
                double etNowAllPrice = Double.valueOf(et_nowPriceAll.getText().toString());
                // 库存数量
                Integer goodsStockNum =  et_goods_stock_num.getText().toString().equals("")?0:Integer.valueOf(et_goods_stock_num.getText().toString());

                String etBarcode = et_barcode.getText().toString();

                if (goods_info == null ){
                    goods_info = new GoodsInfo();
                }

                // 添加商品信息 准备存储数据库
                goods_info.setNowPrice(etNowAllPrice);
                goods_info.setGoodsName(etGoodsName);
                goods_info.setNewStockNum(goodsStockNum);
                goods_info.setGoodsBarCode(etBarcode);
                goods_info.setGoodsId(etBarcode);
                // 存储数据库
                goodsInfoController.save(goods_info);

                Toast.makeText(this, "信息入库完毕！", Toast.LENGTH_SHORT).show();
                clean();
                break;
        }
    }

    /**
     * 数据入库
     */
    private void setGoodsStock() {
        // 编码号
        String etBarcodeString = et_barcode.getText().toString();
        // 进货总价
        double etInfoAllPrice = Double.valueOf(et_intoPriceAll==null?"0":et_intoPriceAll.getText().toString());
        // 进货数量
        Integer etIntoGoodNum = Integer.valueOf(et_intoGoodsNum==null?"0":et_intoGoodsNum.getText().toString());

        GoodsStock goodsStock = new GoodsStock();
        goodsStock.setGoodsId(etBarcodeString);
        goodsStock.setIntoStockNum(etIntoGoodNum);
        goodsStock.setIntoStockPrice(etInfoAllPrice / etIntoGoodNum);
        goodsStock.setResidueGoodsNum(goodsStock.getIntoStockNum());

        goodsStockProvider.goodsStockInsert(goods_info, goodsStock);
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
