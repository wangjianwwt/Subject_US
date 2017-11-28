package com.born.bc.commons.entity;

/**
 * 分页参数VO
 */
public class PageParamVO {

    /**
     * 当前页码
     */
    private Integer pageIndex;
    /**
     * 每页显示条数
     */
    private Integer pageSize;
    /**
     * 总条数
     */
    private long total;

    public Integer getPageIndex() {
        return (pageIndex == null) ? 1 : pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return (pageSize == null) ? 10 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PageParamVO{" +
                "pageIndex=" + pageIndex +
                ", pageSize=" + pageSize +
                ", total=" + total +
                '}';
    }
}
