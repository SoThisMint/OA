package com.qf.oa.service;

import com.qf.oa.entity.SysPurchase;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/28 18:44
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface ISysPurchaseService extends IBaseService<SysPurchase> {
    void addPurchaseAndStartProcess(SysPurchase sysPurchase);
}
