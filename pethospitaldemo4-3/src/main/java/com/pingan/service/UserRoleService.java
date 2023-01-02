package com.pingan.service;

/**
 * 用户角色关联service接口
 */
public interface UserRoleService {

    /**
     * 根据用户id删除所有关联信息
     *
     * @param userId
     * @return
     */
    Integer deleteByUserId(Integer userId);

    /**
     * 添加用户角色关联
     *
     * @param roleId
     * @param userId
     * @return
     */
    Integer add(Integer roleId, Integer userId);

    /**
     * 根据角色id删除用户角色关联
     *
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(Integer roleId);
}
