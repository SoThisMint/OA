package com.qf.service;

import com.github.pagehelper.PageInfo;
import com.qf.oa.entity.SysMenu;
import com.qf.oa.entity.SysOrg;
import com.qf.oa.service.ISysOrgService;
import com.qf.oa.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/18 16:22
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class ServiceTest {

    @Autowired
    private ISysOrgService sysOrgService;

    @Autowired
    private ISysUserService sysUserService;

    @Test
    public void selectByPrimaryKeyTest() {
        SysOrg sysOrg = sysOrgService.selectByPrimaryKey(1L);
        System.out.println(sysOrg);
    }

    @Test
    public void deleteByPrimaryKeyTest() {
        sysOrgService.deleteByPrimaryKey(4L);
    }

    @Test
    public void insert() {
        sysOrgService.insert(new SysOrg(null, 1L, "d", "d", "d", true, new Date(), new Date()));
    }

    @Test
    public void insertSelectiveTest() {
        for (int i = 0; i < 100; i++) {
            SysOrg sysOrg = new SysOrg();
            sysOrg.setOrgId((long) i + 100);
            sysOrg.setOrgName(UUID.randomUUID().toString().substring(0, 5));
            sysOrg.setFlag((int) (Math.random() * 2) == 0);
            sysOrgService.insert(sysOrg);
        }
    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println(sysOrgService.selectByPrimaryKey(1L));
    }

    @Test
    public void updateByPrimaryKeySelective() {
        int res = sysOrgService.updateByPrimaryKeySelective(new SysOrg(100L, 1L, "深圳千锋", "亲的风格", "测试", false, null, null));
        System.out.println(res);
    }

    @Test
    public void updateByPrimaryKey() {

    }

    @Test
    public void getPage() {
        PageInfo page = sysOrgService.getPage(1, 5);
        System.out.println(page);
    }

    @Test
    public void testGetMenuList(){
        List<SysMenu> menuList = sysUserService.getMenuListByUserId(3L);
        for(SysMenu sysMenu:menuList){
            System.out.println(sysMenu);
        }
    }

}
