package com.qf.oa.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysRole;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/20 21:31
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface ISysRoleService extends IBaseService<SysRole> {
    SysResult batchAdd(List<Long> ids, Long roleId);

    SysResult batchAddUserToRole(List<Long> idList, Long roleId);

    SysResult delUserFormRole(Long roleId, Long userId);

    SysResult batchAddMenuToRole(List<Long> idList, Long roleId);
}
