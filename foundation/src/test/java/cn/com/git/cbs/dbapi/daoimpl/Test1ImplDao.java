//package cn.com.git.cbs.dbapi.daoimpl;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import cn.com.git.cbs.dao.CommDao;
//import cn.com.git.cbs.exception.CbsRunException;
//import cn.com.git.cbs.mybatis.model.Test1;
//
//@Repository(value="test1ImplDao")
//public class Test1ImplDao extends  CommDao<Test1>{
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(Test1ImplDao.class);
//	
//	@Override
//	protected Test1 copyRow(ResultSet rs) throws SQLException {
//
//		Test1 test1 = new Test1();
////		test1.setId(rs.getBigDecimal("id"));
//		test1.setOprtime(rs.getString("oprtime"));
//		test1.setCdlx(rs.getString("cdlx"));
//		
//		return test1;
//	}
//
//	@Override
//	protected String getSqlForSelect() {
//		// TODO Auto-generated method stub
////		"select id,oprtime,cdlx from test where name=:name";  
//		StringBuffer sb = new StringBuffer();
//		sb.append("select id,oprtime,cdlx from test1 where id=:id");
//		
//		return sb.toString();
//	}
//	
//	@Override
//	protected String getSqlForSelectTable() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("select id,oprtime,cdlx from test1 where ");
//		
//		return sb.toString();
//	}
//
//	@Override
//	protected String getSqlForInsert() {
//		// TODO Auto-generated method stub
//		//"insert into test(name) values(:name)";
//		StringBuffer sb = new StringBuffer();
//		sb.append("insert into test1(id,oprtime,cdlx) values(:id,:oprtime,:cdlx)");
//		
//		return sb.toString();
//	}
//	
//	@Override
//	protected String getSqlForUpdate() {
//		// TODO Auto-generated method stub
//		//update test set ID=(:name), OPRTIME=(:name), CDLX=(:name)   where name=:name
//		StringBuffer sb = new StringBuffer();
//		sb.append("update test1 set id=(:id),oprtime=(:oprtime),cdlx=(:cdlx) where id=:id");
//		
//		return sb.toString();
//	}
//
//	@Override
//	protected String getSqlForDelete() {
//		//delete from test where name=:name";   
//		StringBuffer sb = new StringBuffer();
//		sb.append("delete from test1 where id=:id");
//		
//		return sb.toString();
//	}
//
////	@Override
////	protected Map getKeys(Test1 t) {
////		// TODO Auto-generated method stub
////		Map<String, Object> paramMap = new HashMap<String, Object>();  
////		paramMap.put("id", t.getId());
////		paramMap.put("oprtime", t.getOprtime());
////		paramMap.put("cdlx", t.getCdlx());
////		return paramMap;
////	}
//
//	@Override
//	protected int chkColumnNull(Test1 t) throws CbsRunException {
//		// TODO Auto-generated method stub
//		if(t.getId() == null){	
//			throw new CbsRunException(CbsRunException.getErrorMsg("DB0015","id"));			
//		}
//		return SQLFAILURE;
//	}
//
//	@Override
//	protected void printColumnLog(Test1 t) {
//		// TODO Auto-generated method stub
//		LOGGER.debug("TEST1:id=BigDecimal," + t.getId());      
//		LOGGER.debug("TEST1:oprtime=String," +  t.getOprtime());      
//		LOGGER.debug("TEST1:cdlx=String," +  t.getCdlx());      
//	}
//
//
//
//
//			
//
//}
