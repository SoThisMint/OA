package com.qf.oa.service.impl;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysOrg;
import com.qf.oa.mapper.SysOrgMapper;
import com.qf.oa.service.ISysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/18 16:16
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class SysOrgServiceImpl extends BaseServiceImpl<SysOrg> implements ISysOrgService {

    @Autowired
    private SysOrgMapper sysOrgMapper;

    @Override
    public IBaseDao<SysOrg> getBaseDao() {
        return sysOrgMapper;
    }

}
