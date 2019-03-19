package com.qf.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysOrg;
import com.qf.oa.mapper.SysOrgMapper;
import com.qf.oa.service.ISysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageInfo getPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysOrg> list = sysOrgMapper.getList();
        PageInfo<SysOrg> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<SysOrg> getList() {
        return sysOrgMapper.getList();
    }

    @Override
    public SysOrg getById(Integer orgId) {
        return sysOrgMapper.selectByPrimaryKey(orgId.longValue());
    }
}
