package com.qf.oa.mapper;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper extends IBaseDao<SysUser> {

    List<SysUser> queryAuthUserByRoleId(Long roleId);

    List<SysUser> queryNoAuthUserByRoleId(@Param("roleId") Long roleId, @Param("userName") String userName);

    SysUser getUserByUserName(SysUser sysUser);

    //通过用户id查询菜单列表
    List<SysMenu> getMenuListByUserId(Long userId);
}