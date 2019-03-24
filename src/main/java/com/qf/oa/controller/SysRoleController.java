package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysRole;
import com.qf.oa.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/22 11:38
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/sysRole")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;


    @RequestMapping("/searchWithConditions")
    public String searchWithConditions(SysRole sysRole, Page page, Model model) {
        PageInfo<SysRole> pageInfo = sysRoleService.searchWithConditions(sysRole, page);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("sysRole", sysRole);

        //还要存url和params用于翻页
        model.addAttribute("url", "sysRole/searchWithConditions");

        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("sysRole", sysRole);
        model.addAttribute("params", gson.toJson(map));
        return "role/role_list";
    }

    @RequestMapping("/add")
    @ResponseBody
    public SysResult add(SysRole sysRole) {
        return sysRoleService.add(sysRole);
    }

    @RequestMapping("/toUpdate/{roleId}")
    public String toUpdate(@PathVariable Long roleId, Model model) {
        SysRole sysRole = sysRoleService.getById(roleId.intValue());
        model.addAttribute("sysRole", sysRole);
        return "role/admin-role-edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public SysResult update(SysRole sysRole){
        return sysRoleService.update(sysRole);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(Long roleId){
        return sysRoleService.checkAndDelete(roleId);
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public SysResult batchDelete(@RequestParam List<Long> ids){
        return sysRoleService.checkAndBatchDelete(ids);
    }
}
