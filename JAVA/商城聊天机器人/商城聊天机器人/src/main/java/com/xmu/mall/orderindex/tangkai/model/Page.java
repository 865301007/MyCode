package com.xmu.mall.orderindex.tangkai.model;

/**
 * @author tangkai
 * @data 2017年6月2日--下午1:19:12
 */
public class Page {
	private int everypage;//每页的大小
	private int totalCount;//总的条目数
	private int totalPage;//总的页数
	private int currentPage;//当前页码
	//private int beginIndex;//鏌ヨ璧峰鐐�
	private boolean hasPrePage;//是否有前一页
	private boolean hasNextPage;//是否有下一页
	public Page(){	
	}
	public Page(int everypage, int totalCount, int totalPage, int currentPage, boolean hasPrePage,
			boolean hasNextPage) {
		super();
		this.everypage = everypage;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
	}
	public int getEverypage() {
		return everypage;
	}
	public void setEverypage(int everypage) {
		this.everypage = everypage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public boolean isHasPrePage() {
		return hasPrePage;
	}
	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}
	public boolean isHasNextPage() {
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
}
