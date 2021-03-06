package com.qf.oa.dao;

import com.github.pagehelper.PageInfo;
import com.qf.oa.entity.SysOrg;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/18 16:18
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface IBaseDao<T> {
    int deleteByPrimaryKey(Long orgId);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Long orgId);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

    List<T> getList();

    List<T> searchWithConditions(T t);

    int checkByParentId(Long id);

    int updateFlagById(Long id);

    int checkByParentIds(List<Long> ids);

    int updateFlagByIds(List<Long> ids);

    PageInfo getPage(int pageNum, int pageSize);

    T getById(Integer id);


}
