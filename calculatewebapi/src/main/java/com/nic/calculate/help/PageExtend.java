package com.nic.calculate.help;

/*
 * 分页模型信息
 * */

public class PageExtend extends Page {
    public PageExtend( ) {

    }

    public PageExtend(Page page, int totalCount) {
        this.setPageSize(page.getPageSize());
        this.setPageIndex(page.getPageIndex());
        this.totalCount = totalCount;
    }

    /// <summary>
    /// 返回总条数
    /// </summary>
    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
