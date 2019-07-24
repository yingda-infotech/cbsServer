/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.git.cbs.common.utils.ValidateUtil;
import cn.com.git.cbs.exception.DBException;
import cn.com.git.cbs.exception.RecordNotFoundException;
import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.model.PersistObject;
import cn.com.git.cbs.model.ReturnObj;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * Mybatis实现的公共方法
 * 
 * @author xia
 * @param <T>
 *            DAO对应的 表对象泛型
 *
 */
@Repository 
public abstract class GenericDAO<T extends PersistObject> extends SqlSessionDaoSupport {

	private final static PlatformLogger LOGGER = PlatformLogger.create();

	private final static String SELECTONEFORUPDATE = ".selectoneForUpdate";

	private final static String MAPPER = ".";

	private final static String SELECT = ".select";

	private final static String SELECTFORUPDATE = ".selectForUpdate";

	private final static String INSERT = ".insert";

	private final static String UPDATEBYPRIMARYKEY = ".updateByPrimaryKey";

	private final static String DELETEBYPRIMARYKEY = ".deleteByPrimaryKey";

	private final static int rowLimit = 1000;

	private final static int SUCCESSFUL = 1;

	protected boolean checkNull = false;

	// 限制查询条数
	private final static RowBounds QUERY_LIMIT = new RowBounds(0, rowLimit);

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	};

	/**
	 * 根据主键查询数据(forUpdate方式查询) 需要在事物中使用<br>
	 * 
	 * @param sqlID
	 *            自定义的 sqlID
	 * @param t
	 *            传入的 表对象
	 * @return T 公共的返回结果
	 */
	public T selectOneForUpdate(String sqlID, T t) throws DBException, RecordNotFoundException {
		T ret;
		try {
			if ((null == sqlID) || "".equals(sqlID)) {
				ret = getSqlSession().selectOne(getMapperName(t) + SELECTONEFORUPDATE, t);
			} else {
				ret = getSqlSession().selectOne(getMapperName(t) + MAPPER + sqlID, t);
			}			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.debug(t.toString());
			throw new DBException(e);
		}

		if (ret == null && checkNull) {
			throw new RecordNotFoundException(getTableName(t));
		}
		return ret;
	}

	/**
	 * 根据应用层自定义的查询语句 sqlID 来查询单条数据
	 * 
	 * @param sqlID
	 *            自定义的 sqlID
	 * @param t
	 *            传入的 表对象
	 * @return T 公共的返回结果
	 */
	public T selectOne(String sqlID, T t) throws DBException, RecordNotFoundException {
		Cursor<T> cursor;
		T ret = null;
		try {
			
			//selectOne底层是使用 selectList，换成 selectCursor			
			if ((null == sqlID) || "".equals(sqlID)) {
				cursor = getSqlSession().selectCursor(getMapperName(t) + SELECT, t);
			} else {
				cursor = getSqlSession().selectCursor(getMapperName(t) + MAPPER + sqlID, t);
			}
			
			Iterator<T> iterator = cursor.iterator();
			if(iterator.hasNext()){
				ret = iterator.next();
				return ret;
			}
						
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.debug(t.toString());
			throw new DBException(e);
		}
		if (ret == null && checkNull) {
			throw new RecordNotFoundException(getTableName(t));
		}
		return ret;
		
		
		
//		T ret;
//		try {
//			if ((null == sqlID) || "".equals(sqlID)) {
//				ret = getSqlSession().selectOne(getMapperName(t) + SELECT, t);
//			} else {
//				ret = getSqlSession().selectOne(getMapperName(t) + MAPPER + sqlID, t);
//			}			
//		} catch (Exception e) {
//			LOGGER.error(e.getMessage(), e);
//			LOGGER.debug(t.toString());
//			throw new DBException(e);
//		}
//
//		if (ret == null && checkNull) {
//			throw new RecordNotFoundException(getTableName(t));
//		}
//		return ret;
	}

	/**
	 * for update 方式根据条件查询多条数据
	 * 
	 * @param sqlID
	 *            自定义的 SQL编号
	 * @param t
	 *            传入的 表对象
	 * @return List 公共的返回结果
	 */
	public List<T> selectForUpdate(String sqlID, T t) throws DBException, RecordNotFoundException {
		List<T> ret;
		try {
			if ((null == sqlID) || "".equals(sqlID)) {
				ret = getSqlSession().selectList(getMapperName(t) + SELECTFORUPDATE, t);
			} else {
				ret = getSqlSession().selectList(getMapperName(t) + MAPPER + sqlID, t);
			}			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.debug(t.toString());
			throw new DBException(e);
		}

		if (ret == null && checkNull) {
			throw new RecordNotFoundException(getTableName(t));
		}
		return ret;
	}

	/**
	 * 根据应用层自定义的查询语句 sqlID 来查询多条数据
	 * 
	 * @param sqlID
	 *            自定义的 sqlID
	 * @param t
	 *            传入的 表对象
	 * @return List 公共的返回结果
	 */
	public List<T> select(String sqlID, T t) throws DBException, RecordNotFoundException {
		List<T> ret;
		try {
			if ((null == sqlID) || "".equals(sqlID)) {
				ret = getSqlSession().selectList(getMapperName(t) + SELECT, t, QUERY_LIMIT);
			} else {
				ret = getSqlSession().selectList(getMapperName(t) + MAPPER + sqlID, t, QUERY_LIMIT);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.debug(t.toString());
			throw new DBException(e);
		}

		if (ret == null && checkNull) {
			throw new RecordNotFoundException(getTableName(t));
		}
		return ret;

	}

	/**
	 * 根据应用层自定义的查询语句 sqlID 来查询多条数据,返回查询的Cursor
	 * 
	 * @param sqlID
	 *            自定义的 sqlID
	 * @param t
	 *            传入的 表对象
	 * @return Cursor Cursor对象
	 */
	public Cursor<T> selectCursor(String sqlID, T t) throws DBException, RecordNotFoundException {
		Cursor<T> ret;
		try {
			if ((null == sqlID) || "".equals(sqlID)) {
				ret = getSqlSession().selectCursor(getMapperName(t) + SELECT, t);
			} else {
				ret = getSqlSession().selectCursor(getMapperName(t) + MAPPER + sqlID, t);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.debug(t.toString());
			throw new DBException(e);
		}

		if (ret == null && checkNull) {
			throw new RecordNotFoundException(getTableName(t));
		}
		return ret;

	}

	/**
	 * 插入一条数据
	 * 
	 * @param sqlID
	 *            自定义的insert id
	 * @param t
	 *            传入的 表对象
	 * @return int sql执行结果
	 */
	public int insert(String sqlID, T t) throws DBException {
		int ret = 0;
		try {
			ReturnObj<Set<ConstraintViolation<PersistObject>>> returnObj = ValidateUtil.validateObject(t);
			Set<ConstraintViolation<PersistObject>> violations = (Set<ConstraintViolation<PersistObject>>) returnObj.getData();
			int retValue = returnObj.getRetValue();
			if (retValue == GenericDAO.SUCCESSFUL) {
				if ((null == sqlID) || "".equals(sqlID)) {
					ret = getSqlSession().insert(getMapperName(t) + INSERT, t);
				} else {
					ret = getSqlSession().insert(getMapperName(t) + MAPPER + sqlID, t);
				}
			} else {
				String errorMsg = ValidateUtil.generateErrorMsg(violations);
				throw ExceptionUtils.returnError("DB0003", errorMsg);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.debug(t.toString());
			throw new DBException(e);
		}
		return ret;
	}


	/**
	 * 根据主键修改一条记录 将为空的字段在数据库中置为NULL
	 * 
	 * @param sqlID
	 *            自定义的update id
	 * @param t
	 *            传入的 表对象
	 * @return int sql执行结果
	 */
	public int updateByPrimaryKey(String sqlID, T t) throws DBException, RecordNotFoundException {
		int ret = 0;
		try {
			ReturnObj<Set<ConstraintViolation<PersistObject>>> returnObj = ValidateUtil.validateObject(t);
			Set<ConstraintViolation<PersistObject>> violations = (Set<ConstraintViolation<PersistObject>>) returnObj.getData();
			int retValue = returnObj.getRetValue();
			if (retValue == GenericDAO.SUCCESSFUL) {
				if ((null == sqlID) || "".equals(sqlID)) {
					ret = getSqlSession().update(getMapperName(t) + UPDATEBYPRIMARYKEY, t);
				} else {
					ret = getSqlSession().update(getMapperName(t) + MAPPER + sqlID, t);
				}
			} else {
				String errorMsg = ValidateUtil.generateErrorMsg(violations);
				throw ExceptionUtils.returnError("DB0003", errorMsg);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.debug(t.toString());
			throw new DBException(e);
		}

		if (ret == 0 && checkNull) {
			throw new RecordNotFoundException(getTableName(t));
		}
		return ret;
	}


	/**
	 * 根据主键删除一条记录
	 * 
	 * @param sqlID
	 *            自定义的delete id
	 * @param t
	 *            传入的 表对象
	 * @return int sql执行结果
	 */
	public int deleteByPrimaryKey(String sqlID, T t) throws DBException, RecordNotFoundException {
		int ret;
		try {
			if ((null == sqlID) || "".equals(sqlID)) {
				ret = getSqlSession().delete(getMapperName(t) + DELETEBYPRIMARYKEY, t);
			} else {
				ret = getSqlSession().delete(getMapperName(t) + MAPPER + sqlID, t);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.debug(t.toString());
			throw new DBException(e);
		}

		if (ret == 0 && checkNull) {
			throw new RecordNotFoundException(getTableName(t));
		}
		return ret;
	}


	/**
	 * 调用存储过程(没有区分插入、修改、删除的存储过程，都用这个来调用)
	 * 
	 * @param sqlID
	 *            自定义的procedure sql id
	 * @param t
	 *            传入的 表对象
	 * @param param
	 *            参数的map
	 * @return int 存储过程执行结果
	 */
	public int callUpdateProcedure(String sqlID, T t, Map<String, Object> param)
			throws DBException, RecordNotFoundException {
		int ret;
		try {
			ret = getSqlSession().update(getMapperName(t) + MAPPER + sqlID, param);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.debug(t.toString());
			throw new DBException(e);
		}

		if (ret == 0 && checkNull) {
			throw new RecordNotFoundException(getTableName(t));
		}
		return ret;
	}

	private String getTableName(T t) {
		return t.getClass().getSimpleName().replace("TBL_", "");
	}
	
	private String getMapperName(T t) {
		return t.getClass().getCanonicalName();
	}
}
