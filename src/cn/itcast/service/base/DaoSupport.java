package cn.itcast.service.base;

import cn.itcast.bean.QueryResult;
import cn.itcast.utils.GenericsUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * desc:
 * User: weiguili(li5220008@163.com)
 * Date: 13-8-26
 * Time: 上午11:24
 */
@Transactional
public abstract class DaoSupport<T> implements DAO<T> {
    @PersistenceContext
    protected EntityManager em;
    protected Class<T> entityClass = GenericsUtils.getSuperClassGenricType(getClass());

    public QueryResult<T> getScrollData(int firstResult, int maxResult){
        String entityName = getEntityName(this.entityClass);
        Query query = em.createQuery("select o from "+entityName+" o");
        query.setFirstResult(firstResult).setMaxResults(maxResult);
        QueryResult<T> qr = new QueryResult<T>();
        qr.setResultlist(query.getResultList());
        query = em.createQuery("select count(o) from "+entityName+" o");
        qr.setRecordtotal((Long)query.getSingleResult());
        return qr;
    }

    @Override
    public void save(T entity) {
        em.persist(entity);
    }

    @Override
    public void update(T entity) {
        em.merge(entity);
    }

    @Override
    public void delete(Serializable entityid) {
        em.remove(em.getReference(entityClass, entityid));
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public T find(Serializable entityid) {
        return em.find(entityClass, entityid);
    }

    protected static <E> String getEntityName(Class<E> entityClass){
        String entityName = entityClass.getSimpleName();
        Entity entity = entityClass.getAnnotation(Entity.class);
        if(entity.name() != null && !"".equals(entity.name())) entityName = entity.name();
        return entityName;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public long getCount() {
        return (Long)em.createQuery("select count(o) from "+getEntityName(this.entityClass)+" o ").getSingleResult();
    }
}
