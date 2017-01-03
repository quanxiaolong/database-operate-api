/*
 * Copyright (c) 2014 中国国际图书贸易集团公司 
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.com.cibtc.database.enums.RelationEnums.Sort;
import cn.com.cibtc.database.util.ConditionUtil;


/**
 * <p>标题：AbstractCondition </p>
 * <p>
 *    功能描述：抽象查询条件类<br/>
 *    所有属于动态条件必须 extends 该抽象类<br/>
 *    泛型说明 第一个参数表示 数据库 查询条件【对应字段{@link IField}】 第二个参数为 数据库排序字段 【对应字段{@link IField}】<br/>
 *    在使用该对象是最好先判断对象是否可用 调用方法{@link ConditionUtil #isConditionAvailable}如果返回值为false 则进行默认处理
 * </p>
 * <p>创建日期：2015年12月17日下午2:29:04</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class DynamicCondition<FieldEnum extends Enum<FieldEnum>> implements Serializable {

	private static final long serialVersionUID = -4037038349252153130L;

	/** 查询集合：多条件块组合 如  a AND b / (a=1 AND b=1) AND ... {@link ConditionBlock}*/
	private List<ConditionBlock<FieldEnum>> conditionBlocks=new ArrayList<ConditionBlock<FieldEnum>>();
	
	/** 查询排序集合：多字段排序 如 A DESC,B ASC .... {@link SortField}*/
	private List<SortField> sortFields=new ArrayList<SortField>();
	
	/** 查询每页条数 */
	private Integer pageSize = 10;
	
	/*** 查询当前页*/
	private Integer currentPage = 1;
	
	/**
	 * 是否匹配所有记录
	 * Note:是否匹配数据库所有记录，当没有查询条件时起作用。
	 * 当为 true时，而且所有查询条件为空({@link #isConditionNull()})时，起作用，会匹配所有的数据库记录
	 */
	private boolean matchAll=false;

	/**
	 * 查询条件是否为空
	 * logic:
	 * 		1、不包含查询语句快conditionBlocks
	 * 		2、所有的查询语句快， 即不包含in条件（InField），也不包含compare条件（CompareField）
	 * @date 2015年12月24日上午11:41:50
	 * @return
	 */
	public boolean isConditionNull(){
		boolean result=(conditionBlocks==null||conditionBlocks.size()<1);
		if(!result){
			for(int index=0,len=conditionBlocks.size();index<len;index++){
				ConditionBlock<FieldEnum> conBlock=conditionBlocks.get(index);
				result=result&&conBlock.isConditionNull();
				if(!result)
					break;
			}
		}
		return result;
	}

	/**
	 * 添加查询条件块
	 * @date 2015年12月29日上午9:55:07
	 * @param block (条件块元素 {@link ConditionBlock}) 
	 */
	public void addCondationBlock(ConditionBlock<FieldEnum> block){
		if(block!=null)
			this.conditionBlocks.add(block);
	}

	/**
	 * 添加排序
	 * @date 2015年12月29日上午9:55:22
	 * @param sortField {@link IField}
	 * @param sortRelation {@link Sort}
	 */
	public void addSort(FieldEnum sortField,Sort sortRelation){
		if(sortField==null||sortRelation==null)
			return;
		if(StringUtils.isEmpty(sortField.toString()))
			return;
		SortField sortFiled=new SortField(sortField.toString(),sortRelation.toString());
		this.sortFields.add(sortFiled);
	}

	/**
	 * 添加排序字段 默认升序 【asc】
	 * @date 2015年12月29日下午4:45:37
	 * @param sortField {@link IField}
	 */
	public void addSort(FieldEnum sortField){
		if(sortField==null)
			return;
		if(StringUtils.isEmpty(sortField.toString()))
			return;
		SortField sortFiled=new SortField(sortField.toString(),Sort.ASC.toString());
		this.sortFields.add(sortFiled);
	}
	
	/*===============================================Getter AND Setter============================================================*/
	/**
	 * @return the sortFields
	 */
	public List<SortField> getSortFields() {
		return sortFields;
	}
	/**
	 * @return the conditionBlocks
	 */
	public List<ConditionBlock<FieldEnum>> getConditionBlocks() {
		return conditionBlocks;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage the currentPage to set
	 */
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	/**
	 * @return the matchAll
	 */
	public boolean isMatchAll() {
		return matchAll;
	}
	/**
	 * @param matchAll the matchAll to set
	 */
	public void setMatchAll(boolean matchAll) {
		this.matchAll = matchAll;
	}

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
}
