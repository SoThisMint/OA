package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/21 10:47
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping("/searchWithConditions")
    public String searchWithConditions(SysUser sysUser, Model model, Page page) {
        PageInfo pageInfo = sysUserService.searchWithConditions(sysUser, page);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("url", "sysUser/searchWithConditions");

        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("userName", sysUser.getUserName());
        map.put("flag", sysUser.getFlag());
        String params = gson.toJson(map);
        model.addAttribute("params", params);
        return "user/user_list";
    }

    @RequestMapping("/add")
    @ResponseBody
    public SysResult add(SysUser sysUser) {
        return sysUserService.add(sysUser);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Long userId, ModelMap map) {
        SysUser sysUser = sysUserService.selectByPrimaryKey(userId);
        map.put("sysUser", sysUser);
        return "user/admin-user-edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public SysResult update(SysUser sysUser) {
        return sysUserService.update(sysUser);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(Long userId) {
        return sysUserService.checkAndDelete(userId);
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public SysResult batchDelete(@RequestParam List<Long> ids) {
        return sysUserService.checkAndBatchDelete(ids);
    }

    @RequestMapping("/checkLogin")
    public String checkLogin(SysUser sysUser, String online, Model model, HttpSession session) {
        SysUser currentUser = sysUserService.getUserByUserName(sysUser);
        System.out.println(online);
        if (currentUser == null) {
            return "login";
        }
        //登录成功
        List<SysMenu> menuList = sysUserService.getMenuListByUserId(currentUser.getUserId());
        if (true) {
            session.setAttribute("currentUser", currentUser);
        }
        model.addAttribute("menuList", menuList);
        return "index";
    }
}
