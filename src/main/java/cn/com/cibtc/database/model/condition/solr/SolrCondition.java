/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition.solr;

import cn.com.cibtc.database.model.condition.BaseCondition;

/**
 * <p>标题：SolrCondition </p>
 * <p>
 *    功能描述：solr条件对象
 * </p>
 * <p>创建日期：2016年9月14日下午2:58:05</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class SolrCondition<FieldEnum extends Enum<FieldEnum>> extends BaseCondition<FieldEnum> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//TODO 添加FQ
	
	
	// FACT统计属性
	boolean fact = false;
	SolrFactCondition<FieldEnum> FactCondition;

	
	//TODO 添加GROUPBY 属性
	
	/* ===================== Getter && Setter ======================= */
	
	public boolean getFact() {
		return fact;
	}
	public void setFact(boolean fact) {
		this.fact = fact;
	}
	public SolrFactCondition<FieldEnum> getFactCondition() {
		return FactCondition;
	}
	public void setFactCondition(SolrFactCondition<FieldEnum> factCondition) {
		FactCondition = factCondition;
	}
}
