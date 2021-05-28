package cn.dlut.util;

import java.util.List;

public class PageModel<T> {

	private int start;
	private int pageNo = 1;//current page
	private int pageSize = 15;//item num in each page
	private int count;//total item num
	private List<T> data; //item list

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void pageCount() {
		int pagecount = count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
		if (pageNo > pagecount) {
			pageNo = pagecount;
		}
		if (pageNo <= 0) {
			pageNo = 1;
		}
	}
}
