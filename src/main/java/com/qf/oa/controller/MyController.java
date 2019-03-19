package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.qf.oa.service.ISysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/18 23:57
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/my")
public class MyController {
    @Autowired
    private ISysOrgService sysOrgService;

    @RequestMapping("/page")
    @ResponseBody
    public PageInfo page() {
        return sysOrgService.getPage("1");
    }
}
