package com.qf.oa.service;

import com.github.pagehelper.PageInfo;
import com.qf.oa.common.Page;
import com.qf.oa.entity.SysUser;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/21 10:44
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface ISysUserService extends IBaseService<SysUser> {
    PageInfo<SysUser> queryAuthUserByRoleId(Long roleId, Page page);

    PageInfo<SysUser> queryNoAuthUserByRoleId(Long roleId, String userName, Page page);
}
