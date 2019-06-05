package com.tts.starsky.phonesweepcode.controller;

import android.content.Context;

import com.tts.starsky.phonesweepcode.bean.TypeConcreteInfo;
import com.tts.starsky.phonesweepcode.bean.TypeGeneralizeInfo;

import java.util.ArrayList;
import java.util.List;

public class TypeContraller {

    private Context context;

    public TypeContraller(Context context) {
        this.context = context;
    }

    public List<TypeGeneralizeInfo> getGeneralizeTypeList(int firstType) {
        List<TypeGeneralizeInfo> typeGeneralizeInfoList = new ArrayList<>();

        if(firstType == 1){
            for (int i = 0; i < 10; i++) {
                TypeGeneralizeInfo typeGeneralizeInfo = new TypeGeneralizeInfo();
                typeGeneralizeInfo.setType_generalize_id(i);
                typeGeneralizeInfo.setType_generalize_name("洗面奶");
                typeGeneralizeInfo.setFirst_type_id(firstType);
                typeGeneralizeInfoList.add(typeGeneralizeInfo);
            }
        }else if(firstType == 2){
            for (int i = 10; i < 20; i++) {
                TypeGeneralizeInfo typeGeneralizeInfo = new TypeGeneralizeInfo();
                typeGeneralizeInfo.setType_generalize_id(i);
                typeGeneralizeInfo.setType_generalize_name("精华");
                typeGeneralizeInfo.setFirst_type_id(firstType);
                typeGeneralizeInfoList.add(typeGeneralizeInfo);
            }
        }else{
            for (int i = 20; i < 30; i++) {
                TypeGeneralizeInfo typeGeneralizeInfo = new TypeGeneralizeInfo();
                typeGeneralizeInfo.setType_generalize_id(i);
                typeGeneralizeInfo.setType_generalize_name("眼影");
                typeGeneralizeInfo.setFirst_type_id(firstType);
                typeGeneralizeInfoList.add(typeGeneralizeInfo);
            }
        }



        //TODO 根据一级类别获取商品二级类别信息
        /*DatabaseOP databaseOP = new DatabaseOP(context);
        typeGeneralizeInfoList = databaseOP.getGeneralizeTypeList();*/
        return typeGeneralizeInfoList;
    }

    public List<TypeConcreteInfo> getConcreteTypeList(int type_generalize_id) {
        List<TypeConcreteInfo> typeConcreteInfoList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            TypeConcreteInfo typeConcreteInfo = new TypeConcreteInfo();
            typeConcreteInfo.setType_concrete_id(i);
            typeConcreteInfo.setType_generalize_id(type_generalize_id);
            typeConcreteInfo.setType_concrete_name("氨基酸");
            typeConcreteInfoList.add(typeConcreteInfo);
        }

        //TODO 根据二级类别获取商品三级类别信息
        /*DatabaseOP databaseOP = new DatabaseOP(context);
        typeConcreteInfoList = databaseOP.getConcreteTypeList(type_generalize_id);*/
        return typeConcreteInfoList;
    }
}
