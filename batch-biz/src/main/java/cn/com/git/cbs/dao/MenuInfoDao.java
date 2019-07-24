package cn.com.git.cbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.com.git.cbs.entity.MenuInfo;

@Mapper
public interface MenuInfoDao {
    
	List<MenuInfo> getMainMenuOrder();
	
	List<MenuInfo> getMenuTree(String pid,String roleid);
	
	List<MenuInfo> findMenuByPid(String pid);
    
}