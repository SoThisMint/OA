package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.qf.oa.service.ISysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String page(@PathVariable String currentPage, Model model){
        PageInfo pageInfo = sysOrgService.getPage(currentPage);
        model.addAttribute("pageInfo",pageInfo);
        return "org/org_list";
    }

}
