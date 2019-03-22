package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.service.ISysMenuService;
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
 * @date ：Created in 2019/3/21 18:45
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    @RequestMapping("/searchWithConditions")
    public String searchWithConditions(SysMenu sysMenu, Model model, Page page) {
        PageInfo<SysMenu> pageInfo = sysMenuService.searchWithConditions(sysMenu, page);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("sysMenu", sysMenu);
        model.addAttribute("url", "sysMenu/searchWithConditions");

        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("menuName", sysMenu.getMenuName());
        map.put("menuType", sysMenu.getMenuType());
        map.put("isPublish", sysMenu.getIsPublish());
        String params = gson.toJson(map);
        model.addAttribute("params", params);
        return "menu/menu_list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<SysMenu> list() {
        List<SysMenu> list = sysMenuService.getList();
        return list;
    }

    @RequestMapping("/add")
    @ResponseBody
    public SysResult add(SysMenu sysMenu) {
        return sysMenuService.add(sysMenu);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(Long menuId) {
        return sysMenuService.checkAndDelete(menuId);
    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public SysResult batchDelete(@RequestParam List<Long> ids) {
        return sysMenuService.checkAndBatchDelete(ids);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Long menuId, Model model) {
        SysMenu sysMenu = sysMenuService.selectByPrimaryKey(menuId);
        model.addAttribute("sysMenu", sysMenu);
        return "menu/admin-menu-edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public SysResult update(SysMenu sysMenu) {
        return sysMenuService.update(sysMenu);
    }
}
