package cn.com.cibtc.database.model.condition.field;

import java.util.Map;

/**
 * <p>标题： SqlField</p>
 * <p>
 *    功能描述：SQL直接查询--处理类
 * </p>
 * <p>创建日期：2017年6月19日 上午11:05:44</p>
 * <p>作者：陶聪</p>
 * <p>版本：2.0</p>
 */
public class SqlField extends Field {

	private static final long serialVersionUID = 1L;

	String sqlWhere;
	Object[] params;
	Map<String, Object> sqlMap;

	/**
	 * @param sqlWhere
	 *            字符串格式：colum1 = #{} and colum2 = #{}
	 * @param params
	 *            实际参数集合
	 */
	public SqlField(String sqlWhere, Object[] params) {
		super(null);
		this.sqlWhere = sqlWhere;
		this.params = params;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public Map<String, Object> getSqlMap() {
		return sqlMap;
	}

	public void setSqlMap(Map<String, Object> sqlMap) {
		this.sqlMap = sqlMap;
	}
}