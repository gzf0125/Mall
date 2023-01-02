package com.pingan.service.impl;

import com.pingan.mapper.UserRoleMapper;
import com.pingan.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户角色关联Service实现类
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public Integer deleteByUserId(Integer userId) {
        return userRoleMapper.deleteByUserId(userId);
    }

    @Override
    public Integer add(Integer roleId, Integer userId) {
        return userRoleMapper.add(roleId, userId);
    }

    @Override
    public Integer deleteByRoleId(Integer roleId) {
        return userRoleMapper.deleteByRoleId(roleId);
    }
}
