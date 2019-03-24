package com.qf.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.dao.IBaseDao;
import com.qf.oa.service.IBaseService;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/18 16:15
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

    public abstract IBaseDao<T> getBaseDao();

    @Override
    public PageInfo getPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = getBaseDao().getList();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<T> getList() {
        return getBaseDao().getList();
    }

    @Override
    public T getById(Integer id) {
        return getBaseDao().selectByPrimaryKey(id.longValue());
    }

    @Override
    public int deleteByPrimaryKey(Long orgId) {
        return getBaseDao().deleteByPrimaryKey(orgId);
    }

    @Override
    public int insert(T t) {
        return getBaseDao().insert(t);
    }

    @Override
    public int insertSelective(T t) {
        return getBaseDao().insertSelective(t);
    }

    @Override
    public T selectByPrimaryKey(Long orgId) {
        return getBaseDao().selectByPrimaryKey(orgId);
    }

    @Override
    public int updateByPrimaryKeySelective(T t) {
        return getBaseDao().updateByPrimaryKeySelective(t);
    }

    @Override
    public int updateByPrimaryKey(T t) {
        return getBaseDao().updateByPrimaryKey(t);
    }

    @Override
    public PageInfo searchWithConditions(T t, Page page) {
        PageHelper.startPage(page.getCurrentPage(), page.getPageSize());
        List<T> list = getBaseDao().searchWithConditions(t);
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public SysResult update(T t) {
        SysResult sysResult = new SysResult();
        int res = getBaseDao().updateByPrimaryKeySelective(t);
        if (res > 0) {
            sysResult.setResult(true);
            sysResult.setData("修改成功！");
        } else {
            sysResult.setResult(false);
            sysResult.setData("修改失败！");
        }
        return sysResult;
    }

    @Override
    public SysResult checkAndDelete(Long id) {
        SysResult sysResult = new SysResult();
        int res = 0;
        try {
            res = getBaseDao().checkByParentId(id);
        } catch (Exception e) {
            sysResult.setResult(true);
            sysResult.setData("删除成功！");
            getBaseDao().updateFlagById(id);
        }
        if (res == 0) {
            sysResult.setResult(true);
            sysResult.setData("删除成功！");
            getBaseDao().updateFlagById(id);
        } else {
            sysResult.setResult(false);
            sysResult.setData("删除失败，该目标含有子节点！");
        }
        return sysResult;
    }

    @Override
    public SysResult checkAndBatchDelete(List<Long> ids) {
        SysResult sysResult = new SysResult();
        int count = 0;
        try {
            count = getBaseDao().checkByParentIds(ids);
        } catch (Exception e) {
            sysResult.setResult(true);
            sysResult.setData("删除成功！");
            getBaseDao().updateFlagByIds(ids);
        }
        if (count == 0) {
            sysResult.setResult(true);
            sysResult.setData("删除成功！");
            getBaseDao().updateFlagByIds(ids);
        } else {
            sysResult.setResult(false);
            sysResult.setData("删除失败，目标含有子节点");
        }
        return sysResult;
    }

    @Override
    public SysResult add(T t) {
        SysResult sysResult = new SysResult();
        int res = getBaseDao().insertSelective(t);
        if (res > 0) {
            sysResult.setResult(true);
            sysResult.setData("添加成功！");
        } else {
            sysResult.setResult(false);
            sysResult.setData("添加失败");
        }
        return sysResult;
    }
}
