package com.base.model;

public class BaseModel {
	/**
	 * 取数据起始行，用于分页
	 */
	private int rowStart;
	/**
	 * 取数据行数，用于分页
	 */
	private int rowCount;
	
	private String sortColumn;//排序字段
	
	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public int getRowStart() {
		return rowStart;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

}
