package com.qf.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysUser;
import com.qf.oa.mapper.SysUserMapper;
import com.qf.oa.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/21 10:45
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public IBaseDao<SysUser> getBaseDao() {
        return sysUserMapper;
    }

    @Override
    public PageInfo<SysUser> queryAuthUserByRoleId(Long roleId, Page page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        List<SysUser> list = sysUserMapper.queryAuthUserByRoleId(roleId);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<SysUser> queryNoAuthUserByRoleId(Long roleId, String userName, Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        List<SysUser> list = sysUserMapper.queryNoAuthUserByRoleId(roleId,userName);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
