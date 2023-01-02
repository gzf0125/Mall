package com.pingan.controller.admin;

import com.pingan.entity.Goods;
import com.pingan.entity.PageBean;
import com.pingan.service.GoodsService;
import com.pingan.service.SaleListGoodsService;
import com.pingan.util.DateUtil;
import com.pingan.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理商品Controller
 */
@RestController
@RequestMapping("/admin/goods")
public class GoodsAdminController {

    @Value("${goodsImageFilePath}")
    private String goodsImageFilePath;

    @Resource
    private GoodsService goodsService;

    @Resource
    private SaleListGoodsService saleListGoodsService;
    /**
     * 根据条件分页查询商品库存信息
     *
     * @param searchGoods
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/listInventory")
    @RequiresPermissions(value = "库存查询")
    public Map<String, Object> listInventory(Goods searchGoods, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        if (searchGoods.getType() != null && searchGoods.getType().getId() != null && searchGoods.getType().getId() != 1) {
            map.put("typeId", searchGoods.getType().getId());
        }
        map.put("codeOrName", searchGoods.getCodeOrName());
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Goods> goodsList = goodsService.listInventory(map);
        Long total = goodsService.getCountInventory(map);
        for (Goods goods : goodsList) {
            long saleNum;
            long returnNum;
            if (saleListGoodsService.getTotalByGoodsId(goods.getId()) == null) {
                saleNum = 0; } else {
                saleNum = saleListGoodsService.getTotalByGoodsId(goods.getId());
            }
            goods.setSaleTotal(saleNum );
        }
        resultMap.put("rows", goodsList);
        resultMap.put("total", total);
        return resultMap;
    }
    /**
     * 根据条件分页查询没有库存的商品信息
     *
     * @param codeOrName
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/listNoInventoryQuantity")
    @RequiresPermissions(value = "库存操作")
    public Map<String, Object> listNoInventoryQuantity(@RequestParam(value = "codeOrName", required = false) String codeOrName, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("codeOrName", codeOrName);
        resultMap.put("rows", goodsService.listNoInventoryQuantityByCodeOrName(map));
        resultMap.put("total", goodsService.getCountNoInventoryQuantityByCodeOrName(map));
        return resultMap;
    }
    /**
     * 根据条件分页查询有库存的商品信息
     *
     * @param codeOrName
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/listHasInventoryQuantity")
    @RequiresPermissions(value = "库存操作")
    public Map<String, Object> listHasInventoryQuantity(@RequestParam(value = "codeOrName", required = false) String codeOrName, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows) {
        PageBean pageBean = new PageBean(page, rows);
        Map<String, Object> resultMap = new HashMap<>(16);
        Map<String, Object> map = new HashMap<>(16);
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        map.put("codeOrName", codeOrName);
        resultMap.put("rows", goodsService.listHasInventoryQuantityByCodeOrName(map));
        resultMap.put("total", goodsService.getCountHasInventoryQuantityByCodeOrName(map));
        return resultMap;
    }





    /**
     * 添加商品到仓库 修改库存以及价格信息
     *
     * @param id
     * @param num
     * @param price
     * @return
     */
    @RequestMapping("/saveStore")
    @RequiresPermissions(value = "库存操作")
    public Map<String, Object> saveStore(Integer id, Integer num, Float price) {
        Map<String, Object> resultMap = new HashMap<>(16);
        Goods goods = goodsService.findById(id);
        goods.setInventoryQuantity(num);
        goods.setPurchasingPrice(price);
        goods.setLastPurchasingPrice(price);
        goods.setState(1);
        goodsService.update(goods);
        resultMap.put("success", true);
        return resultMap;
    }

    /**
     * 删除库存，吧商品的库存设置为0
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteStock")
    @RequiresPermissions(value = "库存操作")
    public Map<String, Object> deleteStock(Integer id) {
        Map<String, Object> resultMap = new HashMap<>(16);
        Goods goods = goodsService.findById(id);
        if (goods.getState() == 2) {
            resultMap.put("success", false);
            resultMap.put("errorInfo", "该商品已经发生单据，不能删除");
        } else {
            goods.setInventoryQuantity(0);
            goodsService.update(goods);
            resultMap.put("success", true);
        }
        return resultMap;
    }
}
