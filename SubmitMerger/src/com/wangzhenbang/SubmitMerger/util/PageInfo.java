package com.wangzhenbang.SubmitMerger.util;

import java.util.List;

public class PageInfo {

    /**
     * 记录总数
     */
    private long recordsTotal;
    /**
     * 过滤后的记录数量
     */
    private long recordsFiltered;
    /**
     * 当前页码
     */
    private int currentPage;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 当前页的记录数据
     */
    private List<?> datas;

    /**
     * 得到总页数
     */
    public int getPages() {
        double t = (recordsFiltered / pageSize);
        if (recordsFiltered % pageSize == 0)
            return (int) t;
        else
            return (int) t + 1;
    }
    public long getRecordsTotal() {
        return recordsTotal;
    }
    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
    public long getRecordsFiltered() {
        return recordsFiltered;
    }
    public void setRecordsFiltered(long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public List<?> getDatas() {
        return datas;
    }
    public void setDatas(List<?> datas) {
        this.datas = datas;
    }

}
