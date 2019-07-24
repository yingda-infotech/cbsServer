//package cn.com.git.cbs.dbapi.daoimpl;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.stereotype.Repository;
//
//import cn.com.git.cbs.dao.CommDao;
//import cn.com.git.cbs.exception.CbsRunException;
//import cn.com.git.cbs.mybatis.model.Test2;
//
//@Repository(value="test2ImplDao")
//public class Test2ImplDao extends  CommDao<Test2>{
//
//	@Override
//	protected Test2 copyRow(ResultSet rs) throws SQLException {
//		
//		Test2 test2 = new Test2();
//		test2.setId(rs.getBigDecimal("id"));
//		test2.setMuid(rs.getString("muid"));
//		test2.setWorkseq(rs.getString("workseq"));
//		test2.setWuid(rs.getString("wuid"));
//		test2.setWumark(rs.getString("wumark"));
//		test2.setEntrycond(rs.getString("entrycond"));
//		test2.setEntryfunc(rs.getString("entryfunc"));
//		test2.setCtrlmode(rs.getString("ctrlmode"));
//		test2.setCommitmode(rs.getString("commitmode"));
//		test2.setFlowmode(rs.getString("flowmode"));
//		test2.setPrewumark(rs.getString("prewumark"));
//		test2.setClearprdt(rs.getString("clearprdt"));
//		test2.setFeetype(rs.getString("feetype"));
//		test2.setAnalysecode(rs.getString("analysecode"));
//		test2.setClasscode1(rs.getString("classcode1"));
//		test2.setClasscode2(rs.getString("classcode2"));
//		test2.setClasscode3(rs.getString("classcode3"));
//		test2.setClasscode4(rs.getString("classcode4"));
//		test2.setParam1(rs.getString("param1"));
//		test2.setParam2(rs.getString("param2"));
//		test2.setParam3(rs.getString("param3"));
//		test2.setMemo(rs.getString("memo"));
//		
//		return test2;
//	}
//
//	@Override
//	protected String getSqlForSelectTable() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected String getSqlForSelect() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("select id,muid,workseq,wuid,wumark,entrycond,entryfunc,ctrlmode,commitmode,flowmode,prewumark,clearprdt")
//			.append(",feetype,analysecode,classcode1,classcode2,classcode3,classcode4,param1,param2,param3,memo")
//			.append(" from test2 where id=:id");
//		
//		return sb.toString();
//	}
//
//	@Override
//	protected String getSqlForInsert() {
//		// TODO Auto-generated method stub
//		StringBuffer sb = new StringBuffer();
//		sb.append("insert into test2(id,muid,workseq,wuid,wumark,entrycond,entryfunc,ctrlmode,commitmode,flowmode,prewumark,clearprdt"
//				+ ",feetype,analysecode,classcode1,classcode2,classcode3,classcode4,param1,param2,param3,memo"
//				+ ") values(:id,:muid,:workseq,:wuid,:wumark,:entrycond,:entryfunc,:ctrlmode,:commitmode,:flowmode,:prewumark,:clearprdt"
//				+ ",:feetype,:analysecode,:classcode1,:classcode2,:classcode3,:classcode4,:param1,:param2,:param3,:memo)");
//		
//		return sb.toString();
//	}
//
//	@Override
//	protected String getSqlForUpdate() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected String getSqlForDelete() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
////	@Override
////	protected Map getKeys(Test2 t) {
////		// TODO Auto-generated method stub
////			Map<String, Object> paramMap = new HashMap<String, Object>();  
////			paramMap.put("id", t.getId());
////			paramMap.put("muid", t.getMuid());
////			paramMap.put("workseq", t.getWorkseq());
////			paramMap.put("wuid", t.getWuid());
////			paramMap.put("wumark", t.getWumark());
////			paramMap.put("entrycond", t.getEntrycond());
////			paramMap.put("entryfunc", t.getEntryfunc());
////			paramMap.put("ctrlmode", t.getCtrlmode());
////			paramMap.put("commitmode", t.getCommitmode());
////			paramMap.put("flowmode", t.getFlowmode());
////			paramMap.put("prewumark", t.getPrewumark());
////			paramMap.put("clearprdt", t.getClearprdt());
////			paramMap.put("feetype", t.getFeetype());
////			paramMap.put("analysecode", t.getAnalysecode());
////			paramMap.put("classcode1", t.getClasscode1());
////			paramMap.put("classcode2", t.getClasscode2());
////			paramMap.put("classcode3", t.getClasscode3());
////			paramMap.put("classcode4", t.getClasscode4());
////			paramMap.put("param1", t.getParam1());
////			paramMap.put("param2", t.getParam2());
////			paramMap.put("param3", t.getParam3());
////			paramMap.put("memo", t.getMemo());
////			return paramMap;
////	}
//
//	@Override
//	protected int chkColumnNull(Test2 t) throws CbsRunException {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	protected void printColumnLog(Test2 t) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
