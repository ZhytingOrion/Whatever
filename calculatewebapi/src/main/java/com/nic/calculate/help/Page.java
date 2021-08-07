package com.nic.calculate.help;

public class Page {
    public Page(){
        this.pageSize = 10;
        this.pageIndex = 1;
    }

    public Page(int pageSize){
        if(pageSize<=0){
            pageSize = 10;
        }

        this.pageSize = pageSize;
        this.pageIndex = 1;
    }

    /// <summary>
    /// 页条数大小
    /// </summary>
    private int pageSize;

    /// <summary>
    /// 页码
    /// </summary>
    private int pageIndex;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
