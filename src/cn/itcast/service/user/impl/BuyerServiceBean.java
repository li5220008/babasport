package cn.itcast.service.user.impl;

import cn.itcast.bean.user.Buyer;
import cn.itcast.service.base.DaoSupport;
import cn.itcast.service.user.BuyerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Desc:
 *
 * @author : weiguili(li5220008@gmail.com)
 *         Date: 13-8-25
 *         Time: 下午3:33
 */
@Service//spring 只会对定义在本类中的方法应用事务通知（advice）
public class BuyerServiceBean extends DaoSupport<Buyer> implements BuyerService {
}
