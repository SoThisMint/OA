package com.qf.oa.service.impl;

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
    public SysResult checkAndDelete(Long id) {
        SysResult sysResult = new SysResult();
        int res = sysUserMapper.updateFlagById(id);
        sysResult.setResult(true);
        sysResult.setData("删除成功！");
        return sysResult;
    }

    @Override
    public SysResult checkAndBatchDelete(List<Long> ids) {
        SysResult sysResult = new SysResult();
        sysUserMapper.updateFlagByIds(ids);
        sysResult.setResult(true);
        sysResult.setData("删除成功！");
        return sysResult;
    }
}
