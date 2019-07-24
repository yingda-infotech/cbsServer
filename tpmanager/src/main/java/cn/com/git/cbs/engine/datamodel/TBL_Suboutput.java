/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.datamodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.com.git.cbs.model.PersistObject;

/**
 * Suboutput，pojo类
 */ 
public class TBL_Suboutput extends PersistObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2297345178318242693L;

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
	 * 返回子交易标识
	 * @return 返回 子交易标识,非空,长度需要在0和3之间
	 */
	@NotEmpty
	@Length(max=3)	
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
	 * 返回数据池子交易标识
	 * @return 返回 数据池子交易标识,长度需要在0和3之间
	 */
	@Length(max=3)	
	public String getPoolwumark() {       
		return getString("poolwumark");
	}
    
	/**
	 * 设置数据池子交易标识
	 * @param poolwumark 数据池子交易标识
	 */
	public void setPoolwumark(String poolwumark) {      
		set("poolwumark",poolwumark);
	}    
	
	/**
	 * 返回数据池变量名称
	 * @return 返回 数据池变量名称,非空,长度需要在0和32之间
	 */
	@NotEmpty
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
	 * 返回映射模式
	 * @return 返回 映射模式,非空,长度需要在0和1之间
	 */
	@NotEmpty
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
	 * 返回子交易输出字段
	 * @return 返回 子交易输出字段,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getLocalname() {       
		return getString("localname");
	}
    
	/**
	 * 设置子交易输出字段
	 * @param localname 子交易输出字段
	 */
	public void setLocalname(String localname) {      
		set("localname",localname);
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