package com.qf.oa.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo getPage(String currentPage) {
        int current = 1;
        if (currentPage != null) {
            current = Integer.parseInt(currentPage);
        }
        PageHelper.startPage(current, 5);
        List<T> list = getBaseDao().getList();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
