package com.pingan.controller;

import com.pingan.entity.*;
import com.pingan.service.*;
import com.pingan.util.DateUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户宠物Controller层
 */
@Controller
@RequestMapping("/pet")
public class PetController {

    @Resource
    private PetService petService;


    /**
     * 添加或者修改宠物信息
     *
     * @param pet
     * @return
     */
    @RequestMapping("/save")
    public String save(HttpSession session, Pet pet) throws Exception {
        if (pet.getId() == null) {
            pet.setCustomer((Customer) session.getAttribute("currentCustomer"));
            petService.add(pet);
        } else {
            String petName1 = petService.findById(pet.getId()).getName();
            petService.update(pet);
            String petName2 = petService.findById(pet.getId()).getName();
            if (!petName1.equals(petName2)) {
                Map<String, Object> map = new HashMap<>(16);
                map.put("petName2", petName1);


            }
        }
        return "redirect:/customer/myPet/list/1";
    }

    /**
     * 删除宠物信息
     *
     * @param petId
     * @return
     */
    @RequestMapping("/deletePet")
    public String delete(Integer petId) {
        petService.delete(petId);
        return "redirect:/customer/myPet/list/1";
    }
}
