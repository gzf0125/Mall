package com.pingan.service.impl;

import com.pingan.entity.Role;
import com.pingan.mapper.RoleMapper;
import com.pingan.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 角色Service实现类
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findByUserId(Integer id) {
        return roleMapper.findByUserId(id);
    }

    @Override
    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    @Override
    public List<Role> listAll() {
        return roleMapper.listAll();
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleMapper.findByRoleName(roleName);
    }

    @Override
    public List<Role> list(Map<String, Object> map) {
        return roleMapper.list(map);
    }

    @Override
    public Long getCount(Map<String, Object> map) {
        return roleMapper.getCount(map);
    }

    @Override
    public Integer add(Role role) {
        return roleMapper.add(role);
    }

    @Override
    public Integer update(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public Integer delete(Integer id) {
        return roleMapper.delete(id);
    }
}
