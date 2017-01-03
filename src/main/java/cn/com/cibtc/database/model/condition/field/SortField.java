/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition.field;

/**
 * <p>标题： SortField</p>
 * <p>
 *    功能描述：排序列--处理类 asc  desc
 * </p>
 * <p>创建日期：2016年1月1日 下午3:46:32</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class SortField extends Field{
	private static final long serialVersionUID = 1L;
	/**
	 * 排序方式 asc/desc
	 */
	private String orderBy="asc";
	
	/**
	 * @param fieldName (排序列名称)
	 * @param orderBy (排序方式)
	 */
	public SortField(String fieldName,String orderBy) {
		super(fieldName);
		this.orderBy=orderBy;
	}
	/**
	 * @return the orderBy
	 */
	public String getOrderBy() {
		if(orderBy==null||"".equals(orderBy))
			orderBy="asc";
		return orderBy;
	}
}