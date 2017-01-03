/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.enums;

/**
 * <p>标题：LogicRelation </p>
 * <p>
 *    功能描述：逻辑关系枚举
 * </p>
 * <p>创建日期：2015年12月17日下午3:12:50</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public enum Logic{
	AND("and"),
	OR("or");
	
	private String code;
	private Logic(String code){
		this.code=code;
	}
	@Override
	public String toString(){
		return this.code;
	}		
	/**
	 * @date 2016年1月5日上午11:09:20
	 * 判断是否包含
	 * Note:根据枚举Name比较 非Code
	 * @param value
	 * @return
	 */
	public static boolean contains(String value){
		if(value==null)
			return false;
		boolean contain=false;
		Logic[] emumArray=Logic.values();
		for(int index=0,len=emumArray.length;index<len;index++){
			Logic enumItem=emumArray[index];
			if(value.equals(enumItem.name()))
			{
				contain=true;
				break;
			}
		}
		return contain;
	}
}
