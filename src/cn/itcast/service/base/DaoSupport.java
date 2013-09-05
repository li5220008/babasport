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
import java.util.LinkedHashMap;
import java.util.Map;

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

    /**
     * 所有记录，不分页
     * @return
     */
    public QueryResult<T> getScrollData(){
        return getScrollData(-1, -1, null, null, null);
    }
    /**
     * 不需要where条件
     * @param firstResult
     * @param maxResult
     * @return
     */
    public QueryResult<T> getScrollData(int firstResult, int maxResult){
        return getScrollData(firstResult, maxResult, null, null, null);
    }


    /**
     * 不需要排序
     * @param firstResult
     * @param maxResult
     * @param where
     * @param params
     * @return
     */
    public QueryResult<T> getScrollData(int firstResult, int maxResult, String where, Object[] params){
        return getScrollData(firstResult, maxResult, where, params, null);
    }



    /**
     * 没有条件，但是有排序
     * @param firstResult
     * @param maxResult
     * @param ordermap
     * @return
     */
    public QueryResult<T> getScrollData(int firstResult, int maxResult, LinkedHashMap<String, String> ordermap){
        return getScrollData(firstResult, maxResult, null, null, ordermap);
    }

    /**
     * 获取分页结果
     * @param firstResult
     * @param maxResult
     * @param where
     * @param params
     * @param ordermap
     * @return
     */
    public QueryResult<T> getScrollData(int firstResult, int maxResult, String where, Object[] params, LinkedHashMap<String, String> ordermap){
        String entityName = getEntityName(this.entityClass);
        String wereql = where !=null && !"".equals(where.trim()) ? " where "+ where : "";
        String sql = "select o from "+entityName+" o "+wereql+buildOrderby(ordermap);
        System.out.println(sql);
        Query query = em.createQuery(sql);
        if(firstResult !=-1 && maxResult!=-1) query.setFirstResult(firstResult).setMaxResults(maxResult); //如果传入-1 则不进行分页
        setQueryParameter(query, params);
        QueryResult<T> qr = new QueryResult<T>();
        qr.setResultlist(query.getResultList());
        query = em.createQuery("select count(o) from "+entityName+" o "+wereql);
        setQueryParameter(query, params);
        qr.setRecordtotal((Long)query.getSingleResult());
        return qr;
    }

    /**
     * 设置查询参数
     * @param query 查询对象
     * @param params 参数值
     */
    private void setQueryParameter(Query query, Object[] params) {
        if(params !=null)
        for(int i=0; i<params.length; i++){
            query.setParameter(i+1, params[i]);
        }
    }

    /**
     * 构建排序语句
     * @param ordermap 排序属性(k) 与 asc/desc(v)
     * @return
     */
    public static String buildOrderby(LinkedHashMap<String, String> ordermap){
        StringBuilder sb = new StringBuilder();
        if(ordermap !=null && !ordermap.isEmpty()){
            sb.append(" order by ");
            for(Map.Entry<String, String> entry : ordermap.entrySet()){
                sb.append("o.").append(entry.getKey()).append(" ").append(entry.getValue()).append(',');
            }
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
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
    public void delete(Serializable... entityids) {
        for(Serializable entityid : entityids){
            em.remove(em.find(entityClass,entityid));
        }
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
