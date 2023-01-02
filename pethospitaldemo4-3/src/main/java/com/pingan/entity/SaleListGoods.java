package com.pingan.entity;

/**
 * 销售单商品实体
 */
public class SaleListGoods {

    /**
     *编号
     */
    private Integer id;
    /**
     *销售单
     */
    private SaleList saleList;
    /**
     * 商品的订单编号
     */
    private Integer saleListId;
    /**
     *商品编码
     */
    private String code;
    /**
     *商品名称
     */
    private String name;
    /**
     *商品型号
     */
    private String model;
    /**
     *商品类别
     */
    private GoodsType type;
    /**
     *类别id
     */
    private Integer typeId;
    /**
     *商品id
     */
    private Integer goodsId;
    /**
     * 商品实体
     */
    private Goods goods;
    /**
     *商品单位
     */
    private String unit;
    /**
     *单价
     */
    private float price;
    /**
     *数量
     */
    private int num;
    /**
     *总金额
     */
    private float total;
    /**
     *查询用到 根据商品编码或者商品名称查询
     */
    private String codeOrName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SaleList getSaleList() {
        return saleList;
    }

    public void setSaleList(SaleList saleList) {
        this.saleList = saleList;
    }

    public Integer getSaleListId() {
        return saleListId;
    }

    public void setSaleListId(Integer saleListId) {
        this.saleListId = saleListId;
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

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCodeOrName() {
        return codeOrName;
    }

    public void setCodeOrName(String codeOrName) {
        this.codeOrName = codeOrName;
    }



    @Override
    public String toString() {
        return "SaleListGoods{" +
                "id=" + id +
                ", saleList=" + saleList +
                ", saleListId=" + saleListId +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", type=" + type +
                ", typeId=" + typeId +
                ", goodsId=" + goodsId +
                ", goods=" + goods +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", total=" + total +
                ", codeOrName='" + codeOrName + '\'' +
                '}';
    }
}
