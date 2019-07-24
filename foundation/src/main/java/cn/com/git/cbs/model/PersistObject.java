package cn.com.git.cbs.model;

/**
 * 数据pojo使用的通用数据对象
 * 
 * @author DengJia
 *
 */
public abstract class PersistObject extends DataObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7824928940667427003L;

//	/**
//	 * 获取的TableName需要去掉 TBL_ eg:Sample
//	 * 
//	 * @return 去掉TBL_前缀的表名称
//	 */
//	protected String getTableName() {
//		return this.getClass().getSimpleName().replace("TBL_", "");
//	}

//	/**
//	 * 获取类的完整mapping名称 eg:cn.com.git.cbs.datamodel.TBL_Sample
//	 * 
//	 * @return 类的完整mapping名称
//	 */
//	protected String getMapperName() {
//		return this.getClass().getCanonicalName();
//	}
}
