package cn.com.git.cbs.service;

import java.util.List;

import cn.com.git.cbs.entity.MenuInfo;


public interface MenuInfoService {
	/**
	 * 查询所有菜单
	 * @return
	 * @throws ServiceException
	 */
	List<MenuInfo> getMainMenuOrder();
	
	
	/**
	 * 根据权限去查询菜单
	 * @param pid
	 * @param roleid
	 * @return
	 */
	List<MenuInfo> getMenuTree(String pid,String roleid);
	
	
	/**
	 * 根据pid去查询菜单
	 * @param pid
	 * @return
	 */
	List<MenuInfo> findMenuByPid(String pid);
}
