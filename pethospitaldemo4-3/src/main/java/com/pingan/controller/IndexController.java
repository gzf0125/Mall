package com.pingan.controller;

import com.pingan.entity.GoodsType;
import com.pingan.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 首页Controller
 */
@Controller
public class IndexController implements CommandLineRunner, ServletContextListener {

    @Override
    public void run(String... args) throws Exception {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        application = sce.getServletContext();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private ServletContext application = null;

    @Resource
    private UserService userService;

    @Resource
    private GoodsTypeService goodsTypeService;

    /**
     * 首页地址
     *
     * @return
     */
    @RequestMapping("/")
    public ModelAndView root() {
        ModelAndView mav = new ModelAndView();
        this.loadSomeData();
        mav.addObject("title", "首页");
        mav.addObject("mainPage", "page/indexFirst");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 用户注册地址
     *
     * @return
     */
    @RequestMapping("/register")
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "用户注册");
        mav.addObject("mainPage", "page/register");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 用户登录
     *
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "用户登录");
        mav.addObject("mainPage", "page/login");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 用户购物车
     *
     * @return
     */
    @RequestMapping("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "我的购物车");
        mav.addObject("mainPage", "page/goods/shoppingCart");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到联系我们
     *
     * @return
     */
    @RequestMapping("/contact")
    public ModelAndView contact() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "联系我们");
        mav.addObject("mainPage", "page/contact");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    public void loadSomeData() {
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
}
