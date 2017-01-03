/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition.field;

/**
 * <p>标题：EmptyField </p>
 * <p>
 *    功能描述：空字段类型--处理类
 * </p>
 * <p>创建日期：2016年1月28日上午11:04:57</p>
 * <p>版本：1.0</p>
 */
public class EmptyField extends Field{

	private static final long serialVersionUID = 1L;

	/**
	 * 比较条件:[is、is not]
	 */
	private String conditionType="is";
	/**
	 * 与下一个字段的关系 and/or
	 */
	private String logic="and";
	
	public EmptyField(String fieldName,String conditionType){
		super(fieldName);
		this.conditionType=conditionType;
	}
	
	public EmptyField(String fieldName,String logic,String conditionType){
		super(fieldName);
		this.logic=logic;
		this.conditionType=conditionType;
	}
	/**
	 * @return the logic
	 */
	public String getLogic() {
		if(logic==null||"".equals(logic))
			logic="and";
		return logic;
	}

	/**
	 * @return the conditionType
	 */
	public String getConditionType() {
		return conditionType;
	}
}
