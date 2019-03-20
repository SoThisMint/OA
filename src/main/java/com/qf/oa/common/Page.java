package com.qf.oa.common;

import java.util.List;

/**
 * @author ：Tony
 * @date ：Created in 2019/3/18 17:41
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public class Page {
    private Integer currentPage = 1;

    private Integer pageSize = 4;

    private Integer totalCount;

    private Integer totalPage;

    private List<?> list;

    private String url;
    ;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
