package cn.itcast.service.user.impl;

import cn.itcast.bean.user.Buyer;
import cn.itcast.service.base.DaoSupport;
import cn.itcast.service.user.BuyerService;
import cn.itcast.utils.MD5;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.io.Serializable;


/**
 * Desc:
 *
 * @author : weiguili(li5220008@gmail.com)
 *         Date: 13-8-25
 *         Time: 下午3:33
 */
@Service//spring 只会对定义在本类中的方法应用事务通知（advice）
public class BuyerServiceBean extends DaoSupport<Buyer> implements BuyerService {
    @Override
    public void save(Buyer entity) {
        entity.setPassword(MD5.MD5Encode(entity.getPassword()));
        super.save(entity);
    }

    @Override
    public boolean exit(String username){
        long count = (Long)em.createQuery("select count(o) from Buyer o where o.username=?1").setParameter(1,username).getSingleResult();
        return count>0;
    }
    public boolean validate(String username, String password){
        long count = (Long)em.createQuery("select count(o) from Buyer o where o.username=?1 and o.password=?2").setParameter(1,username).setParameter(2,MD5.MD5Encode(password)).getSingleResult();
        return count>0;
    }

    @Override
    public void delete(Serializable... entityids) {
        visable(false,entityids);
    }

    public void visable(boolean isvisable, Serializable... entityids){
        if(entityids != null && entityids.length>0){
            StringBuilder jpql = new StringBuilder();
            for(int i=0 ; i<entityids.length; i++){
                jpql.append("?").append(entityids.length+2).append(",");
            }
            jpql.deleteCharAt(jpql.length()-1);
            Query query = em.createQuery("update Buyer o set o.visible =?1 where  o.username in(" + jpql.toString() + ")");
            query.setParameter(1,isvisable);
            for(int i=0 ;i<entityids.length; i++){
                query.setParameter(i+2, entityids[i]);
            }
            query.executeUpdate();
        }
    }

}
