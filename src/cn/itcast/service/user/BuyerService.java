package cn.itcast.service.user;

import cn.itcast.bean.user.Buyer;

/**
 * Desc:  用户业务处理类
 * @author : weiguili(li5220008@gmail.com)
 * Date: 13-8-25
 * Time: 下午3:21
 */
public interface BuyerService {
    public void save(Buyer buyer);
    public void update(Buyer buyer);
    public void delete(String username);
    public Buyer find(Buyer buyer);
}
