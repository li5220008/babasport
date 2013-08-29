package cn.itcast.service.user;

import cn.itcast.bean.user.Buyer;
import cn.itcast.service.base.DAO;

/**
 * Desc:  用户业务处理类
 * @author : weiguili(li5220008@gmail.com)
 * Date: 13-8-25
 * Time: 下午3:21
 */
public interface BuyerService extends DAO<Buyer> {
    /**
     * 判断用户是否存在
     * @param username 用户名
     * @return
     */
    public boolean exit(String username);
}
