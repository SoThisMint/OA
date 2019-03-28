package com.qf.oa.service.impl;

import com.qf.oa.dao.IBaseDao;
import com.qf.oa.entity.SysPurchase;
import com.qf.oa.mapper.SysPurchaseMapper;
import com.qf.oa.service.ISysPurchaseService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/28 18:45
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Service
public class SysPurchaseServiceImpl extends BaseServiceImpl<SysPurchase> implements ISysPurchaseService {

    @Autowired
    private SysPurchaseMapper sysPurchaseMapper;

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public IBaseDao<SysPurchase> getBaseDao() {
        return sysPurchaseMapper;
    }

    @Override
    public void addPurchaseAndStartProcess(SysPurchase sysPurchase) {
        //添加采购信息，需要主键回填
        sysPurchaseMapper.insertSelective(sysPurchase);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("money",sysPurchase.getMoney());
        map.put("currentId",sysPurchase.getUserId());
        map.put("administrationId",2);
        map.put("managerId",3);
        map.put("financialId",4);
        //bussinessKey 让流程与业务进行关联
        String businessKey = sysPurchase.getId().toString();
        runtimeService.startProcessInstanceByKey("purchase",businessKey,map);
    }
}
