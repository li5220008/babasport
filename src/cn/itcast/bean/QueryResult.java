package cn.itcast.bean;

import java.util.List;

/**
 * desc: 查询结果
 * User: weiguili(li5220008@163.com)
 * Date: 13-8-26
 * Time: 下午3:55
 */
public class QueryResult<T> {
    //结果集
    private List<T> resultlist;
    //总记录数
    private long recordtotal;

    public List<T> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<T> resultlist) {
        this.resultlist = resultlist;
    }

    public long getRecordtotal() {
        return recordtotal;
    }

    public void setRecordtotal(long recordtotal) {
        this.recordtotal = recordtotal;
    }
}
