package com.pingan.service;

import com.pingan.entity.GoodsType;

import java.util.List;

/**
 * 商品类别Service接口
 */
public interface GoodsTypeService {

    /**
     * 根据父节点查找所有子节点
     *
     * @param parentId
     * @return
     */
    List<GoodsType> findByParentId(Integer parentId);

    /**
     * 添加商品类别
     *
     * @param goodsType
     * @return
     */
    Integer add(GoodsType goodsType);

    /**
     * 修改商品类别
     *
     * @param goodsType
     * @return
     */
    Integer update(GoodsType goodsType);

    /**
     * 删除商品列表
     *
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据id查询实体
     *
     * @param id
     * @return
     */
    GoodsType findById(Integer id);
}
