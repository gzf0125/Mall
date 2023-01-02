package com.pingan.mapper;

import com.pingan.entity.Pet;

import java.util.List;
import java.util.Map;

/**
 * 客户宠物Mapper接口
 */
public interface PetMapper {

    /**
     * 分页分条件查询
     *
     * @param map
     * @return
     */
    List<Pet> list(Map<String, Object> map);

    /**
     * 获取记录数
     *
     * @param map
     * @return
     */
    Long getCount(Map<String, Object> map);

    /**
     * 添加宠物信息
     *
     * @param pet
     * @return
     */
    Integer add(Pet pet);

    /**
     * 修改宠物信息
     *
     * @param pet
     * @return
     */
    Integer update(Pet pet);

    /**
     * 删除宠物信息
     *
     * @param id
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据id查找实体
     *
     * @param id
     * @return
     */
    Pet findById(Integer id);

    /**
     * 根据宠物名查找宠物
     *
     * @param name
     * @return
     */
    Pet findByName(String name);
}
