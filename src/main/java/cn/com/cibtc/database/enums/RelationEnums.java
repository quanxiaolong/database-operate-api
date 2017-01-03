/*
 * Copyright (c) 2014 中国国际图书贸易集团公司 
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.enums;

/**
 * <p>标题：RelationCondition </p>
 * <p>
 *    功能描述：关系枚举
 * </p>
 * <p>创建日期：2015年12月17日下午3:01:30</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class RelationEnums {

	/**
	 * <p>标题：LogicRelation </p>
	 * <p>
	 *    功能描述：逻辑关系枚举
	 * </p>
	 * <p>创建日期：2015年12月17日下午3:12:50</p>
	 * <p>作者：权小龙</p>
	 * <p>版本：1.0</p>
	 */
	public static enum Logic{
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
	
	/**
	 * <p>标题：InType </p>
	 * <p>
	 *    功能描述：包含条件枚举
	 * </p>
	 * <p>创建日期：2015年12月17日下午3:59:52</p>
	 * <p>作者：权小龙</p>
	 * <p>版本：1.0</p>
	 */
	public static enum InType{
		IN("in"),
		NOT_IN("not in");
		private String code;
		private InType(String code){
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
			InType[] emumArray=InType.values();
			for(int index=0,len=emumArray.length;index<len;index++){
				InType enumItem=emumArray[index];
				if(value.equals(enumItem.name()))
				{
					contain=true;
					break;
				}
			}
			return contain;
		}
	}
	
	/**
	 * <p>标题：SortRelation </p>
	 * <p>
	 *    功能描述：排序关系枚举
	 * </p>
	 * <p>创建日期：2015年12月17日下午3:11:42</p>
	 * <p>作者：权小龙</p>
	 * <p>版本：1.0</p>
	 */
	public static enum Sort{
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
	/**
	 * <p>标题：CompareRelation </p>
	 * <p>
	 *    功能描述：比较关系枚举
	 * </p>
	 * <p>创建日期：2015年12月17日下午3:11:36</p>
	 * <p>作者：权小龙</p>
	 * <p>版本：1.0</p>
	 */
	public static enum Compare{
		GREATER(">"),
		GREATER_EQUAL(">="),
		LESS("<"),
		LESS_EQUAL("<="),
		EQUAL("="),
		NOT_EQUAL("<>"),
		LIKE("LIKE");
		
		private String code;
		private Compare(String code){
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
			Compare[] emumArray=Compare.values();
			for(int index=0,len=emumArray.length;index<len;index++){
				Compare enumItem=emumArray[index];
				if(value.equals(enumItem.name()))
				{
					contain=true;
					break;
				}
			}
			return contain;
		}
	}
	/**
	 * <p>标题：EmptyType </p>
	 * <p>
	 *    功能描述：空字段判断枚举
	 * </p>
	 * <p>创建日期：2016年1月28日上午11:49:52</p>
	 * <p>作者：权小龙</p>
	 * <p>版本：1.0</p>
	 */
	public static enum EmptyType{
		NULL("is"),
		NOT_NULL("is not"),
		;
		private String code;
		private EmptyType(String code){
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
			EmptyType[] emumArray=EmptyType.values();
			for(int index=0,len=emumArray.length;index<len;index++){
				EmptyType enumItem=emumArray[index];
				if(value.equals(enumItem.name()))
				{
					contain=true;
					break;
				}
			}
			return contain;
		}
	}
}
