package com.qf.oa.controller;

import com.qf.oa.entity.SysPurchase;
import com.qf.oa.entity.SysUser;
import com.qf.oa.service.ISysPurchaseService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/28 18:26
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private ISysPurchaseService sysPurchaseService;

    /**
     * 跳转到采购申请的页面
     */
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "purchase/purchase_add";
    }

    /**
     * 添加采购申请
     */
    @RequestMapping("/add")
    public String add(SysPurchase sysPurchase){
        //标题，内容，金额，（申请人的id，名称）
        //得到当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        SysUser sysUser = (SysUser) subject.getPrincipal();
        sysPurchase.setUserId(sysUser.getUserId());
        sysPurchase.setUsername(sysUser.getUserName());
        //调用方法，做添加采购单的操作
        sysPurchaseService.addPurchaseAndStartProcess(sysPurchase);
        return "purchase/purchase_add";
    }
}
