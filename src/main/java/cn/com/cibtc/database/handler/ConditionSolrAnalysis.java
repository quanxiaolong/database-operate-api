/*
 * Copyright (c) 2016 权小龙
 * All rights reserved.
 *  
 */
package cn.com.cibtc.database.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.util.ClientUtils;
import org.springframework.util.CollectionUtils;

import cn.com.cibtc.database.enums.Logic;
import cn.com.cibtc.database.enums.Sort;
import cn.com.cibtc.database.model.condition.BaseCondition;
import cn.com.cibtc.database.model.condition.ConditionBlock;
import cn.com.cibtc.database.model.condition.field.CompareField;
import cn.com.cibtc.database.model.condition.field.EmptyField;
import cn.com.cibtc.database.model.condition.field.Field;
import cn.com.cibtc.database.model.condition.field.InField;
import cn.com.cibtc.database.model.condition.field.SortField;
import cn.com.cibtc.database.model.condition.solr.SolrCondition;
import cn.com.cibtc.database.model.condition.solr.SolrFactCondition;
import cn.com.cibtc.database.util.ConditionUtil;

/**
 * <p>标题：ConditionSolrAnalysis </p>
 * <p>
 *    功能描述：Solr条件对象解析工具
 * </p>
 * <p>创建日期：2016年2月3日上午10:54:55</p>
 * <p>作者：权小龙</p>
 * <p>版本：1.0</p>
 */
public class ConditionSolrAnalysis implements IConditionAnalysis{
	
	private static final String SOLR_LOGIC_AND = " AND ";		//solr查询关系：AND
	
	private static final String SOLR_LOGIC_OR = " OR ";			//solr查询关系：OR
	
	@Override
	public <FieldEnum extends Enum<FieldEnum>> Object parseSql(BaseCondition<FieldEnum> condition) {
		SolrCondition<FieldEnum> solrCondition= (SolrCondition<FieldEnum>) condition;
		SolrQuery solrQuery = new SolrQuery(parseSearchParam(solrCondition));
		// 设置查询分页信息
		Integer pageSize = solrCondition.getPageSize();
		Integer currentPage = solrCondition.getCurrentPage();
		solrQuery.setStart((currentPage - 1) * pageSize);
		solrQuery.setRows(pageSize);
		
		// 解析排序
		List<SortField> sortFields = solrCondition.getSortFields();
		solrQuery.setSorts(parseSort(sortFields));
		
		// 解析统计
		if (solrCondition.getFact()) {
			setFacet(solrQuery, solrCondition.getFactCondition());
		}
		
		return solrQuery;
	}
	
	/*=================================================私有辅助方式========================================================*/
	/**
	 * 解析查询条件
	 * @author Taocong
	 * @date 2016年7月25日 下午4:28:18
	 */
	private static <FieldEnum extends Enum<FieldEnum>> String parseSearchParam(SolrCondition<FieldEnum> condition) {
		String searchParam = "-*:*";			// 当条件是空或null时，查询不到信息
		if(!ConditionUtil.isConditionAvailable(condition))
			return searchParam;
		List<ConditionBlock<FieldEnum>> conditionBlocks = condition.getConditionBlocks();
		
		searchParam = parseConditonBlocks(conditionBlocks, "*:*");
		
		return searchParam;
	}

	/**
	 * 解析block条件
	 * @param conditionBlocks
	 * @param defaultSearchParam
	 * @return
	 */
	private static <FieldEnum extends Enum<FieldEnum>> String parseConditonBlocks(
			List<ConditionBlock<FieldEnum>> conditionBlocks, String defaultSearchParam) {
		String searchParam = defaultSearchParam;
		/*
		 * 如果条件集合为null或空，直接返回字符串---查询不到信息
		 * 如果不符合以上条件，解析每一个查询条件
		 */
		if (!CollectionUtils.isEmpty(conditionBlocks)) {
			int size = conditionBlocks.size(); 	// 条件集合长度
			int i = 1; 							// 标识位，当标识位与条件集合长度相等时，说明是最后一条数据
			StringBuffer searchParamBuffer = new StringBuffer();
			for (ConditionBlock<FieldEnum> conditionBlock : conditionBlocks) {
				searchParamBuffer.append(parseBlock(conditionBlock,false));
				if (i < size) {					// 当标识小于集合长度时，说明不是最后一条，需要添加“组合关系”
					searchParamBuffer.append(parseLogic(conditionBlock.getLogic()));
				}
				i++;							// 标识位自加1
			}
			searchParam = searchParamBuffer.toString();
		}
		return searchParam;
	}

