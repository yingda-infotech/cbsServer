package cn.com.git.cbs.entity;

public class MenuInfo {

	private String menuId;
	private String pmenuId;
	private String menuName;
	private String mainSn;
	private String subSn;
	private String leaf;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getPmenuId() {
		return pmenuId;
	}
	public void setPmenuId(String pmenuId) {
		this.pmenuId = pmenuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMainSn() {
		return mainSn;
	}
	public void setMainSn(String mainSn) {
		this.mainSn = mainSn;
	}
	public String getSubSn() {
		return subSn;
	}
	public void setSubSn(String subSn) {
		this.subSn = subSn;
	}
	public String getLeaf() {
		return leaf;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private String state;
	private String url;

}
