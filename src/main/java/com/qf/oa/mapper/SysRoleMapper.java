package com.qf.oa.mapper;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper extends IBaseDao<SysRole> {

    int batchAdd(@Param("ids") List<Long> ids, @Param("roleId") Long roleId);

    int batchAddUserToRole(@Param("idList") List<Long> idList, @Param("roleId") Long roleId);

    int delUserFormRole(@Param("roleId") Long roleId, @Param("userId") Long userId);

    int batchAddMenuToRole(@Param("idList") List<Long> idList, @Param("roleId") Long roleId);
}