	/**
	 * TODO 需要针对last做特殊处理
	 * @param conditionBlock
	 * @return
	 * @author Taocong
	 * @date 2016年7月25日 下午5:15:42
	 */
	private static Object parseBlock(ConditionBlock<?> conditionBlock,boolean last) {
		String searchParam = "";
		List<Field> fields = conditionBlock.getFields();
		
		if (!CollectionUtils.isEmpty(fields)) {
			int size = fields.size(); 			// 条件集合长度
			int i = 1; 							// 标识位，当标识位与条件集合长度相等时，说明是最后一条数据
			StringBuffer searchParamBuffer = new StringBuffer();
			for (Field field : fields) {
				boolean isLast = size == i;		// 是否最后一位，取决于标识位与条件集合长度是否相等
				
				/*
				 * 根据参数的类型进行不同的解析
				 */
				if (field instanceof CompareField) {
					searchParamBuffer.append(parseCompareField((CompareField)field, isLast));
				} else if (field instanceof InField) {
					parseInField((InField)field, isLast);
				} else if (field instanceof EmptyField) {
					parseEmptyField((EmptyField)field, isLast);
				}
				
				i++;							// 标识位自加1
			}
			searchParam = searchParamBuffer.toString();
		}
		return searchParam;
	}
	
	/**
	 * 转化“比较”类型的参数
	 * @param field
	 * @param isLast	是否是最后一个参数
	 * @return
	 * @author Taocong
	 * @date 2016年7月26日 下午2:16:45
	 */
	private static String parseCompareField(CompareField field, boolean isLast) {
		/*
		 * 获取条件部分
		 */
		String conditionType = field.getConditionType();			// 比较条件
		String colum = field.getFieldName();						// 字段名称
		String value = (String)field.getValue();					// 字段值
		value = replaceStringValue(value);							// 对字段值进行特殊字符的替换
		
		/*
		 * 拼装条件部分
		 */
		StringBuffer compareFieldParam = new StringBuffer("(");
		compareFieldParam.append(colum);
		compareFieldParam.append(":");
		
		switch (conditionType) {
		case "=":
			compareFieldParam.append(value);
			break;
		case ">=":
			compareFieldParam.append("[");
			compareFieldParam.append(value);
			compareFieldParam.append(" TO *]");
			break;
		case ">":
			compareFieldParam.append("{");
			compareFieldParam.append(value);
			compareFieldParam.append(" TO *]");
			break;
		case "<":
			compareFieldParam.append("[* TO ");
			compareFieldParam.append(value);
			compareFieldParam.append("}");
			break;
		case "<=":
			compareFieldParam.append("[* TO ");
			compareFieldParam.append(value);
			compareFieldParam.append("]");
			break;
		case "<>":
			compareFieldParam.append(value);
			compareFieldParam.insert(0, "-");
			break;
		case "LIKE":
			compareFieldParam.append("*");
			compareFieldParam.append(value);
			compareFieldParam.append("*");
			break;
		case "LLIKE":
			compareFieldParam.append("*");
			compareFieldParam.append(value);
			break;
		case "RLIKE":
			compareFieldParam.append(value);
			compareFieldParam.append("*");
			break;
		default:
			break;
		}
		
		compareFieldParam.append(")");
		
		if (!isLast) {
			// 不是最后一个参数才加载与下一个参数的关系
			compareFieldParam.append(parseLogic(field.getLogic()));
		}
		
		return compareFieldParam.toString();
	}
	
	/**
	 * 转化“区间”类型的参数
	 * @param field
	 * @param isLast	是否是最后一个参数
	 * @return
	 * @author Taocong
	 * @date 2016年7月26日 下午5:47:36
	 */
	private static String parseInField(InField field, boolean isLast) {
		/*
		 * 获取条件部分
		 */
		String conditionType = field.getConditionType();			// 比较条件
		String colum = field.getFieldName();						// 字段名称
		List<Object> values = field.getValues();					// 字段值
		
		/*
		 * 拼装条件部分
		 */
		StringBuffer inFieldParam = new StringBuffer("(");
		inFieldParam.append(colum);
		inFieldParam.append(":");
		inFieldParam.append("(");
		int size = values.size();
		int i = 1;
		for (Object value : values) {
			String param = replaceStringValue((String)value);		// 对字段值进行特殊字符的替换
			inFieldParam.append(param);
			if (i < size) {											// 当不是最后一条时，需要添加“ OR ”
				inFieldParam.append(SOLR_LOGIC_OR);
			}
		}
		inFieldParam.append("))");
		if ("not in".equals(conditionType)) {						// 如果是 not in 取非操作
			inFieldParam.insert(0, "-");
		}
		
		if (!isLast) {
			// 不是最后一个参数才加载与下一个参数的关系
			inFieldParam.append(parseLogic(field.getLogic()));
		}
		
		return inFieldParam.toString();
	}
	
