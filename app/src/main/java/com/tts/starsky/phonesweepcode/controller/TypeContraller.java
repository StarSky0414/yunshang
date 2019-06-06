package com.tts.starsky.phonesweepcode.controller;

import android.content.Context;

import com.tts.starsky.phonesweepcode.bean.TypeConcreteInfo;
import com.tts.starsky.phonesweepcode.bean.TypeGeneralizeInfo;
import com.tts.starsky.phonesweepcode.db.bean.GoodsTypeInfo;
import com.tts.starsky.phonesweepcode.db.provider.GoodsTypeInfoProvider;

import java.util.ArrayList;
import java.util.List;

public class TypeContraller {

    private Context context;
    private final GoodsTypeInfoProvider goodsTypeInfoProvider;

    public TypeContraller(Context context) {
        this.context = context;
        goodsTypeInfoProvider = new GoodsTypeInfoProvider();
    }

    public List<GoodsTypeInfo> getGeneralizeTypeList(int firstType) {
//        List<TypeGeneralizeInfo> typeGeneralizeInfoList = new ArrayList<>();

        List<GoodsTypeInfo> goodsTypeInfos = goodsTypeInfoProvider.queryGoodsTypeListByFatherId(Long.valueOf(firstType));

//        if(firstType == 1){
//            for (int i = 0; i < 10; i++) {
//                TypeGeneralizeInfo typeGeneralizeInfo = new TypeGeneralizeInfo();
//                typeGeneralizeInfo.setType_generalize_id(i);
//                typeGeneralizeInfo.setType_generalize_name("洗面奶");
//                typeGeneralizeInfo.setFirst_type_id(firstType);
//                typeGeneralizeInfoList.add(typeGeneralizeInfo);
//            }
//        }else if(firstType == 2){
//            for (int i = 10; i < 20; i++) {
//                TypeGeneralizeInfo typeGeneralizeInfo = new TypeGeneralizeInfo();
//                typeGeneralizeInfo.setType_generalize_id(i);
//                typeGeneralizeInfo.setType_generalize_name("精华");
//                typeGeneralizeInfo.setFirst_type_id(firstType);
//                typeGeneralizeInfoList.add(typeGeneralizeInfo);
//            }
//        }else{
//            for (int i = 20; i < 30; i++) {
//                TypeGeneralizeInfo typeGeneralizeInfo = new TypeGeneralizeInfo();
//                typeGeneralizeInfo.setType_generalize_id(i);
//                typeGeneralizeInfo.setType_generalize_name("眼影");
//                typeGeneralizeInfo.setFirst_type_id(firstType);
//                typeGeneralizeInfoList.add(typeGeneralizeInfo);
//            }
//        }



        //TODO 根据一级类别获取商品二级类别信息
        /*DatabaseOP databaseOP = new DatabaseOP(context);
        typeGeneralizeInfoList = databaseOP.getGeneralizeTypeList();*/
        return goodsTypeInfos;
    }

    public List<GoodsTypeInfo> getConcreteTypeList(Long type_generalize_id) {

        List<GoodsTypeInfo> goodsTypeInfos = goodsTypeInfoProvider.queryGoodsTypeListByFatherId(type_generalize_id);

        //TODO 根据二级类别获取商品三级类别信息
        /*DatabaseOP databaseOP = new DatabaseOP(context);
        typeConcreteInfoList = databaseOP.getConcreteTypeList(type_generalize_id);*/
        return goodsTypeInfos;
    }
}
