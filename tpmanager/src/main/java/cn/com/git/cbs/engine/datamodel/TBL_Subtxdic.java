/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.datamodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.com.git.cbs.model.PersistObject;

/**
 * Subtxdic，pojo类
 */ 
public class TBL_Subtxdic extends PersistObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7448957470287508645L;

	/**
	 * 返回子交易代码
	 * @return 返回 子交易代码,非空,长度需要在0和8之间
	 */
	@NotEmpty
	@Length(max=8)	
	public String getWuid() {       
		return getString("wuid");
	}
    
	/**
	 * 设置子交易代码
	 * @param wuid 子交易代码
	 */
	public void setWuid(String wuid) {      
		set("wuid",wuid);
	}    
	
	/**
	 * 返回子交易内部字典名称
	 * @return 返回 子交易内部字典名称,非空,长度需要在0和32之间
	 */
	@NotEmpty
	@Length(max=32)	
	public String getLocalname() {       
		return getString("localname");
	}
    
	/**
	 * 设置子交易内部字典名称
	 * @param localname 子交易内部字典名称
	 */
	public void setLocalname(String localname) {      
		set("localname",localname);
	}    
	
	/**
	 * 返回子交易内部字典说明
	 * @return 返回 子交易内部字典说明,长度需要在0和40之间
	 */
	@Length(max=40)	
	public String getMemo() {       
		return getString("memo");
	}
    
	/**
	 * 设置子交易内部字典说明
	 * @param memo 子交易内部字典说明
	 */
	public void setMemo(String memo) {      
		set("memo",memo);
	}    
	
	/**
	 * 返回字典数据类型
	 * @return 返回 字典数据类型,非空,长度需要在0和1之间
	 */
	@NotEmpty
	@Length(max=1)	
	public String getDicttype() {       
		return getString("dicttype");
	}
    
	/**
	 * 设置字典数据类型
	 * @param dicttype 字典数据类型
	 */
	public void setDicttype(String dicttype) {      
		set("dicttype",dicttype);
	}    
	
	/**
	 * 返回输入输出标志
	 * @return 返回 输入输出标志,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getInoutflag() {       
		return getString("inoutflag");
	}
    
	/**
	 * 设置输入输出标志
	 * @param inoutflag 输入输出标志
	 */
	public void setInoutflag(String inoutflag) {      
		set("inoutflag",inoutflag);
	}    
	
	/**
	 * 返回条件说明
	 * @return 返回 条件说明,长度需要在0和254之间
	 */
	@Length(max=254)	
	public String getCond() {       
		return getString("cond");
	}
    
	/**
	 * 设置条件说明
	 * @param cond 条件说明
	 */
	public void setCond(String cond) {      
		set("cond",cond);
	}    
}