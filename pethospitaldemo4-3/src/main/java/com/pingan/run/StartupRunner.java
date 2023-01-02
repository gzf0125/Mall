package com.pingan.run;

import com.pingan.entity.GoodsType;
import com.pingan.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页数据预缓存
 */
@Component
public class StartupRunner implements CommandLineRunner, ServletContextListener {

    private ServletContext application = null;


    @Resource
    private UserService userService;

    @Resource
    private GoodsTypeService goodsTypeService;

    @Override
    public void run(String... args) throws Exception {

    }

    /**
     * 加载数据到application缓存中
     */
    public void loadData() {

        Map<String,Object> map=new HashMap<>(16);


        //获取医生信息
        map.put("type", 2);
        application.setAttribute("doctorList",userService.list(map));
        //获取商品分类
        List<GoodsType> goodsTypeList = goodsTypeService.findByParentId(1);
        for (GoodsType goodsType : goodsTypeList) {
            goodsType.setSmallGoodsTypeList(goodsTypeService.findByParentId(goodsType.getId()));
        }
        application.setAttribute("goodsTypeList", goodsTypeList);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        application = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
