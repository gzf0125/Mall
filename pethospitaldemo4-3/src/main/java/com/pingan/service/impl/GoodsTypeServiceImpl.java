package com.pingan.service.impl;

import com.pingan.entity.GoodsType;
import com.pingan.mapper.GoodsTypeMapper;
import com.pingan.service.GoodsTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品类别Service实现类
 */
@Service("goodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Resource
    private GoodsTypeMapper goodsTypeMapper;

    @Override
    public List<GoodsType> findByParentId(Integer parentId) {
        return goodsTypeMapper.findByParentId(parentId);
    }

    @Override
    public Integer add(GoodsType goodsType) {
        return goodsTypeMapper.add(goodsType);
    }

    @Override
    public Integer update(GoodsType goodsType) {
        return goodsTypeMapper.update(goodsType);
    }

    @Override
    public Integer delete(Integer id) {
        return goodsTypeMapper.delete(id);
    }

    @Override
    public GoodsType findById(Integer id) {
        return goodsTypeMapper.findById(id);
    }
}
