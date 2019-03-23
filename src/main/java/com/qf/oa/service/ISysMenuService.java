package com.qf.oa.service;

import com.github.pagehelper.PageInfo;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysMenu;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/21 18:42
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface ISysMenuService extends IBaseService<SysMenu> {
    PageInfo<SysMenu> queryAuthMenuByRoleId(Long roleId, Page page);

    SysResult delUserFormMenu(Long roleId, Long menuId);

    PageInfo<SysMenu> queryNoAuthMenuByRoleId(Long roleId, String menuName, Page page);
}
