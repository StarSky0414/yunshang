package com.tts.starsky.phonesweepcode.db.provider;

import com.tts.starsky.phonesweepcode.db.bean.GoodsTypeInfo;
import com.tts.starsky.phonesweepcode.db.dao.GoodsTypeInfoDao;

import java.util.List;

/**
 * 提供商品类型数据库操作
 */
public class GoodsTypeInfoProvider extends DBProviderBase {

    private final GoodsTypeInfoDao goodsTypeInfoDao;
    public static final int ONERANK = 1;
    public static final int TWORANK = 2;
    public static final int THREERANK = 3;

    public GoodsTypeInfoProvider() {
        goodsTypeInfoDao = dbSession.getGoodsTypeInfoDao();
    }

    /**
     * 分类插入基础设定
     */
    private void goodsTypeInsertBase(GoodsTypeInfo goodsTypeInfo) {
        goodsTypeInfoDao.insertOrReplace(goodsTypeInfo);
    }

    /**
     * 插入一级分类 【由系统完成】
     *
     * @param goodsTypeName 插入分类名称
     * @param photoPath     插入图片路径 【网络/本地】
     */
    public void goodsTypeInsertOne(String goodsTypeName, String photoPath, long concreteId) {
        GoodsTypeInfo goodsTypeInfo = goodsTypeInfoBaseSet(goodsTypeName, 0, photoPath);
        goodsTypeInfo.setType_concrete_id(concreteId);
        goodsTypeInfo.setRank(ONERANK);
        goodsTypeInsertBase(goodsTypeInfo);
    }

    /**
     * 插入一级分类 【由系统完成】
     *
     * @param goodsTypeName 插入分类名称
     */
    public void goodsTypeInsertOne(String goodsTypeName, long concreteId) {
        GoodsTypeInfo goodsTypeInfo = goodsTypeInfoBaseSet(goodsTypeName, 0, null);
        goodsTypeInfo.setType_concrete_id(concreteId);
        goodsTypeInfo.setRank(ONERANK);
        goodsTypeInsertBase(goodsTypeInfo);
    }

    /**
     * 插入二级分类
     *
     * @param goodsTypeName 插入分类名称
     * @param photoPath     插入图片路径 【网络/本地】
     */
    public void goodsTypeInsertTwo(String goodsTypeName, long typeFatherId, String photoPath) {
        GoodsTypeInfo goodsTypeInfo = goodsTypeInfoBaseSet(goodsTypeName, typeFatherId, photoPath);
        goodsTypeInfo.setRank(TWORANK);
        goodsTypeInsertBase(goodsTypeInfo);
    }

    /**
     * 插入二级分类
     *
     * @param goodsTypeName 插入分类名称
     * @param typeFatherId  插入分类父类id  root为0
     */
    public void goodsTypeInsertTwo(String goodsTypeName, long typeFatherId) {
        GoodsTypeInfo goodsTypeInfo = goodsTypeInfoBaseSet(goodsTypeName, typeFatherId, null);
        goodsTypeInfo.setRank(TWORANK);
        goodsTypeInsertBase(goodsTypeInfo);
    }

    /**
     * 插入三级分类
     *
     * @param goodsTypeName 插入分类名称
     * @param typeFatherId  插入分类父类id  root为0
     * @param photoPath     插入图片路径 【网络/本地】
     */
    public void goodsTypeInsertThree(String goodsTypeName, long typeFatherId, String photoPath) {
        GoodsTypeInfo goodsTypeInfo = goodsTypeInfoBaseSet(goodsTypeName, typeFatherId, photoPath);
        goodsTypeInfo.setRank(THREERANK);
        goodsTypeInsertBase(goodsTypeInfo);
    }

    /**
     * 插入三级分类
     *
     * @param goodsTypeName 插入分类名称
     * @param typeFatherId  插入分类父类id  root为0
     */
    public void goodsTypeInsertThree(String goodsTypeName, long typeFatherId) {
        GoodsTypeInfo goodsTypeInfo = goodsTypeInfoBaseSet(goodsTypeName, typeFatherId, null);
        goodsTypeInfo.setRank(THREERANK);
        goodsTypeInsertBase(goodsTypeInfo);
    }

    /**
     * 插入分类基础信息设定
     *
     * @param goodsTypeName 插入分类名称
     * @param typeFatherId  插入分类父类id  root为0
     * @param photoPath     插入图片路径 【网络/本地】
     * @return 分类信息
     */
    private GoodsTypeInfo goodsTypeInfoBaseSet(String goodsTypeName, long typeFatherId, String photoPath) {
        GoodsTypeInfo goodsTypeInfo = new GoodsTypeInfo();
        goodsTypeInfo.setType_father_id(typeFatherId);
        goodsTypeInfo.setType_concrete_name(goodsTypeName);
        if (photoPath != null) {
            goodsTypeInfo.setType_concrete_image(photoPath);
        }
        return goodsTypeInfo;
    }

    /**
     * 根据父类id查询分类列表
     *
     * @param fatherId 父类id
     * @return 子类分类列表
     */
    public List<GoodsTypeInfo> queryGoodsTypeListByFatherId(Long fatherId) {
        List<GoodsTypeInfo> goodsTypeInfoList = goodsTypeInfoDao.queryBuilder().where(GoodsTypeInfoDao.Properties.Type_father_id.eq(fatherId)).list();
        return goodsTypeInfoList;
    }
    /**
     * 根据查询根分类列表
     *
     * @return 根分类列表
     */
    public List<GoodsTypeInfo> queryGoodsTypeListRoot() {
        List<GoodsTypeInfo> goodsTypeInfoList = queryGoodsTypeListByFatherId(Long.valueOf(0));
        return goodsTypeInfoList;
    }

    /**
     * 根据id删除分类列表
     *
     * @param id 分类列表id
     */
    public void deleGoodsTypeById(Long id) {
        goodsTypeInfoDao.deleteByKey(id);
    }
}
