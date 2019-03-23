package com.qf.oa.mapper;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuMapper extends IBaseDao<SysMenu> {

    List<SysMenu> queryAuthMenuByRoleId(Long roleId);

    int delUserFormMenu(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    List<SysMenu> queryNoAuthMenuByRoleId(@Param("roleId") Long roleId, @Param("menuName") String menuName);
}