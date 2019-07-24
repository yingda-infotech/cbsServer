/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.datamodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.com.git.cbs.model.PersistObject;

/**
 * Txoutput，pojo类
 */ 
public class TBL_Txoutput extends PersistObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6676241633908912305L;

	/**
	 * 返回变量表达式
	 * @return 返回 变量表达式,非空,长度需要在0和254之间
	 */
	@NotEmpty
	@Length(max=254)	
	public String getPoolname() {       
		return getString("poolname");
	}
    
	/**
	 * 设置变量表达式
	 * @param poolname 变量表达式
	 */
	public void setPoolname(String poolname) {      
		set("poolname",poolname);
	}    
	
	/**
	 * 返回交易代码
	 * @return 返回 交易代码,非空,长度需要在0和6之间
	 */
	@NotEmpty
	@Length(max=6)	
	public String getMuid() {       
		return getString("muid");
	}
    
	/**
	 * 设置交易代码
	 * @param muid 交易代码
	 */
	public void setMuid(String muid) {      
		set("muid",muid);
	}    
	
	/**
	 * 返回流程单元号
	 * @return 返回 流程单元号,长度需要在0和6之间
	 */
	@Length(max=6)	
	public String getPuid() {       
		return getString("puid");
	}
    
	/**
	 * 设置流程单元号
	 * @param puid 流程单元号
	 */
	public void setPuid(String puid) {      
		set("puid",puid);
	}    
	
	/**
	 * 返回输出选项
	 * @return 返回 输出选项,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getOuttype() {       
		return getString("outtype");
	}
    
	/**
	 * 设置输出选项
	 * @param outtype 输出选项
	 */
	public void setOuttype(String outtype) {      
		set("outtype",outtype);
	}    
	
	/**
	 * 返回输出变量名称
	 * @return 返回 输出变量名称,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getDatadicname() {       
		return getString("datadicname");
	}
    
	/**
	 * 设置输出变量名称
	 * @param datadicname 输出变量名称
	 */
	public void setDatadicname(String datadicname) {      
		set("datadicname",datadicname);
	}    
}