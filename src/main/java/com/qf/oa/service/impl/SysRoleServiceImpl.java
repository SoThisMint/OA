package com.qf.oa.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qf.oa.common.SysResult;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysRole;
import com.qf.oa.mapper.SysRoleMapper;
import com.qf.oa.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public SysResult batchAdd(List<Long> ids, Long roleId) {
        SysResult sysResult = new SysResult();
        int res = sysRoleMapper.batchAdd(ids, roleId);
        if (res > 0) {
            sysResult.setData("授权成功！");
            sysResult.setResult(true);
        } else {
            sysResult.setResult(false);
            sysResult.setData("授权失败！");
        }
        return sysResult;
    }

    @Override
    public SysResult batchAddUserToRole(List<Long> idList, Long roleId) {
        SysResult sysResult = new SysResult();
        int count = sysRoleMapper.batchAddUserToRole(idList, roleId);
        if (count > 0) {
            sysResult.setResult(true);
        } else {
            sysResult.setResult(false);
        }
        return sysResult;
    }

    @Override
    public SysResult delUserFormRole(Long roleId, Long userId) {
        SysResult sysResult = new SysResult();
        int res = sysRoleMapper.delUserFormRole(roleId, userId);
        if (res > 0) {
            sysResult.setResult(true);
        } else {
            sysResult.setResult(false);
        }
        return sysResult;
    }

    @Override
    public SysResult batchAddMenuToRole(List<Long> idList, Long roleId) {
        SysResult sysResult = new SysResult();
        int count = sysRoleMapper.batchAddMenuToRole(idList,roleId);
        if(count>0){
            sysResult.setResult(true);
        }else{
            sysResult.setResult(false);
        }
        return sysResult;
    }

}
