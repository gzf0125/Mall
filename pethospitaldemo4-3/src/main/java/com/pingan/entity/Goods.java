package com.pingan.entity;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private Integer id;
    /**
     * 商品编码
     */
    private String code;
    /**
     * 商品名称
     */
    @NotEmpty(message = "请输入要搜索的商品名称!")
    private String name;
    /**
     * 图片名
     */
    private String imageName;
    /**
     * 商品型号
     */
    private String model;
    /**
     * 商品类别
     */
    private GoodsType type;
    /**
     * 商品单位
     */
    private String unit;
    /**
     * 上次采购价格
     */
    private float lastPurchasingPrice;
    /**
     * 采购价格  成本价  假如价格变动 算平均值
     */
    private float purchasingPrice;
    /**
     * 出售价格
     */
    private float sellingPrice;
    /**
     * 库存数量
     */
    private int inventoryQuantity;
    /**
     * 库存下限
     */
    private int minNum;
    /**
     * 0 初始化状态 1 库存操作入仓库  2  有进货或者销售单据
     */
    private int state;

    /**
     * 查询用到 根据商品编码或者商品名称查询
     */
    private String codeOrName;
    /**
     * 销售总数
     */
    private Long saleTotal;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public GoodsType getType() {
        return type;
    }

    public void setType(GoodsType type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getLastPurchasingPrice() {
        return lastPurchasingPrice;
    }

    public void setLastPurchasingPrice(float lastPurchasingPrice) {
        this.lastPurchasingPrice = lastPurchasingPrice;
    }

    public float getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(float purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getInventoryQuantity() {
        return inventoryQuantity;
    }

    public void setInventoryQuantity(int inventoryQuantity) {
        this.inventoryQuantity = inventoryQuantity;
    }

    public int getMinNum() {
        return minNum;
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCodeOrName() {
        return codeOrName;
    }

    public void setCodeOrName(String codeOrName) {
        this.codeOrName = codeOrName;
    }

    public Long getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(Long saleTotal) {
        this.saleTotal = saleTotal;
    }


    @Override
    public String toString() {
        return "--{" +
                "id=" + id +
                ", 编号='" + code + '\'' +
                ", 商品名称='" + name + '\'' +
                ", 上次采购价格=" + lastPurchasingPrice +
                ", 采购价格=" + purchasingPrice +
                ", 出售价格=" + sellingPrice +
                '}';
    }
}
