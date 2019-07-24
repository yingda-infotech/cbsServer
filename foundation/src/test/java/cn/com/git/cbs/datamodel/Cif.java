package cn.com.git.cbs.datamodel;

import java.math.BigDecimal;
import cn.com.git.cbs.model.PersistObject;

public class Cif extends PersistObject {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5626440816263660851L;

	public BigDecimal getId() {
        return (BigDecimal) get("id");
    }
   
    public void setId(BigDecimal id) {    	
    	set("id",id);
    }

    public String getOprtime() {
        return getString("oprtime");
    }

    public void setOprtime(String oprtime) {
    	set("oprtime",oprtime);
    }

    public String getCdlx() {
    	return getString("cdlx");
    }

    public void setCdlx(String cdlx) {
    	set("cdlx",cdlx);
    }
}