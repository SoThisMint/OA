package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.SysRole;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.ISysMenuService;
import com.qf.oa.service.ISysRoleService;
import com.qf.oa.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/23 9:36
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping("/roleList")
    @ResponseBody
    public List<SysRole> roleList() {
        return sysRoleService.getList();
    }

    @RequestMapping("/sysRoleList")
    public String roleList(Model model) {
        List<SysRole> list = sysRoleService.getList();
        model.addAttribute("roleList", list);
        return "authorization/authorization";
    }

    /**
     * 查询该角色下所有授权了的用户
     *
     * @param roleId
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/queryAuthUserByRoleId")
    public String queryAuthUserByRoleId(Long roleId, Page page, Model model) {
        PageInfo<SysUser> pageInfo = sysUserService.queryAuthUserByRoleId(roleId, page);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("url", "authorization/queryAuthUserByRoleId");
        saveRoleId(roleId, model);
        return "authorization/authorization_authUser_list";
    }

    /**
     * 查询该角色下所有没有授权的用户
     *
     * @param roleId
     * @param userName
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/queryNoAuthUserByRoleId")
    public String queryNoAuthUserByRoleId(Long roleId, String userName, Page page, Model model) {
        PageInfo<SysUser> pageInfo = sysUserService.queryNoAuthUserByRoleId(roleId, userName, page);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("userName", userName);
        model.addAttribute("url", "authorization/queryNoAuthUserByRoleId");
        model.addAttribute("roleId", roleId);

        saveRoleId(roleId, model);
        return "authorization/authorization_noauthUser_list";
    }

    /**
     * 批量给某个角色授权用户，实际上要往角色用户关系表中添加用户id和角色id
     *
     * @param idList
     * @param roleId
     * @return
     */
    @RequestMapping("/batchAddUserToRole")
    @ResponseBody
    public SysResult batchAddUserToRole(@RequestParam List<Long> idList, Long roleId) {
        return sysRoleService.batchAddUserToRole(idList, roleId);
    }

    /**
     * 接触用户和角色的关系
     *
     * @param roleId
     * @param userId
     * @return
     */
    @RequestMapping("/delUserFormRole")
    @ResponseBody
    public SysResult delUserFormRole(Long roleId, Long userId) {
        return sysRoleService.delUserFormRole(roleId, userId);
    }

    /**
     * 查询某角色下所有授权了的菜单
     *
     * @param roleId
     * @param page
     * @param model
     * @return
     */
    @RequestMapping("/queryAuthMenuByRoleId")
    public String queryAuthMenuByRoleId(Long roleId, Page page, Model model) {
        PageInfo<SysMenu> pageInfo = sysMenuService.queryAuthMenuByRoleId(roleId, page);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("url", "authorization/queryAuthMenuByRoleId");
        saveRoleId(roleId, model);
        return "authorization/authorization_authMenu_list";
    }

    @RequestMapping("/queryNoAuthMenuByRoleId")
    public String queryNoAuthMenuByRoleId(Long roleId, String menuName, Page page, Model model) {
        PageInfo<SysMenu> pageInfo = sysMenuService.queryNoAuthMenuByRoleId(roleId, menuName, page);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("url", "authorization/queryNoAuthMenuByRoleId");
        model.addAttribute("roleId", roleId);
        //菜单名=>搜索回填用的
        model.addAttribute("menuName", menuName);

        saveRoleId(roleId, model);
        return "authorization/authorization_noauthMenu_list";
    }

    @RequestMapping("/batchAddMenuToRole")
    @ResponseBody
    public SysResult batchAddMenuToRole(@RequestParam List<Long> idList, Long roleId) {
        return sysRoleService.batchAddMenuToRole(idList, roleId);
    }

    @RequestMapping("/delUserFormMenu")
    @ResponseBody
    public SysResult delUserFormMenu(Long roleId, Long menuId) {
        return sysMenuService.delUserFormMenu(roleId, menuId);
    }

    /**
     * 封装roleId以json格式传到前端
     *
     * @param roleId
     * @param model
     */
    private void saveRoleId(Long roleId, Model model) {
        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        model.addAttribute("params", gson.toJson(map));
    }

}
