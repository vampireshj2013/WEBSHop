package com.shop.Utils;

public class PageUtil {
	private Integer pageSize = 10;// �?��显示条数
	private Integer recordCount = 0;// 总条�?
	private Integer index = 0;// 每页数据的第�?��的索�?
	private Integer currPage = 1;// 当前页数

	/**
	 * 获取总的页数
	 * 
	 * @return
	 */
	public Integer getPageCount() {
		Integer size = this.recordCount / this.pageSize;
		Integer mod = recordCount % pageSize;
		if (mod != 0) {
			size++;
		}
		return this.recordCount == 0 ? 1 : size;
	}

	/**
	 * 是否有上�?��
	 * 
	 * @return
	 */
	public boolean isHasPrevious() {
		//不是第一�?
		if (this.currPage != 1) {
			return true;
		}
		return false;
	}

	/**
	 * 是否有下�?��
	 * 
	 * @return
	 */
	public boolean isHasNext() {
		//不是�?���?��
		if (this.currPage != this.getPageCount()) {
			return true;
		}
		return false;
	}

	public Integer getNextIndex() {
		return this.currPage * this.pageSize;
	}

	public Integer getPreviousIndex() {
		return (this.currPage - 2) * this.pageSize;
	}

	/**
	 * 得到索引
	 * 
	 * @return
	 */
	public Integer getIndex() {
		return this.index;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	/**
	 * 得到当前�?
	 * 
	 * @return
	 */
	public Integer getCurrPage() {
		Integer c = index / pageSize;
		if ((index + 1) % pageSize != 0) {
			c++;
		}
		return c == 0 ? 1 : c;
	}

	/**
	 * 得到末页的索�?
	 * 
	 * @return
	 */
	public Integer getLastIndex() {
		return this.getPageCount()*this.pageSize;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setCurrPage(Integer currPage) {
		this.currPage = currPage;
	}

}
