/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.datamodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.com.git.cbs.model.PersistObject;

/**
 * Subtxdef，pojo类
 */ 
public class TBL_Subtxdef extends PersistObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5310289103343926269L;

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
	 * 返回子交易名称
	 * @return 返回 子交易名称,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getWuname() {       
		return getString("wuname");
	}
    
	/**
	 * 设置子交易名称
	 * @param wuname 子交易名称
	 */
	public void setWuname(String wuname) {      
		set("wuname",wuname);
	}    
	
	/**
	 * 返回子系统代码
	 * @return 返回 子系统代码,长度需要在0和3之间
	 */
	@Length(max=3)	
	public String getSubid() {       
		return getString("subid");
	}
    
	/**
	 * 设置子系统代码
	 * @param subid 子系统代码
	 */
	public void setSubid(String subid) {      
		set("subid",subid);
	}    
	
	/**
	 * 返回正交易函数名称
	 * @return 返回 正交易函数名称,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getWufunc() {       
		return getString("wufunc");
	}
    
	/**
	 * 设置正交易函数名称
	 * @param wufunc 正交易函数名称
	 */
	public void setWufunc(String wufunc) {      
		set("wufunc",wufunc);
	}    
	
	/**
	 * 返回反交易函数名称
	 * @return 返回 反交易函数名称,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getWurevfunc() {       
		return getString("wurevfunc");
	}
    
	/**
	 * 设置反交易函数名称
	 * @param wurevfunc 反交易函数名称
	 */
	public void setWurevfunc(String wurevfunc) {      
		set("wurevfunc",wurevfunc);
	}    
	
	/**
	 * 返回允许冲正标志
	 * @return 返回 允许冲正标志,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getWualwrevflag() {       
		return getString("wualwrevflag");
	}
    
	/**
	 * 设置允许冲正标志
	 * @param wualwrevflag 允许冲正标志
	 */
	public void setWualwrevflag(String wualwrevflag) {      
		set("wualwrevflag",wualwrevflag);
	}    
	
	/**
	 * 返回子交易类型
	 * @return 返回 子交易类型,长度需要在0和2之间
	 */
	@Length(max=2)	
	public String getWutype() {       
		return getString("wutype");
	}
    
	/**
	 * 设置子交易类型
	 * @param wutype 子交易类型
	 */
	public void setWutype(String wutype) {      
		set("wutype",wutype);
	}    
	
	/**
	 * 返回子交易模式
	 * @return 返回 子交易模式,长度需要在0和2之间
	 */
	@Length(max=2)	
	public String getWumode() {       
		return getString("wumode");
	}
    
	/**
	 * 设置子交易模式
	 * @param wumode 子交易模式
	 */
	public void setWumode(String wumode) {      
		set("wumode",wumode);
	}    
	
	/**
	 * 返回交易级别
	 * @return 返回 交易级别,长度需要在0和2之间
	 */
	@Length(max=2)	
	public String getWulevel() {       
		return getString("wulevel");
	}
    
	/**
	 * 设置交易级别
	 * @param wulevel 交易级别
	 */
	public void setWulevel(String wulevel) {      
		set("wulevel",wulevel);
	}    
	
	/**
	 * 返回保留字段1
	 * @return 返回 保留字段1,长度需要在0和16之间
	 */
	@Length(max=16)	
	public String getRsvitem1() {       
		return getString("rsvitem1");
	}
    
	/**
	 * 设置保留字段1
	 * @param rsvitem1 保留字段1
	 */
	public void setRsvitem1(String rsvitem1) {      
		set("rsvitem1",rsvitem1);
	}    
	
	/**
	 * 返回保留字段2
	 * @return 返回 保留字段2,长度需要在0和16之间
	 */
	@Length(max=16)	
	public String getRsvitem2() {       
		return getString("rsvitem2");
	}
    
	/**
	 * 设置保留字段2
	 * @param rsvitem2 保留字段2
	 */
	public void setRsvitem2(String rsvitem2) {      
		set("rsvitem2",rsvitem2);
	}    
}