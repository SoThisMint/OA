package com.qf.oa.service;

import com.github.pagehelper.PageInfo;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysOrg;
import com.qf.oa.entity.SysUser;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/18 16:12
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface IBaseService<T> {

    int deleteByPrimaryKey(Long orgId);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

    PageInfo<T> searchWithConditions(T t, Page page);

    SysResult checkAndDelete(Long id);

    SysResult checkAndBatchDelete(List<Long> ids);

    PageInfo getPage(int pageNum, int pageSize);

    List<T> getList();

    T getById(Integer id);

    SysResult update(T t);

    SysResult add(T t);
}
