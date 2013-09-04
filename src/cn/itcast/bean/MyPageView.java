package cn.itcast.bean;

import java.util.List;

/**
 * desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-9-4
 * Time: 下午3:29
 */
public class MyPageView<T> {
    private List<T> records;
    private PageIndex pageIndex;
    private int currentpage =1;
    private long totalpage =1;
    private long totalrecord;
    private int maxresult = 10;
    private int pageindexsize = 10;

    public int getPageindexsize() {
        return pageindexsize;
    }

    public void setPageindexsize(int pageindexsize) {
        this.pageindexsize = pageindexsize;
    }

    public int getFirstResult(){
        return (currentpage -1)* maxresult;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;

    }

    public void setQueryResult(QueryResult<T> qr){
        setRecords(qr.getResultlist());
        setTotalpage(qr.getRecordtotal());
    }

    public PageIndex getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(PageIndex pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

    public long getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(long totalpage) {
        this.totalpage = totalpage;
        this.pageIndex = PageIndex.getPageIndex(this.pageindexsize,this.currentpage,totalpage);
    }

    public long getTotalrecord() {
        return totalrecord;
    }

    public void setTotalrecord(long totalrecord) {
        this.totalrecord = totalrecord;
        setTotalpage(totalrecord%2 == 0? totalrecord/maxresult : totalrecord/maxresult+1);
    }

    public long getMaxresult() {
        return maxresult;
    }

    public void setMaxresult(int maxresult) {
        this.maxresult = maxresult;
    }

    public MyPageView(int pagesize, int currentpage) {
        this.maxresult = pagesize;
        this.currentpage = currentpage;
    }


}
