/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.datamodel;

import org.hibernate.validator.constraints.Length;

import cn.com.git.cbs.model.PersistObject;

/**
 * Subinput，pojo类
 */ 
public class TBL_Subinput extends PersistObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -379564943095463944L;

	/**
	 * 返回子交易输入字段名称
	 * @return 返回 子交易输入字段名称,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getLocalname() {       
		return getString("localname");
	}
    
	/**
	 * 设置子交易输入字段名称
	 * @param localname 子交易输入字段名称
	 */
	public void setLocalname(String localname) {      
		set("localname",localname);
	}    
	
	/**
	 * 返回交易代码
	 * @return 返回 交易代码,长度需要在0和6之间
	 */
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
	 * 返回子交易标识
	 * @return 返回 子交易标识,长度需要在0和2之间
	 */
	@Length(max=2)	
	public String getWumark() {       
		return getString("wumark");
	}
    
	/**
	 * 设置子交易标识
	 * @param wumark 子交易标识
	 */
	public void setWumark(String wumark) {      
		set("wumark",wumark);
	}    
	
	/**
	 * 返回映射模式
	 * @return 返回 映射模式,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getMapmode() {       
		return getString("mapmode");
	}
    
	/**
	 * 设置映射模式
	 * @param mapmode 映射模式
	 */
	public void setMapmode(String mapmode) {      
		set("mapmode",mapmode);
	}    
	
	/**
	 * 返回数据区域
	 * @return 返回 数据区域,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getDataarea() {       
		return getString("dataarea");
	}
    
	/**
	 * 设置数据区域
	 * @param dataarea 数据区域
	 */
	public void setDataarea(String dataarea) {      
		set("dataarea",dataarea);
	}    
	
	/**
	 * 返回数据池变量名称
	 * @return 返回 数据池变量名称,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getPoolname() {       
		return getString("poolname");
	}
    
	/**
	 * 设置数据池变量名称
	 * @param poolname 数据池变量名称
	 */
	public void setPoolname(String poolname) {      
		set("poolname",poolname);
	}    
	
	/**
	 * 返回对应子交易标识
	 * @return 返回 对应子交易标识,长度需要在0和2之间
	 */
	@Length(max=2)	
	public String getPoolwumark() {       
		return getString("poolwumark");
	}
    
	/**
	 * 设置对应子交易标识
	 * @param poolwumark 对应子交易标识
	 */
	public void setPoolwumark(String poolwumark) {      
		set("poolwumark",poolwumark);
	}    
	
	/**
	 * 返回常量值
	 * @return 返回 常量值,长度需要在0和254之间
	 */
	@Length(max=254)	
	public String getFixvalue() {       
		return getString("fixvalue");
	}
    
	/**
	 * 设置常量值
	 * @param fixvalue 常量值
	 */
	public void setFixvalue(String fixvalue) {      
		set("fixvalue",fixvalue);
	}    
	
	/**
	 * 返回数据字典名称
	 * @return 返回 数据字典名称,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getDatadicname() {       
		return getString("datadicname");
	}
    
	/**
	 * 设置数据字典名称
	 * @param datadicname 数据字典名称
	 */
	public void setDatadicname(String datadicname) {      
		set("datadicname",datadicname);
	}    
}