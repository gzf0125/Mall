package com.pingan.controller;

import com.pingan.entity.User;
import com.pingan.service.UserService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 客户预约单Controller层
 */
@Controller
@RequestMapping("/reservation")
public class ReservationController {
    @Resource
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(true);
        //true:允许输入空值，false:不能为空值
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * 查看可预约的医生
     *
     * @return
     */
    @RequestMapping("/doctorCanReserve")
    public ModelAndView doctorCanReserve() {
        ModelAndView mav = new ModelAndView();
        int total=0;
        List<User> userList = userService.canReserve();
        for (User user : userList) {
            if (user.getType()==2) {
                total++;
            }
        }
        mav.addObject("total", total);
        mav.addObject("userList", userList);
        mav.addObject("title", "可预约的医生");
        mav.addObject("mainPage", "page/reservation/doctorCanReserve");
        mav.addObject("mainPageKey", "#b");
        mav.setViewName("index");
        return mav;
    }


}
