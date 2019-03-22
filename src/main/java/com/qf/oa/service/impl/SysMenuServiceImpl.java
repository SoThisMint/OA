package com.qf.oa.service.impl;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.mapper.SysMenuMapper;
import com.qf.oa.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
