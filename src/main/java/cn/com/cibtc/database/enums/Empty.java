/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.enums;
/**
 * <p>标题：EmptyType </p>
 * <p>
 *    功能描述：空字段判断枚举
 * </p>
 * <p>创建日期：2016年1月28日上午11:49:52</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public enum Empty{
	NULL("is"),
	NOT_NULL("is not"),
	;
	private String code;
	private Empty(String code){
		this.code=code;
	}
	
	@Override
	public String toString(){
		return this.code;
	}
	
	/**
	 * 判断是否包含
	 * Note:根据枚举Name比较 非Code
	 * @date 2016年1月5日上午10:47:06
	 * @param value
	 * @return
	 */
	public static boolean contains(String value){
		if(value==null)
			return false;
		boolean contain=false;
		Empty[] emumArray=Empty.values();
		for(int index=0,len=emumArray.length;index<len;index++){
			Empty enumItem=emumArray[index];
			if(value.equals(enumItem.name()))
			{
				contain=true;
				break;
			}
		}
		return contain;
	}
}
