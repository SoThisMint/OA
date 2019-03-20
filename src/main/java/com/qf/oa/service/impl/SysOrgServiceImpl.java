package com.qf.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
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

    @Override
    public PageInfo searchWithConditions(SysOrg sysOrg, Page page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        List<SysOrg> list = sysOrgMapper.searchWithConditions(sysOrg);
        PageInfo<SysOrg> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public SysResult checkAndDelete(Long orgId) {
        SysResult sysResult = new SysResult();
        int count = sysOrgMapper.checkByParentId(orgId);

        if (count == 0) {
            sysResult.setResult(true);
            sysResult.setData("删除成功！");
            SysOrg sysOrg = new SysOrg();
            sysOrg.setOrgId(orgId);
            sysOrg.setFlag(false);
            sysOrgMapper.updateByPrimaryKeySelective(sysOrg);
        } else {
            sysResult.setResult(false);
            sysResult.setData("删除失败，目标含有子节点");
        }
        return sysResult;
    }

    @Override
    public SysResult checkAndBatchDelete(List<Long> ids) {
        SysResult sysResult = new SysResult();
        int count = sysOrgMapper.checkByParentIds(ids);
        if (count == 0) {
            sysResult.setResult(true);
            sysResult.setData("删除成功！");
            sysOrgMapper.updateFlagByIds(ids);
        } else {
            sysResult.setResult(false);
            sysResult.setData("删除失败，目标含有子节点");
        }
        return sysResult;
    }
}
