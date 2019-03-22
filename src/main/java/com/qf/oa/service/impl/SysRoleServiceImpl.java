package com.qf.oa.service.impl;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysRole;
import com.qf.oa.mapper.SysRoleMapper;
import com.qf.oa.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/20 21:32
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public IBaseDao<SysRole> getBaseDao() {
        return sysRoleMapper;
    }

}
