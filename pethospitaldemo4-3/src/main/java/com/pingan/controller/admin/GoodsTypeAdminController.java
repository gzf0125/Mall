package com.pingan.controller.admin;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pingan.entity.GoodsType;
import com.pingan.service.GoodsService;
import com.pingan.service.GoodsTypeService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台管理商品类别Controller
 */
@RestController
@RequestMapping("/admin/goodsType")
public class GoodsTypeAdminController {

    @Resource
    private GoodsTypeService goodsTypeService;

    @Resource
    private GoodsService goodsService;






    /**
     * 根据父节点获取所有复选框权限菜单
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/loadTreeInfo")
    @RequiresPermissions(value = {"库存查询"}, logical = Logical.OR)
    public String loadTreeInfo() {
        return getAllByParentId(-1).toString();
    }

    /**
     * 根据父节点id和权限菜单id集合获取所有复选框菜单集合
     *
     * @param parentId
     * @return
     */
    private JsonArray getAllByParentId(Integer parentId) {
        JsonArray jsonArray = this.getByParentId(parentId);
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = (JsonObject) jsonArray.get(i);
            if ("open".equals(jsonObject.get("state").getAsString())) {
                continue;
            } else {
                jsonObject.add("children", getAllByParentId(jsonObject.get("id").getAsInt()));
            }
        }
        return jsonArray;
    }

    /**
     * 根据父节点查询所有子节点
     *
     * @param parentId
     * @return
     */
    private JsonArray getByParentId(Integer parentId) {
        List<GoodsType> goodsTypeList = goodsTypeService.findByParentId(parentId);
        JsonArray jsonArray = new JsonArray();
        for (GoodsType goodsType : goodsTypeList) {
            JsonObject jsonObject = new JsonObject();
            // 节点Id
            jsonObject.addProperty("id", goodsType.getId());
            // 节点名称
            jsonObject.addProperty("text", goodsType.getName());
            if (goodsType.getState() == 1) {
                // 根节点
                jsonObject.addProperty("state", "closed");
            } else {
                // 叶子节点
                jsonObject.addProperty("state", "open");
            }
            // 节点图标
            jsonObject.addProperty("iconCls", goodsType.getIcon());
            // 扩展属性
            JsonObject attributeObject = new JsonObject();
            // 节点状态
            attributeObject.addProperty("state", goodsType.getState());
            jsonObject.add("attributes", attributeObject);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
