package com.pingan.controller;

import com.pingan.entity.*;
import com.pingan.entity.Goods;
import com.pingan.service.*;
import com.pingan.util.PageUtil;
import com.pingan.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.*;


@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Resource
    private GoodsTypeService goodsTypeService;


    @Resource
    private SaleListGoodsService saleListGoodsService;



    /**
     * 分页分类查询商品信息
     *
     * @param page
     * @param typeId
     * @return
     */
    @RequestMapping("/list/{id}")
    public ModelAndView list(@PathVariable(value = "id", required = false) Integer page, @RequestParam(value = "typeId", required = false) Integer typeId,HttpSession session) {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<>(16);
        map.put("typeId", typeId);
        //每页商品数量
        int pageSize = 6;
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        if (goodsTypeService.findById(typeId).getState() == 1) {
            //根据商品大类分类
            List<Goods> goodsList = goodsService.listByBigTypeId(map);
            Long total = goodsService.getCountByBigTypeId(map);
            mav.addObject("goodsList", goodsList);
            mav.addObject("total", total);
            mav.addObject("pageCode", PageUtil.genPagination("/goods/list", total, page, pageSize, typeId));
        } else {
            //根据商品小类分类
            List<Goods> goodsList = goodsService.list(map);
            Long total = goodsService.getCount(map);
            mav.addObject("goodsList", goodsList);
            mav.addObject("total", total);
            mav.addObject("pageCode", PageUtil.genPagination("/goods/list", total, page, pageSize, typeId));
        }
        //获取商品分类
        List<GoodsType> goodsTypeList = goodsTypeService.findByParentId(1);
        for (GoodsType goodsType : goodsTypeList) {
            goodsType.setSmallGoodsTypeList(goodsTypeService.findByParentId(goodsType.getId()));
        }
        mav.addObject("bigTypeName", goodsTypeService.findById(goodsTypeService.findById(typeId).getpId()).getName());
        mav.addObject("bigTypeId", goodsTypeService.findById(goodsTypeService.findById(typeId).getpId()).getId());
        mav.addObject("typeName", goodsTypeService.findById(typeId).getName());
        mav.addObject("title", "商品列表(" + goodsTypeService.findById(typeId).getName() + ")");
        mav.addObject("goodsTypeList", goodsTypeList);
        mav.addObject("mainPage", "page/goods/goodsList");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }



    /**
     * 查看商品详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/{id}")
    public ModelAndView view(@PathVariable(value = "id", required = false) Integer id, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        Goods goods = goodsService.findById(id);
        List<GoodsType> goodsTypeList = goodsTypeService.findByParentId(1);
        for (GoodsType goodsType : goodsTypeList) {
            goodsType.setSmallGoodsTypeList(goodsTypeService.findByParentId(goodsType.getId()));
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("typeId", goods.getType().getId());
        List<Goods> goodsList = goodsService.list(map);
        Collections.shuffle(goodsList);
        goodsList.remove(goods);
        List<Goods> goodsList1 = new ArrayList<>();
        goodsList1.add(goods);
        mav.addObject("allSaleTotal", saleListGoodsService.getSaleCount(goods.getId()) );
        mav.addObject("bigTypeName", goodsTypeService.findById(goodsTypeService.findById(goods.getType().getId()).getpId()).getName());
        mav.addObject("bigTypeId", goodsTypeService.findById(goodsTypeService.findById(goods.getType().getId()).getpId()).getId());
        mav.addObject("goods", goods);
        mav.addObject("recommendGoodsList", goodsList);
        mav.addObject("goodsTypeList", goodsTypeList);
        mav.addObject("title", goods.getName());
        mav.addObject("mainPage", "page/goods/goodsView");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }



}
