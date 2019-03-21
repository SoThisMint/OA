package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qf.oa.common.Page;
import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysOrg;
import com.qf.oa.service.ISysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/18 17:26
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/sysOrg")
public class SysOrgController {

    @Autowired
    private ISysOrgService sysOrgService;

    @RequestMapping("/page/{currentPage}")
    public String page(@PathVariable Integer currentPage, Integer pageSize, Model model) {
        if (pageSize == null) {
            pageSize = 4;
        }
        if (currentPage == null) {
            currentPage = 1;
        }
        PageInfo pageInfo = sysOrgService.getPage(currentPage, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        return "org/org_list";
    }

    @RequestMapping("/add")
    @ResponseBody
    public SysResult add(SysOrg sysOrg) {
        return sysOrgService.add(sysOrg);
    }


    @RequestMapping("/list")
    @ResponseBody
    public List<SysOrg> list() {
        List<SysOrg> list = sysOrgService.getList();
        return list;
    }

    @RequestMapping("/toUpdate/{orgId}")
    public String uoUpdate(@PathVariable Integer orgId, ModelMap map) {
        SysOrg sysOrg = sysOrgService.getById(orgId);
        map.put("sysOrg", sysOrg);
        return "org/admin-org-edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    public SysResult update(SysOrg sysOrg) {
        return sysOrgService.update(sysOrg);
    }

    @RequestMapping("/searchWithConditions")
    public String searchWithConditions(SysOrg sysOrg, Model model, Page page) {
        PageInfo pageInfo = sysOrgService.searchWithConditions(sysOrg, page);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("sysOrg", sysOrg);
        model.addAttribute("url","sysOrg/searchWithConditions");

        //传递json格式的条件给page使用
        Gson gson = new Gson();
        Map<String,Object> map = new HashMap<>();
        map.put("orgName",sysOrg.getOrgName());
        map.put("orgParentName",sysOrg.getOrgParentName());
        map.put("flag",sysOrg.getFlag());
        String json = gson.toJson(map);
        model.addAttribute("params",json);
        return "org/org_list";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public SysResult delete(Long orgId) {
        return sysOrgService.checkAndDelete(orgId);

    }

    @RequestMapping("/batchDelete")
    @ResponseBody
    public SysResult batchDelete(@RequestParam List<Long> ids){
        return sysOrgService.checkAndBatchDelete(ids);
    }

}
