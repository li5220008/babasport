package cn.itcast.service.base;

import cn.itcast.bean.QueryResult;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * desc: 实体通用接口
 * User: weiguili(li5220008@163.com)
 * Date: 13-8-26
 * Time: 上午11:03  、
 * @param <T> 实体类型
 */
public interface DAO<T> {
    public QueryResult<T> getScrollData(int firstResult, int maxResult);

    /**
     * 保存实体
     * @param entity
     */
    public void save(T entity);

    /**
     * 更新实体
     * @param entity
     */
    public void update(T entity);

    /**
     * 删除实体
     * @param entityid
     */
    public void delete(Serializable entityid);//JPA 规范实体的id属性必须实现序列化接口

    /**
     * 查找所有实体
     * @param entityid
     * @return
     */
    public T find(Serializable entityid);

    /**
     * 获取总数
     * @return
     */
    public long getCount();


}
