package cn.itcast.service.user.impl;

import cn.itcast.bean.user.Buyer;
import cn.itcast.service.user.BuyerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Desc:
 *
 * @author : weiguili(li5220008@gmail.com)
 *         Date: 13-8-25
 *         Time: 下午3:33
 */
@Service
@Transactional
public class BuyerServiceBean implements BuyerService {

    @Override
    public void save(Buyer buyer) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Buyer buyer) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(String username) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Buyer find(Buyer buyer) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
