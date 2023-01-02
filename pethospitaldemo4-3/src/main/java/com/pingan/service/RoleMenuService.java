package com.pingan.service;

/**
 * 角色菜单关联Service接口
 */
public interface RoleMenuService {

    /**
     * 根据角色id删除所有关联信息
     *
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(Integer roleId);

    /**
     * 添加角色菜单关联信息
     *
     * @param roleId
     * @param menuId
     * @return
     */
    Integer add(Integer roleId, Integer menuId);
}
