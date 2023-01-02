package com.pingan.controller;

import com.pingan.entity.*;
import com.pingan.service.*;
import com.pingan.util.PageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台客户Controller层
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Value("${customerImageFilePath}")
    private String customerImageFilePath;

    @Resource
    private CustomerService customerService;

    @Resource
    private PetService petService;



    /**
     * 添加或者修改客户信息
     *
     * @param customer
     * @return
     */
    @RequestMapping("/save")
    public ModelAndView save(Customer customer,  HttpSession session) throws Exception {

        if (customer.getId() == null) {
            customerService.add(customer);
            ModelAndView mav = new ModelAndView("redirect:/login");
            mav.addObject("successRegister", true);
            mav.addObject("title", "用户登录");
            mav.addObject("mainPage", "page/login");
            mav.addObject("mainPageKey", "#b");
            return mav;
        } else {
            String contact1 = customerService.findById(customer.getId()).getContact();
            customerService.update(customer);
            String contact2 = customerService.findById(customer.getId()).getContact();
            if (!contact1.equals(contact2)) {
                Map<String, Object> map = new HashMap<>(16);
                map.put("customerName2", contact1);



            }
            ModelAndView mav = new ModelAndView("redirect:/customer/personalCenter");
            mav.addObject("successModify", true);
            mav.addObject("title", "个人中心");
            mav.addObject("mainPage", "page/customer/personalCenterFirst");
            customer.setImageName(customerService.findById(customer.getId()).getImageName());
            session.setAttribute("currentCustomer", customer);
            mav.addObject("mainPageKey", "#b");
            return mav;
        }
    }

    /**
     * 客户登录
     *
     * @param customer
     * @param bindingResult
     * @param session
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(@Valid Customer customer, BindingResult bindingResult, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mav.addObject("customer", customer);
            mav.addObject("error", bindingResult.getFieldError().getDefaultMessage());
            mav.addObject("title", "用户登录");
            mav.addObject("mainPage", "page/login");
        } else {
            List<Customer> customerList = customerService.findByUserName(customer.getUserName());
            if (customerList.size() != 0) {
                Customer currentCustomer = customerService.findByUserName(customer.getUserName()).get(0);
                if (currentCustomer.getPassword().equals(customer.getPassword())) {
                    session.setAttribute("currentCustomer", currentCustomer);
                    mav.addObject("successLogin", true);
                    mav.addObject("title", "首页");
                    mav.addObject("mainPage", "page/indexFirst");
                } else {
                    mav.addObject("successLogin", false);
                    mav.addObject("title", "用户登录");
                    mav.addObject("mainPage", "page/login");
                }
            } else {
                mav.addObject("successLogin", false);
                mav.addObject("title", "用户登录");
                mav.addObject("mainPage", "page/login");
            }
        }
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 退出登录返回的页面
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("currentCustomer");
        ModelAndView mav = new ModelAndView("");
        mav.addObject("title", "用户登录");
        mav.addObject("mainPage", "page/indexFirst");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到修改个人中心页面
     *
     * @return
     */
    @RequestMapping("/personalCenter")
    public ModelAndView personalCenter() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "个人中心");
        mav.addObject("mainPage", "page/customer/personalCenterFirst");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 跳转到修改个人信息页面
     *
     * @return
     */
    @RequestMapping("/personalCenter/ModifyMessage")
    public ModelAndView personalCenterModifyMessage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "修改个人信息");
        mav.addObject("mainPage", "page/customer/personalCenterModifyMessage");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 客户注册时判断用户名是否已经存在
     *
     * @param userName
     * @return
     */
    @ResponseBody
    @RequestMapping("/existUserWithUserName")
    public Map<String, Object> existUserWithUserName(String userName) {
        Map<String, Object> resultMap = new HashMap<>(16);
        Long count = customerService.getCountByUserName(userName);
        if (count != 0) {
            resultMap.put("success", true);
        } else {
            resultMap.put("success", false);
        }
        return resultMap;
    }

    /**
     * 查看我的宠物
     *
     * @return
     */
    @RequestMapping("/myPet/list/{id}")
    public ModelAndView myPet(@PathVariable(value = "id", required = false) Integer page, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> map = new HashMap<>(16);
        int pageSize = 3;
        map.put("start", (page - 1) * pageSize);
        map.put("size", pageSize);
        map.put("customer", session.getAttribute("currentCustomer"));
        List<Pet> petList = petService.list(map);
        Long total = petService.getCount(map);
        mav.addObject("petList", petList);
        mav.addObject("total", total);
        mav.addObject("pageCode", PageUtil.genPagination2("/customer/myPet/list", total, page, pageSize));
        mav.addObject("title", "我的宠物");
        mav.addObject("mainPage", "page/customer/myPet");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 添加宠物信息页面
     *
     * @return
     */
    @RequestMapping("/petAdd")
    public ModelAndView petAdd() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("title", "添加宠物信息");
        mav.addObject("mainPage", "page/customer/petAdd");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 修改宠物信息页面
     *
     * @param petId
     * @return
     */
    @RequestMapping("/petModify")
    public ModelAndView petModify(Integer petId) {
        Pet pet = petService.findById(petId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("pet", pet);
        mav.addObject("title", "修改宠物信息");
        mav.addObject("mainPage", "page/customer/petModify");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }

    /**
     * 查看宠物信息页面
     *
     * @param petId
     * @return
     */
    @RequestMapping("/petDetails")
    public ModelAndView petDetails(Integer petId) {
        Pet pet = petService.findById(petId);
        ModelAndView mav = new ModelAndView();
        mav.addObject("pet", pet);
        mav.addObject("title", "查看宠物信息");
        mav.addObject("mainPage", "page/customer/petDetails");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }
}
