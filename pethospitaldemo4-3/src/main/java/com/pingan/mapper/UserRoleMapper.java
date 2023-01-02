package com.pingan.mapper;

/**
 * 用户角色关联mapper接口
 */
public interface UserRoleMapper {

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
