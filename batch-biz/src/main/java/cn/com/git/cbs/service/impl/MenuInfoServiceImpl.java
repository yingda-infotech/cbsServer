package cn.com.git.cbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cn.com.git.cbs.dao.MenuInfoDao;
import cn.com.git.cbs.entity.MenuInfo;
import cn.com.git.cbs.service.MenuInfoService;

@Service
@Primary
public class MenuInfoServiceImpl implements MenuInfoService {
	

	@Resource
	private MenuInfoDao menuDao;

	@Override
	public List<MenuInfo> getMainMenuOrder() {
		return menuDao.getMainMenuOrder();
	}

	@Override
	public List<MenuInfo> getMenuTree(String pid, String roleid) {
		return menuDao.getMenuTree(pid,roleid);
	}

	@Override
	public List<MenuInfo> findMenuByPid(String pid) {
		return menuDao.findMenuByPid(pid);
	}

}
