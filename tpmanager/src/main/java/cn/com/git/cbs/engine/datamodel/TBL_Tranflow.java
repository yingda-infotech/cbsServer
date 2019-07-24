/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.engine.datamodel;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import cn.com.git.cbs.model.PersistObject;

/**
 * Tranflow，pojo类
 */ 
public class TBL_Tranflow extends PersistObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5337375896134163090L;

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
	 * 返回顺序编号
	 * @return 返回 顺序编号
	 */	
	public int getWorkseq() {       
		return (int) getNumber("workseq");
	}
    
	/**
	 * 设置顺序编号
	 * @param workseq 顺序编号
	 */
	public void setWorkseq(int workseq) {      
		setNumber("workseq",workseq);
	}    
	
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
	 * 返回子交易运行条件
	 * @return 返回 子交易运行条件,长度需要在0和254之间
	 */
	@Length(max=254)	
	public String getEntrycond() {       
		return getString("entrycond");
	}
    
	/**
	 * 设置子交易运行条件
	 * @param entrycond 子交易运行条件
	 */
	public void setEntrycond(String entrycond) {      
		set("entrycond",entrycond);
	}    
	
	/**
	 * 返回启动条件函数
	 * @return 返回 启动条件函数,长度需要在0和32之间
	 */
	@Length(max=32)	
	public String getEntryfunc() {       
		return getString("entryfunc");
	}
    
	/**
	 * 设置启动条件函数
	 * @param entryfunc 启动条件函数
	 */
	public void setEntryfunc(String entryfunc) {      
		set("entryfunc",entryfunc);
	}    
	
	/**
	 * 返回控制/调用方式
	 * @return 返回 控制/调用方式,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getCtrlmode() {       
		return getString("ctrlmode");
	}
    
	/**
	 * 设置控制/调用方式
	 * @param ctrlmode 控制/调用方式
	 */
	public void setCtrlmode(String ctrlmode) {      
		set("ctrlmode",ctrlmode);
	}    
	
	/**
	 * 返回提交模式
	 * @return 返回 提交模式,长度需要在0和1之间
	 */
	@Length(max=1)	
	public String getCommitmode() {       
		return getString("commitmode");
	}
    
	/**
	 * 设置提交模式
	 * @param commitmode 提交模式
	 */
	public void setCommitmode(String commitmode) {      
		set("commitmode",commitmode);
	}    
	
	/**
	 * 返回流程模式
	 * @return 返回 流程模式,长度需要在0和8之间
	 */
	@Length(max=8)	
	public String getFlowmode() {       
		return getString("flowmode");
	}
    
	/**
	 * 设置流程模式
	 * @param flowmode 流程模式
	 */
	public void setFlowmode(String flowmode) {      
		set("flowmode",flowmode);
	}    
	
	/**
	 * 返回控制子交易标识
	 * @return 返回 控制子交易标识,长度需要在0和2之间
	 */
	@Length(max=2)	
	public String getPrewumark() {       
		return getString("prewumark");
	}
    
	/**
	 * 设置控制子交易标识
	 * @param prewumark 控制子交易标识
	 */
	public void setPrewumark(String prewumark) {      
		set("prewumark",prewumark);
	}    
	
	/**
	 * 返回保留字段1
	 * @return 返回 保留字段1,长度需要在0和10之间
	 */
	@Length(max=10)	
	public String getClearprdt() {       
		return getString("clearprdt");
	}
    
	/**
	 * 设置保留字段1
	 * @param clearprdt 保留字段1
	 */
	public void setClearprdt(String clearprdt) {      
		set("clearprdt",clearprdt);
	}    
	
	/**
	 * 返回收费种类
	 * @return 返回 收费种类,长度需要在0和10之间
	 */
	@Length(max=10)	
	public String getFeetype() {       
		return getString("feetype");
	}
    
	/**
	 * 设置收费种类
	 * @param feetype 收费种类
	 */
	public void setFeetype(String feetype) {      
		set("feetype",feetype);
	}    
	
	/**
	 * 返回分析代码
	 * @return 返回 分析代码,长度需要在0和10之间
	 */
	@Length(max=10)	
	public String getAnalysecode() {       
		return getString("analysecode");
	}
    
	/**
	 * 设置分析代码
	 * @param analysecode 分析代码
	 */
	public void setAnalysecode(String analysecode) {      
		set("analysecode",analysecode);
	}    
	
	/**
	 * 返回分类码1
	 * @return 返回 分类码1,长度需要在0和4之间
	 */
	@Length(max=4)	
	public String getClasscode1() {       
		return getString("classcode1");
	}
    
	/**
	 * 设置分类码1
	 * @param classcode1 分类码1
	 */
	public void setClasscode1(String classcode1) {      
		set("classcode1",classcode1);
	}    
	
	/**
	 * 返回分类码2
	 * @return 返回 分类码2,长度需要在0和4之间
	 */
	@Length(max=4)	
	public String getClasscode2() {       
		return getString("classcode2");
	}
    
	/**
	 * 设置分类码2
	 * @param classcode2 分类码2
	 */
	public void setClasscode2(String classcode2) {      
		set("classcode2",classcode2);
	}    
	
	/**
	 * 返回分类码3
	 * @return 返回 分类码3,长度需要在0和4之间
	 */
	@Length(max=4)	
	public String getClasscode3() {       
		return getString("classcode3");
	}
    
	/**
	 * 设置分类码3
	 * @param classcode3 分类码3
	 */
	public void setClasscode3(String classcode3) {      
		set("classcode3",classcode3);
	}    
	
	/**
	 * 返回分类码4
	 * @return 返回 分类码4,长度需要在0和4之间
	 */
	@Length(max=4)	
	public String getClasscode4() {       
		return getString("classcode4");
	}
    
	/**
	 * 设置分类码4
	 * @param classcode4 分类码4
	 */
	public void setClasscode4(String classcode4) {      
		set("classcode4",classcode4);
	}    
	
	/**
	 * 返回子交易类别
	 * @return 返回 子交易类别,长度需要在0和16之间
	 */
	@Length(max=16)	
	public String getParam1() {       
		return getString("param1");
	}
    
	/**
	 * 设置子交易类别
	 * @param param1 子交易类别
	 */
	public void setParam1(String param1) {      
		set("param1",param1);
	}    
	
	/**
	 * 返回保留参数2
	 * @return 返回 保留参数2,长度需要在0和16之间
	 */
	@Length(max=16)	
	public String getParam2() {       
		return getString("param2");
	}
    
	/**
	 * 设置保留参数2
	 * @param param2 保留参数2
	 */
	public void setParam2(String param2) {      
		set("param2",param2);
	}    
	
	/**
	 * 返回保留参数3
	 * @return 返回 保留参数3,长度需要在0和16之间
	 */
	@Length(max=16)	
	public String getParam3() {       
		return getString("param3");
	}
    
	/**
	 * 设置保留参数3
	 * @param param3 保留参数3
	 */
	public void setParam3(String param3) {      
		set("param3",param3);
	}    
	
	/**
	 * 返回说明
	 * @return 返回 说明,长度需要在0和40之间
	 */
	@Length(max=40)	
	public String getMemo() {       
		return getString("memo");
	}
    
	/**
	 * 设置说明
	 * @param memo 说明
	 */
	public void setMemo(String memo) {      
		set("memo",memo);
	}    
}