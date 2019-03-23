package com.qf.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.mapper.SysMenuMapper;
import com.qf.oa.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/21 18:42
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public IBaseDao<SysMenu> getBaseDao() {
        return sysMenuMapper;
    }

    @Override
    public PageInfo<SysMenu> queryAuthMenuByRoleId(Long roleId, Page page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        List<SysMenu> list = sysMenuMapper.queryAuthMenuByRoleId(roleId);
        PageInfo<SysMenu> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public SysResult delUserFormMenu(Long roleId, Long menuId) {
        SysResult sysResult = new SysResult();
        int res = sysMenuMapper.delUserFormMenu(roleId, menuId);
        if (res > 0) {
            sysResult.setResult(true);
        } else {
            sysResult.setResult(false);
        }
        return sysResult;
    }

    @Override
    public PageInfo<SysMenu> queryNoAuthMenuByRoleId(Long roleId, String menuName, Page page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        List<SysMenu> list = sysMenuMapper.queryNoAuthMenuByRoleId(roleId,menuName);
        PageInfo<SysMenu> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}