	/**
	 * 转化“区间”类型的参数
	 * @param field
	 * @param isLast	是否是最后一个参数
	 * @return
	 * @author Taocong
	 * @date 2016年7月26日 下午5:47:36
	 */
	private static String parseEmptyField(EmptyField field, boolean isLast) {
		/*
		 * 获取条件部分
		 */
		String conditionType = field.getConditionType();			// 比较条件
		String colum = field.getFieldName();						// 字段名称
		
		/*
		 * 拼装条件部分
		 */
		StringBuffer inFieldParam = new StringBuffer("(");
		inFieldParam.append(colum);
		inFieldParam.append(":");
		inFieldParam.append("*");
		inFieldParam.append(")");
		if ("is not".equals(conditionType)) {						// 如果是 is not 取非操作
			inFieldParam.insert(0, "-");
		}
		
		if (!isLast) {
			// 不是最后一个参数才加载与下一个参数的关系
			inFieldParam.append(parseLogic(field.getLogic()));
		}
		
		return inFieldParam.toString();
	}

	/**
	 * 解析排序字段
	 * @param sortFields
	 * @return
	 * @author Taocong
	 * @date 2016年7月26日 下午6:29:52
	 */
	private static List<SortClause> parseSort(List<SortField> sortFields) {
		List<SortClause> sorClauseList = new ArrayList<>();
		for (SortField sortField : sortFields) {
			ORDER order = adaptSolrOrder(sortField);
			SortClause sortClause = new SortClause(sortField.getFieldName(), order);
			sorClauseList.add(sortClause);
		}
		return sorClauseList;
	}
	
	/**
	 * 将condition的关系连接转为solr中的关系连接
	 * @param logic
	 * @author Taocong
	 * @date 2016年7月25日 下午5:02:18
	 */
	private static String parseLogic(String logic) {
		if (Logic.AND.toString().equals(logic)) {
			return SOLR_LOGIC_AND;
		} else {
			return SOLR_LOGIC_OR;
		}
	}
	
	/**
	 * 对查询参数进行转义
	 * <p>1.如果参数为空，转为:\"\"</p>
	 * <p>2.替换solr关键字</p>
	 * @param param
	 * @return
	 * @author Taocong
	 * @date 2016年7月26日 上午11:33:16
	 */
	private static String replaceStringValue(String param) {
		if ("".equals(param)) {
			return "\"\"";
		}
		param =  ClientUtils.escapeQueryChars(param);		// 替换solr关键字
		
		return param;
	}
	
	/**
	 * 获取Solr排序枚举
	 * @param field
	 * @return
	 */
	private static ORDER adaptSolrOrder(SortField field){
		ORDER result = ORDER.asc;
		if(field !=null){
			if(Sort.DESC.toString().equals(field.getOrderBy())){
				result=ORDER.desc;
			}
		}
		return result;
	}
	
	/**
	 * 设置统计条件
	 * @param solrQuery
	 * @param factCondition
	 */
	private <FieldEnum extends Enum<FieldEnum>> void setFacet(SolrQuery solrQuery,
			SolrFactCondition<FieldEnum> factCondition) {
		solrQuery.setFacet(true);
		solrQuery.addFacetField(factCondition.getFacetFields());
		solrQuery.setFacetLimit(factCondition.getFacetLimit());
		solrQuery.setFacetMissing(factCondition.getFacetMissing());
		solrQuery.setFacetMinCount(factCondition.getFacetMinCount());
		// 设置fact中的查询
		List<ConditionBlock<FieldEnum>> conditionBlocks = factCondition.getConditionBlocks();
		if (!CollectionUtils.isEmpty(conditionBlocks)) {
			solrQuery.addFacetQuery(parseConditonBlocks(conditionBlocks, null));
		}
	}
}
