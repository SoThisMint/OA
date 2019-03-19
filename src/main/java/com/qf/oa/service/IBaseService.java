package com.qf.oa.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

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

}
