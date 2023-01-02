package com.pingan.service.impl;

import com.pingan.entity.SaleListGoods;
import com.pingan.mapper.SaleListGoodsMapper;
import com.pingan.service.SaleListGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 销售单商品Service实现类
 */
@Service("saleListGoodsService")
public class SaleListGoodsServiceImpl implements SaleListGoodsService {

    @Resource
    private SaleListGoodsMapper saleListGoodsMapper;

    @Override
    public List<SaleListGoods> listBySaleListId(Integer saleListId) {
        return saleListGoodsMapper.listBySaleListId(saleListId);
    }

    @Override
    public Integer deleteBySaleListId(Integer saleListId) {
        return saleListGoodsMapper.deleteBySaleListId(saleListId);
    }

    @Override
    public Long getTotalByGoodsId(Integer goodsId) {
        return saleListGoodsMapper.getTotalByGoodsId(goodsId);
    }

    @Override
    public List<SaleListGoods> list(Map<String, Object> map) {
        return saleListGoodsMapper.list(map);
    }

    @Override
    public Long getCount(Map<String, Object> map) {
        return saleListGoodsMapper.getCount(map);
    }

    @Override
    public Integer add(SaleListGoods saleListGoods) {
        return saleListGoodsMapper.add(saleListGoods);
    }

    @Override
    public Integer update(SaleListGoods saleListGoods) {
        return saleListGoodsMapper.update(saleListGoods);
    }

    @Override
    public Long getSaleCount(Integer goodsId) {
        return saleListGoodsMapper.getSaleCount(goodsId);
    }

    @Override
    public SaleListGoods findById(Integer saleListGoodsId) {
        return saleListGoodsMapper.findById(saleListGoodsId);
    }
}
