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

import cn.com.cibtc.database.enums.RelationEnums.Compare;
import cn.com.cibtc.database.enums.RelationEnums.EmptyType;
import cn.com.cibtc.database.enums.RelationEnums.InType;
import cn.com.cibtc.database.enums.RelationEnums.Logic;



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
	 * in类型条件字段集合
	 */
	private List<InField> inFields=new ArrayList<InField>();
	/**
	 * 范围类型条件字段集合
	 */
	private List<CompareField> compareFields=new ArrayList<CompareField>();
	/**
	 * 空字段类型条件集合
	 */
	private List<EmptyField> emptyFields=new ArrayList<EmptyField>();
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
	/**
	 * @return the inFields
	 */
	public List<InField> getInFields() {
		return inFields;
	}

	/**
	 * @return the rangeFields
	 */
	public List<CompareField> getCompareFields() {
		return compareFields;
	}

	/**
	 * @return the emptyFields
	 */
	public List<EmptyField> getEmptyFields() {
		return emptyFields;
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
		boolean result=(inFields==null||inFields.size()<1);
		result=result&&(compareFields==null||compareFields.size()<1);
		result=result&&(emptyFields==null||emptyFields.size()<1);
		return result;
	}
	
	/**
	 * 添加包含/非包含条件
	 * @date 2015年12月29日上午9:31:19
	 * @param field
	 * @param inType  not in  / in
	 * @param values
	 */
	public void addInCondition(FieldEnum field,InType inType,List<String> values){
		if(field==null||inType==null)
			return;
		if(values==null||values.size()==0)
			return;
		if(StringUtils.isEmpty(field.toString()))
			return;
		InField inField=new InField(field.toString(), inType.toString());
		inField.setValues(values);
		this.getInFields().add(inField);
	}
	
	/**
	 * 添加包含/非包含条件
	 * @date 2015年12月29日上午9:31:55
	 * @param field
	 * @param logicRelation 与下一个字段的关系[and / or] 默认是and 
	 * @param inType not in  / in
	 * @param values
	 */
	public void addInCondition(FieldEnum field,Logic logicRelation,InType inType,List<String> values){
		if(field==null||logicRelation==null||inType==null)
			return;
		if(values==null||values.size()==0)
			return;
		if(StringUtils.isEmpty(field.toString()))
			return;
		InField inField=new InField(field.toString(), logicRelation.toString(), inType.toString());
		inField.setValues(values);
		this.getInFields().add(inField);
	}
	
	/**
	 * 添加比较条件
	 * @date 2015年12月29日上午9:33:00
	 * @param field
	 * @param compare
	 * @param value
	 */
	public void addCompareCondition(FieldEnum field,Compare compare,String value){
		if(field==null||compare==null)
			return;
		if(StringUtils.isEmpty(field.toString()))
			return;
		CompareField compareField=new CompareField(field.toString(),compare.toString());
		compareField.setValue(value);
		this.getCompareFields().add(compareField);
	}
	
	/**
	 * 添加比较条件
	 * @date 2015年12月29日上午9:33:19
	 * @param field
	 * @param logicRelation
	 * @param compare
	 * @param value
	 */
	public void addCompareCondition(FieldEnum field,Logic logicRelation,Compare compare,String value){
		if(field==null||logicRelation==null||compare==null)
			return;
		if(StringUtils.isEmpty(field.toString()))
			return;
		CompareField compareField=new CompareField(field.toString(), logicRelation.toString(),compare.toString());
		compareField.setValue(value);
		this.getCompareFields().add(compareField);
	}
	
	/**
	 * 添加空值查询条件
	 * @date 2016年1月28日上午11:56:03
	 * @param field
	 * @param emptyType
	 */
	public void addEmptyCondition(FieldEnum field,EmptyType emptyType){
		if(field==null||emptyType==null)
			return;
		EmptyField emptyField=new EmptyField(field.toString(), emptyType.toString());
		this.getEmptyFields().add(emptyField);
	}
	/**
	 * 添加空值查询条件
	 * @date 2016年1月28日上午11:56:18
	 * @param field
	 * @param logicRelation
	 * @param emptyType
	 */
	public void addEmptyCondition(FieldEnum field,Logic logicRelation,EmptyType emptyType){
		if(field==null||logicRelation==null||emptyType==null)
			return;
		EmptyField emptyField=new EmptyField(field.toString(), logicRelation.toString(), emptyType.toString());
		this.getEmptyFields().add(emptyField);
	}
	
	
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

		private String value;

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
		public String getValue() {
			return value;
		}

		/**
		 * @param value the value to set
		 */
		public void setValue(String value) {
			if(Compare.LIKE.toString()==this.conditionType&&value!=null){
				value=value.replace("%", "\\%");								//防止value包含“%”特殊字符
				value="%"+value+"%";
			}
			this.value = value;
		}
		/**
		 * @return the conditionType
		 */
		public String getConditionType() {
			return conditionType;
		}
	}

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
		private List<String> values;
		
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
		public List<String> getValues() {
			if(values==null)
				values=new ArrayList<String>();
			return values;
		}

		/**
		 * @param values the values to set
		 */
		public void setValues(List<String> values) {
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
}
