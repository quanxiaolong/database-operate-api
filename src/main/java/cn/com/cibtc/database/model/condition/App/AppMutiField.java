/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.model.condition.App;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.cibtc.database.model.condition.BaseCondition;
import cn.com.cibtc.database.model.condition.ConditionBlock;


/**
 * <p>标题：AppMutiField </p>
 * <p>
 *    功能描述：前端多条件查询字段模型 
 *    单字段：{@link AppField}
 * </p>
 * <p>创建日期：2016年1月5日下午2:37:02</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class AppMutiField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AppField> fields;

	/**
	 * @return Returns the fields.
	 */
	public List<AppField> getFields() {
		return fields;
	}

	/**
	 * @param fields The fields to set.
	 */
	public void setFields(List<AppField> fields) {
		this.fields = fields;
	}
	
	/**
	 * 根据前台字段集合，创建后台动态条件
	 * F:表示查询类字段枚举
	 * T:表示动态条件 extends {@link BaseCondition}
	 * @date 2016年1月5日下午2:35:16
	 * @param enumField
	 * @param appFields
	 * @return 
	 * @return
	 */
	public <F extends Enum<F>,T extends BaseCondition<F>> T createCondition(Class<T> conditionClz,Class<F> fieldClz){
		
		T condition=null;
		
		List<AppField> listFields=this.getFields();
		if(CollectionUtils.isEmpty(listFields))
			return condition;
		
		ConditionBlock<F> block=null;

		//key:枚举的Name，alue：枚举
		Map<String,F> mapEnumField=new HashMap<String,F>();
		
		//字段枚举元素集合
		EnumSet<F> enumSet=EnumSet.allOf(fieldClz);
		for(F enumItem:enumSet){
			mapEnumField.put(enumItem.name(),enumItem);
		}
//		
		for(int index=0,len=listFields.size();index<len;index++){
			AppField appField=listFields.get(index);
			if(appField!=null&&appField.isAvailable()){
				String fieldName=appField.getName();
				String fieldValue=appField.getValue();
				if(mapEnumField.containsKey(fieldName)){
					if(block==null){
						block=new ConditionBlock<F>();
					}
					block.addCompareCondition(mapEnumField.get(fieldName), appField.getEnumLogic(), appField.getEnumCompare(), fieldValue);
				}
			}
		}
		
		if(block!=null){
			try {
				condition=conditionClz.newInstance();
				condition.addCondationBlock(block);
			} catch (Exception e) {
				condition=null;
			}
			
		}
		
		return condition;
	}
}
