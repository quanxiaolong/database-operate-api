/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.com.cibtc.database.enums.Compare;
import cn.com.cibtc.database.enums.Empty;
import cn.com.cibtc.database.enums.In;
import cn.com.cibtc.database.enums.Logic;
import cn.com.cibtc.database.model.condition.field.CompareField;
import cn.com.cibtc.database.model.condition.field.EmptyField;
import cn.com.cibtc.database.model.condition.field.Field;
import cn.com.cibtc.database.model.condition.field.InField;
import cn.com.cibtc.database.model.condition.field.SqlField;



/**
 * <p>标题：ConditionBlock </p>
 * <p>
 *    功能描述：条件块 每个条件块用“()”括号括起来
 * </p>
 * <p>创建日期：2015年12月17日下午2:14:21</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class ConditionBlock<FieldEnum extends Enum<FieldEnum>> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 条件字段集合
	 */
	private List<Field> fields = new ArrayList<Field>();
	
	/**
	 * 直接SQL查询条件
	 */
	private SqlField sqlField;
	/**
	 * 与下一个条件块之间的关系
	 */
	private String logic="and";
	
	public ConditionBlock(){
		
	}
	
	public ConditionBlock(Logic logicRelation){
		if(logicRelation!=null){
			this.logic=logicRelation.toString();

		}
	}
	
	public List<Field> getFields() {
		return fields;
	}
	
	public SqlField getSqlField() {
		return sqlField;
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
	 * 条件是否为空
	 * @date 2015年12月24日上午11:45:36
	 * @return
	 */
	public boolean isConditionNull(){
		boolean result=(fields==null||fields.size()<1);
		return result;
	}
	
	/**
	 * 添加包含/非包含条件
	 * @date 2015年12月29日上午9:31:19
	 * @param field
	 * @param inType  not in  / in
	 * @param values
	 */
	public void addInCondition(FieldEnum field,In inType,List<Object> values){
		if(field==null||inType==null)
			return;
		if(values==null||values.size()==0)
			return;
		if(StringUtils.isEmpty(field.toString()))
			return;
		InField inField=new InField(field.toString(), inType.toString());
		inField.setValues(values);
		this.fields.add(inField);
	}
	
	/**
	 * 添加包含/非包含条件
	 * @date 2015年12月29日上午9:31:55
	 * @param field
	 * @param logicRelation 与下一个字段的关系[and / or] 默认是and 
	 * @param inType not in  / in
	 * @param values
	 */
	public void addInCondition(FieldEnum field,Logic logicRelation,In inType,List<Object> values){
		if(field==null||logicRelation==null||inType==null)
			return;
		if(values==null||values.size()==0)
			return;
		if(StringUtils.isEmpty(field.toString()))
			return;
		InField inField=new InField(field.toString(), logicRelation.toString(), inType.toString());
		inField.setValues(values);
		this.fields.add(inField);
	}
	
	/**
	 * 添加比较条件
	 * @date 2015年12月29日上午9:33:00
	 * @param field
	 * @param compare
	 * @param value
	 */
	public void addCompareCondition(FieldEnum field,Compare compare,Object value){
		if(field==null||compare==null||value==null)
			return;
		CompareField compareField=new CompareField(field.toString(),compare.toString());
		compareField.setValue(value);
		this.fields.add(compareField);
	}
	
	/**
	 * 添加比较条件
	 * @date 2015年12月29日上午9:33:19
	 * @param field
	 * @param logicRelation
	 * @param compare
	 * @param value
	 */
	public void addCompareCondition(FieldEnum field,Logic logicRelation,Compare compare,Object value){
		if(field==null||logicRelation==null||compare==null||value==null)
			return;
		CompareField compareField=new CompareField(field.toString(), logicRelation.toString(),compare.toString());
		compareField.setValue(value);
		this.fields.add(compareField);
	}
	
	/**
	 * 添加空值查询条件
	 * @date 2016年1月28日上午11:56:03
	 * @param field
	 * @param emptyType
	 */
	public void addEmptyCondition(FieldEnum field,Empty emptyType){
		if(field==null||emptyType==null)
			return;
		EmptyField emptyField=new EmptyField(field.toString(), emptyType.toString());
		this.fields.add(emptyField);
	}
	/**
	 * 添加空值查询条件
	 * @date 2016年1月28日上午11:56:18
	 * @param field
	 * @param logicRelation
	 * @param emptyType
	 */
	public void addEmptyCondition(FieldEnum field,Logic logicRelation,Empty emptyType){
		if(field==null||logicRelation==null||emptyType==null)
			return;
		EmptyField emptyField=new EmptyField(field.toString(), logicRelation.toString(), emptyType.toString());
		this.fields.add(emptyField);
	}
	/**
	 * 添加SQL直接查询
	 * @date 2017年6月19日 上午11:24:55
	 * @author 陶聪
	 * @param sqlWhere	字符串格式：colum1 = #{} and colum2 = #{}
	 *                  当参数为null或空时，不进行操作
	 * @param params	实际查询参数集合
	 */
	public void addSqlCondition(String sqlWhere, Object[] params) {
		if (!StringUtils.isEmpty(sqlWhere)) {
			SqlField sqlField = new SqlField(sqlWhere, params);
			this.sqlField = sqlField;
		}
	}
}
