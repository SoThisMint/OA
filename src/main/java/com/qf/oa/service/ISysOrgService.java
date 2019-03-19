package com.qf.oa.service;

import com.github.pagehelper.PageInfo;
import com.qf.oa.entity.SysOrg;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/18 16:16
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface ISysOrgService extends IBaseService<SysOrg> {

    PageInfo getPage(int pageNum, int pageSize);

    List<SysOrg> getList();
}
