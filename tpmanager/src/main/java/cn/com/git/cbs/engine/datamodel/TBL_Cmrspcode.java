/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.datamodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.com.git.cbs.model.PersistObject;

/**
 * Cmrspcode，pojo类
 */ 
public class TBL_Cmrspcode extends PersistObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4810716806621680823L;

	/**
	 * 返回响应代码
	 * @return 返回 响应代码,非空,长度需要在0和6之间
	 */
	@NotEmpty
	@Length(max=6)	
	public String getRspcode() {       
		return getString("rspcode");
	}
    
	/**
	 * 设置响应代码
	 * @param rspcode 响应代码
	 */
	public void setRspcode(String rspcode) {      
		set("rspcode",rspcode);
	}    
	
	/**
	 * 返回响应信息
	 * @return 返回 响应信息,长度需要在0和60之间
	 */
	@Length(max=60)	
	public String getMemo() {       
		return getString("memo");
	}
    
	/**
	 * 设置响应信息
	 * @param memo 响应信息
	 */
	public void setMemo(String memo) {      
		set("memo",memo);
	}    
	
	/**
	 * 返回对应标准响应代码
	 * @return 返回 对应标准响应代码,长度需要在0和6之间
	 */
	@Length(max=6)	
	public String getStdrspcode() {       
		return getString("stdrspcode");
	}
    
	/**
	 * 设置对应标准响应代码
	 * @param stdrspcode 对应标准响应代码
	 */
	public void setStdrspcode(String stdrspcode) {      
		set("stdrspcode",stdrspcode);
	}    
}