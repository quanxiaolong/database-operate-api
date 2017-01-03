/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.enums;
/**
 * <p>标题：InType </p>
 * <p>
 *    功能描述：包含条件枚举
 * </p>
 * <p>创建日期：2015年12月17日下午3:59:52</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public enum In{
	IN("in"),
	NOT_IN("not in");
	private String code;
	private In(String code){
		this.code=code;
	}
	
	@Override
	public String toString(){
		return this.code;
	}
	
	/**
	 * 判断是否包含
	 * Note:根据枚举Name比较 非Code
	 * @date 2016年1月5日上午10:47:34
	 * @param value
	 * @return
	 */
	public static boolean contains(String value){
		if(value==null)
			return false;
		boolean contain=false;
		In[] emumArray=In.values();
		for(int index=0,len=emumArray.length;index<len;index++){
			In enumItem=emumArray[index];
			if(value.equals(enumItem.name()))
			{
				contain=true;
				break;
			}
		}
		return contain;
	}
}
