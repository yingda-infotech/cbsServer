/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.datamodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.com.git.cbs.model.PersistObject;

/**
 * Maintxdef，pojo类
 */ 
public class TBL_Maintxdef extends PersistObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4087837179747529122L;

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
	 * 返回交易名称
	 * @return 返回 交易名称,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getMuname() {       
		return getString("muname");
	}
    
	/**
	 * 设置交易名称
	 * @param muname 交易名称
	 */
	public void setMuname(String muname) {      
		set("muname",muname);
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
	 * 返回交易类型
	 * @return 返回 交易类型,长度需要在0和2之间
	 */
	@Length(max=2)	
	public String getTrantype() {       
		return getString("trantype");
	}
    
	/**
	 * 设置交易类型
	 * @param trantype 交易类型
	 */
	public void setTrantype(String trantype) {      
		set("trantype",trantype);
	}    
	
	/**
	 * 返回交易模式
	 * @return 返回 交易模式,长度需要在0和2之间
	 */
	@Length(max=2)	
	public String getTranmode() {       
		return getString("tranmode");
	}
    
	/**
	 * 设置交易模式
	 * @param tranmode 交易模式
	 */
	public void setTranmode(String tranmode) {      
		set("tranmode",tranmode);
	}    
	
	/**
	 * 返回可应用的系统状态
	 * @return 返回 可应用的系统状态,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getSysmode() {       
		return getString("sysmode");
	}
    
	/**
	 * 设置可应用的系统状态
	 * @param sysmode 可应用的系统状态
	 */
	public void setSysmode(String sysmode) {      
		set("sysmode",sysmode);
	}    
	
	/**
	 * 返回通讯方式
	 * @return 返回 通讯方式,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getCommmode() {       
		return getString("commmode");
	}
    
	/**
	 * 设置通讯方式
	 * @param commmode 通讯方式
	 */
	public void setCommmode(String commmode) {      
		set("commmode",commmode);
	}    
	
	/**
	 * 返回报文方式
	 * @return 返回 报文方式,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getMsgmode() {       
		return getString("msgmode");
	}
    
	/**
	 * 设置报文方式
	 * @param msgmode 报文方式
	 */
	public void setMsgmode(String msgmode) {      
		set("msgmode",msgmode);
	}    
	
	/**
	 * 返回MAC检查
	 * @return 返回 MAC检查,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getMacchk() {       
		return getString("macchk");
	}
    
	/**
	 * 设置MAC检查
	 * @param macchk MAC检查
	 */
	public void setMacchk(String macchk) {      
		set("macchk",macchk);
	}    
	
	/**
	 * 返回柜员工作状态
	 * @return 返回 柜员工作状态,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getTellerchk() {       
		return getString("tellerchk");
	}
    
	/**
	 * 设置柜员工作状态
	 * @param tellerchk 柜员工作状态
	 */
	public void setTellerchk(String tellerchk) {      
		set("tellerchk",tellerchk);
	}    
	
	/**
	 * 返回机构工作状态
	 * @return 返回 机构工作状态,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getBranchchk() {       
		return getString("branchchk");
	}
    
	/**
	 * 设置机构工作状态
	 * @param branchchk 机构工作状态
	 */
	public void setBranchchk(String branchchk) {      
		set("branchchk",branchchk);
	}    
	
	/**
	 * 返回取主机流水号
	 * @return 返回 取主机流水号,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getRsvflag1() {       
		return getString("rsvflag1");
	}
    
	/**
	 * 设置取主机流水号
	 * @param rsvflag1 取主机流水号
	 */
	public void setRsvflag1(String rsvflag1) {      
		set("rsvflag1",rsvflag1);
	}    
	
	/**
	 * 返回柜员权限检查标志
	 * @return 返回 柜员权限检查标志,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getRsvflag2() {       
		return getString("rsvflag2");
	}
    
	/**
	 * 设置柜员权限检查标志
	 * @param rsvflag2 柜员权限检查标志
	 */
	public void setRsvflag2(String rsvflag2) {      
		set("rsvflag2",rsvflag2);
	}    
	
	/**
	 * 返回流水平衡检查标志
	 * @return 返回 流水平衡检查标志,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getRsvflag3() {       
		return getString("rsvflag3");
	}
    
	/**
	 * 设置流水平衡检查标志
	 * @param rsvflag3 流水平衡检查标志
	 */
	public void setRsvflag3(String rsvflag3) {      
		set("rsvflag3",rsvflag3);
	}    
	
	/**
	 * 返回复核标志
	 * @return 返回 复核标志,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getRsvflag4() {       
		return getString("rsvflag4");
	}
    
	/**
	 * 设置复核标志
	 * @param rsvflag4 复核标志
	 */
	public void setRsvflag4(String rsvflag4) {      
		set("rsvflag4",rsvflag4);
	}    
	
	/**
	 * 返回冲正标志
	 * @return 返回 冲正标志,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getRsvflag5() {       
		return getString("rsvflag5");
	}
    
	/**
	 * 设置冲正标志
	 * @param rsvflag5 冲正标志
	 */
	public void setRsvflag5(String rsvflag5) {      
		set("rsvflag5",rsvflag5);
	}    
	
	/**
	 * 返回允许柜面冲正
	 * @return 返回 允许柜面冲正,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getRsvflag6() {       
		return getString("rsvflag6");
	}
    
	/**
	 * 设置允许柜面冲正
	 * @param rsvflag6 允许柜面冲正
	 */
	public void setRsvflag6(String rsvflag6) {      
		set("rsvflag6",rsvflag6);
	}    
	
	/**
	 * 返回保留字段
	 * @return 返回 保留字段,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getRsvitem1() {       
		return getString("rsvitem1");
	}
    
	/**
	 * 设置保留字段
	 * @param rsvitem1 保留字段
	 */
	public void setRsvitem1(String rsvitem1) {      
		set("rsvitem1",rsvitem1);
	}    
	
	/**
	 * 返回保留字段2
	 * @return 返回 保留字段2,长度需要在0和32之间
	 */
	@Length(max=32)	
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
	
	/**
	 * 返回保留字段3
	 * @return 返回 保留字段3,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getRsvitem3() {       
		return getString("rsvitem3");
	}
    
	/**
	 * 设置保留字段3
	 * @param rsvitem3 保留字段3
	 */
	public void setRsvitem3(String rsvitem3) {      
		set("rsvitem3",rsvitem3);
	}    
	
	/**
	 * 返回保留字段4
	 * @return 返回 保留字段4,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getRsvitem4() {       
		return getString("rsvitem4");
	}
    
	/**
	 * 设置保留字段4
	 * @param rsvitem4 保留字段4
	 */
	public void setRsvitem4(String rsvitem4) {      
		set("rsvitem4",rsvitem4);
	}    
	
	/**
	 * 返回保留字段5
	 * @return 返回 保留字段5,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getRsvitem5() {       
		return getString("rsvitem5");
	}
    
	/**
	 * 设置保留字段5
	 * @param rsvitem5 保留字段5
	 */
	public void setRsvitem5(String rsvitem5) {      
		set("rsvitem5",rsvitem5);
	}    
	
	/**
	 * 返回保留字段6
	 * @return 返回 保留字段6,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getRsvitem6() {       
		return getString("rsvitem6");
	}
    
	/**
	 * 设置保留字段6
	 * @param rsvitem6 保留字段6
	 */
	public void setRsvitem6(String rsvitem6) {      
		set("rsvitem6",rsvitem6);
	}    
}