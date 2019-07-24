/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OraclePreparedStatement;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

/***
 * Oracle JDBC Bug补丁
 * Char字段无法使用PreparedStatement setString方法，必须使用 Oracle
 * setFixedChar方法。 适用于Oracle 10g/11g的JDBC驱动。
 * 参考<a href="https://community.oracle.com/message/506702">https://community.oracle.com/message/506702</a>
 * 
 * @author DengJia
 *  
 */
public class OracleCharStringTypeHandler implements TypeHandler<String> {

	@Override
	public void setParameter(PreparedStatement ps, int i, String parameter,
			JdbcType jdbcType) throws SQLException {
		OraclePreparedStatement ops = ps.unwrap(OraclePreparedStatement.class);
		ops.setFixedCHAR(i, parameter);
	}

	@Override
	public String getResult(ResultSet rs, String columnName)
			throws SQLException {
		String ret = rs.getString(columnName);
		if (ret != null) {
			return ret.trim();
		}
		return null;
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		String ret = rs.getString(columnIndex);
		if (ret != null) {
			return ret.trim();
		}
		return null;
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		String ret = cs.getString(columnIndex);
		if (ret != null) {
			return ret.trim();
		}
		return null;
	}

}