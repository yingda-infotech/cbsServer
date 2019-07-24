/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.datamodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.com.git.cbs.model.PersistObject;

/**
 * Pubdatadic，pojo类
 */ 
public class TBL_Pubdatadic extends PersistObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5025724157477547709L;

	/**
	 * 返回字典代码
	 * @return 返回 字典代码,非空,长度需要在0和32之间
	 */
	@NotEmpty
	@Length(max=32)	
	public String getDictcode() {       
		return getString("dictcode");
	}
    
	/**
	 * 设置字典代码
	 * @param dictcode 字典代码
	 */
	public void setDictcode(String dictcode) {      
		set("dictcode",dictcode);
	}    
	
	/**
	 * 返回字典名称
	 * @return 返回 字典名称,非空,长度需要在0和40之间
	 */
	@NotEmpty
	@Length(max=40)	
	public String getDictname() {       
		return getString("dictname");
	}
    
	/**
	 * 设置字典名称
	 * @param dictname 字典名称
	 */
	public void setDictname(String dictname) {      
		set("dictname",dictname);
	}    
	
	/**
	 * 返回备注信息
	 * @return 返回 备注信息,长度需要在0和256之间
	 */
	@Length(max=256)	
	public String getDictmemo() {       
		return getString("dictmemo");
	}
    
	/**
	 * 设置备注信息
	 * @param dictmemo 备注信息
	 */
	public void setDictmemo(String dictmemo) {      
		set("dictmemo",dictmemo);
	}    
	
	/**
	 * 返回字典类型
	 * @return 返回 字典类型,非空,长度需要在0和1之间
	 */
	@NotEmpty
	@Length(max=1)	
	public String getDicttype() {       
		return getString("dicttype");
	}
    
	/**
	 * 设置字典类型
	 * @param dicttype 字典类型
	 */
	public void setDicttype(String dicttype) {      
		set("dicttype",dicttype);
	}    
	
	/**
	 * 返回字典长度
	 * @return 返回 字典长度
	 */	
	public int getDictlen() {       
		return (int) getNumber("dictlen");
	}
    
	/**
	 * 设置字典长度
	 * @param dictlen 字典长度
	 */
	public void setDictlen(int dictlen) {      
		setNumber("dictlen",dictlen);
	}    
	
	/**
	 * 返回字典小数位数
	 * @return 返回 字典小数位数
	 */	
	public int getDeclen() {       
		return (int) getNumber("declen");
	}
    
	/**
	 * 设置字典小数位数
	 * @param declen 字典小数位数
	 */
	public void setDeclen(int declen) {      
		setNumber("declen",declen);
	}    
	
	/**
	 * 返回标志
	 * @return 返回 标志,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getFlag() {       
		return getString("flag");
	}
    
	/**
	 * 设置标志
	 * @param flag 标志
	 */
	public void setFlag(String flag) {      
		set("flag",flag);
	}    
	
	/**
	 * 返回小写字典名称
	 * @return 返回 小写字典名称,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getLowcode() {       
		return getString("lowcode");
	}
    
	/**
	 * 设置小写字典名称
	 * @param lowcode 小写字典名称
	 */
	public void setLowcode(String lowcode) {      
		set("lowcode",lowcode);
	}    
}