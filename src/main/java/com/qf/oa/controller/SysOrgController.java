package com.qf.oa.controller;

import com.github.pagehelper.PageInfo;
import com.qf.oa.common.SysResult;
import com.qf.oa.entity.SysOrg;
import com.qf.oa.service.ISysOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
        SysResult sysResult = new SysResult();
        int res = sysOrgService.insertSelective(sysOrg);
        if (res > 0) {
            sysResult.setResult(true);
            sysResult.setData("添加成功！");
        } else {
            sysResult.setResult(false);
            sysResult.setData("添加失败！");
        }
        return sysResult;
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
        SysResult sysResult = new SysResult();
        int res = sysOrgService.updateByPrimaryKeySelective(sysOrg);
        if (res > 0) {
            sysResult.setResult(true);
            sysResult.setData("修改成功！");
        } else {
            sysResult.setResult(false);
            sysResult.setData("修改失败！");
        }
        return sysResult;
    }


}
