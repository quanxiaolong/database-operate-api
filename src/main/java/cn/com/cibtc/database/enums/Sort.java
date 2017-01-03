/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.enums;
/**
 * <p>标题：SortRelation </p>
 * <p>
 *    功能描述：排序关系枚举
 * </p>
 * <p>创建日期：2015年12月17日下午3:11:42</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public  enum Sort{
	ASC("asc"),
	DESC("desc");
	
	private String code;
	private Sort(String code){
		this.code=code;
	}
	
	@Override
	public String toString(){
		return this.code;
	}
	
	/**
	 * 判断是否包含
	 * Note:根据枚举Name比较 非Code
	 * @date 2016年1月5日上午10:47:27
	 * @param value
	 * @return
	 */
	public static boolean contains(String value){
		if(value==null)
			return false;
		boolean contain=false;
		Sort[] emumArray=Sort.values();
		for(int index=0,len=emumArray.length;index<len;index++){
			Sort enumItem=emumArray[index];
			if(value.equals(enumItem.name()))
			{
				contain=true;
				break;
			}
		}
		return contain;
	}
	
}
