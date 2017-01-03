/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition.field;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>标题： InField</p>
 * <p>
 *    功能描述：包含类型列-处理类
 * </p>
 * <p>创建日期：2016年1月1日 下午3:49:47</p>
 * <p>版本：1.0</p>
 */
public class InField extends Field {
	
	private static final long serialVersionUID = 1L;

	/**
	 * 字段包含的值
	 */
	private List<Object> values;
	
	/**
	 * 多值条件类型 (in、not in)
	 */
	private String conditionType="in";
	/**
	 * 与下一个字段的关系 and/or
	 */
	private String logic="and";
	
	public InField(String fieldName,String conditionType){
		super(fieldName);
		this.conditionType=conditionType;
	}
	
	public InField(String fieldName,String logic,String conditionType){
		super(fieldName);
		this.logic=logic;
		this.conditionType=conditionType;
	}

	/**
	 * @return the inValues
	 */
	public List<Object> getValues() {
		if(values==null)
			values=new ArrayList<Object>();
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(List<Object> values) {
		this.values = values;
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
