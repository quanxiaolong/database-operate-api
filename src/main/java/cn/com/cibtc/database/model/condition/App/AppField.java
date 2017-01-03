/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition.App;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import cn.com.cibtc.database.enums.Compare;
import cn.com.cibtc.database.enums.Logic;




/**
 * <p>标题：AppField </p>
 * <p>
 *    功能描述：前端单个条件查询字段模型
 * </p>
 * <p>创建日期：2016年1月4日下午4:25:36</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class AppField implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**字段名*/
	private String name;
	
	/** 字段值 */
	private String value;
	
	/**
	 * 比较关系{@link Compare}
	 */
	private String compare;
	
	/**
	 * 逻辑关系{@link Logic}}
	 */
	private String logic;

	/**
	 * @return the fieldName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param fieldName the fieldName to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the fieldValue
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param fieldValue the fieldValue to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取比较关系符号
	 * Note：先判断是否可用 isAvailable()
	 * @date 2016年1月5日上午11:13:57
	 * @return
	 */
	public Compare getEnumCompare(){
		return Compare.valueOf(this.getCompare().toUpperCase());
	}
	/**
	 * @return the compareRelation
	 */
	public String getCompare() {
		if(StringUtils.isEmpty(compare))
			compare=Compare.EQUAL.name();
		return compare;
	}

	/**
	 * @param compareRelation the compareRelation to set
	 */
	public void setCompare(String compare) {
		this.compare = compare;
	}

	/**
	 * 获取逻辑关系枚举
	 * Note：先判断是否可用 isAvailable()
	 * @date 2016年1月5日上午11:12:37
	 * @return
	 */
	public Logic getEnumLogic(){
		return Logic.valueOf(this.getLogic().toUpperCase());
	}
	/**
	 * @return the logicRelation
	 */
	public String getLogic() {
		if(StringUtils.isEmpty(logic))
			logic=Logic.AND.name();
		return logic;
	}
	
	/**
	 * @param logicRelation the logicRelation to set
	 */
	public void setLogic(String logic) {
		this.logic = logic;
	}

	
	/**
	 * 判断是否可用
	 * @date 2016年1月5日上午10:18:02
	 * @return
	 */
	public boolean isAvailable(){
		boolean available=false;
		//字段、字段值不为空
		if(!StringUtils.isEmpty(this.name)&&!StringUtils.isEmpty(this.value)){
			
			String compare=this.getCompare();
			String logic=this.getLogic();
			//逻辑符号、比较符号不为空
			if(!StringUtils.isEmpty(compare)&&!StringUtils.isEmpty(logic)){
				if(Compare.contains(compare.toUpperCase())&&Logic.contains(logic.toUpperCase()))
					available=true;
			}
			
		}
		return available;
	}
	
	@Override
	public String toString() {
		return "AppField [fieldName=" + name + ", fieldValue=" + value + "]";
	}
}
