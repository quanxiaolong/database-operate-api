/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition.solr;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.cibtc.database.model.condition.ConditionBlock;

/**
 * <p>标题：SolrCondition </p>
 * <p>
 *    功能描述：solr fact 条件对象
 * </p>
 * <p>创建日期：2016年9月14日下午2:58:05</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class SolrFactCondition<FieldEnum extends Enum<FieldEnum>> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**	需要进行fact的字段 */
	private String[] facetFields;
	/**	限制facet返回的数量 */
	private Integer facetLimit = 10;
	/**	是否统计null的值 */
	private Boolean facetMissing = true;
	/** 设置返回的数据中每个分组的数据最小值 */
	private Integer facetMinCount = 0;
	
	/** 查询集合：多条件块组合 如  a AND b / (a=1 AND b=1) AND ... {@link ConditionBlock}*/
	private List<ConditionBlock<FieldEnum>> conditionBlocks=new ArrayList<ConditionBlock<FieldEnum>>();
	
	
	/* ==================== Getter && Setter ======================== */
	public String[] getFacetFields() {
		return facetFields;
	}
	public void setFacetFields(String[] facetFields) {
		this.facetFields = facetFields;
	}
	public Integer getFacetLimit() {
		return facetLimit;
	}
	public void setFacetLimit(Integer facetLimit) {
		this.facetLimit = facetLimit;
	}
	public Boolean getFacetMissing() {
		return facetMissing;
	}
	public void setFacetMissing(Boolean facetMissing) {
		this.facetMissing = facetMissing;
	}
	public Integer getFacetMinCount() {
		return facetMinCount;
	}
	public void setFacetMinCount(Integer facetMinCount) {
		this.facetMinCount = facetMinCount;
	}
	public List<ConditionBlock<FieldEnum>> getConditionBlocks() {
		return conditionBlocks;
	}
	public void setConditionBlocks(List<ConditionBlock<FieldEnum>> conditionBlocks) {
		this.conditionBlocks = conditionBlocks;
	}
	
}
