/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition.field;

/**
 * <p>标题： CompareField</p>
 * <p>
 *    功能描述：比较类型列--处理类
 * </p>
 * <p>创建日期：2016年1月1日 下午3:49:20</p>
 * <p>版本：1.0</p>
 */
public class CompareField extends Field {
	
	private static final long serialVersionUID = 1L;

	private Object value;

	/**
	 * 比较条件:[>,>=,<,<=]
	 */
	private String conditionType="=";
	/**
	 * 与下一个字段的关系 and/or
	 */
	private String logic="and";
	
	public CompareField(String fieldName,String conditionType){
		super(fieldName);
		this.conditionType=conditionType;
	}
	
	public CompareField(String fieldName,String logic,String conditionType){
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
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	/**
	 * @return the conditionType
	 */
	public String getConditionType() {
		return conditionType;
	}
}

