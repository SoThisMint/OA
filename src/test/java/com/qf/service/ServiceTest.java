package com.qf.service;

import com.qf.oa.entity.SysOrg;
import com.qf.oa.service.ISysOrgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void selectByPrimaryKeyTest() {
        SysOrg sysOrg = sysOrgService.selectByPrimaryKey(1L);
        System.out.println(sysOrg);
    }

}
