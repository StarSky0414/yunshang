package com.tts.starsky.phonesweepcode.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tts.starsky.phonesweepcode.utile.OkHttpUtil;
import com.tts.starsky.phonesweepcode.db.bean.GoodsInfo;
import com.tts.starsky.phonesweepcode.db.provider.GoodsInfoProvider;
import com.tts.starsky.phonesweepcode.http.Goods;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 商品信息控制层
 */
public class GoodsInfoController implements Callback{

    private final GoodsInfoProvider goodsInfoProvider;

    public GoodsInfoController() {
        goodsInfoProvider = new GoodsInfoProvider();

    }

    /**
     * 显示所有商品列表
     * @return
     */
    public List<GoodsInfo> showAllGoodsInfoList() {
        Long userId = UserController.getUserId();
        List<GoodsInfo> goodsInfoList =  goodsInfoProvider.showAllGoodsInfoList(userId);
        return goodsInfoList;
    }

    /**
     * 显示单个商品信息
     */
    public GoodsInfo showGoodsInfo(String brCode) {
        Long userId = UserController.getUserId();
        GoodsInfo goodsInfo = goodsInfoProvider.goodsQueryByBrCode(brCode,userId);
        return goodsInfo;
    }

    public void save(GoodsInfo goodsInfo) {
        goodsInfo.setUserId(Long.parseLong(UserController.getFatherUserId()));
        goodsInfoProvider.goodsInfoInsert(goodsInfo);
    }

    public GoodsInfo getGoodsInfoByDB(String theresult) {
        Long userId = UserController.getUserId();
        GoodsInfo goods_info = goodsInfoProvider.goodsQueryByBrCode(theresult,userId);
        if (goods_info == null) {
            goods_info = new GoodsInfo();
            goods_info.setGoodsBarCode(theresult);
            goods_info.setGoodsId(theresult);
            goods_info.setNewStockNum(0);
        }
        return goods_info;
    }

    public void queryGoodsInfoName(String code){
        String url = "http://www.mxnzp.com/api/barcode/goods/details?barcode=" + code;

        OkHttpUtil okHttpUtil = new OkHttpUtil();
        okHttpUtil.getRequest(url,this);
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        System.out.println("~~~~~~~~~~~~~~~~~`12");
        ResponseBody body = response.body();
        String string = body.string();
        System.out.println("======="+string);
        JSONObject jsonObject = JSON.parseObject(string);
        Integer integer = jsonObject.getInteger("code");
        String data = jsonObject.getString("data");
        if (integer == 1) {
            Goods goods = JSON.parseObject(data, Goods.class);
            EventBus.getDefault().post(goods);
            System.out.println("===========" + string);
        }
    }
}
