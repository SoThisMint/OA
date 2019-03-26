package com.qf.oa.realm;

import com.qf.oa.entity.SysUser;
import com.qf.oa.service.ISysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/26 17:42
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证处理");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //得到用户名
        String username = token.getUsername();
        //通过用户名查询用户对象
        SysUser sysUser = sysUserService.findUserByUserName(username);
        if (sysUser != null) {
            //加盐值
            ByteSource byteSource = ByteSource.Util.bytes(username);
            return new SimpleAuthenticationInfo(sysUser, sysUser.getUserPassword(), byteSource, this.getName());
        }
        return null;
    }
}
