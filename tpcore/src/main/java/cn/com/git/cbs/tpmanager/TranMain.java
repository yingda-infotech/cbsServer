package cn.com.git.cbs.tpmanager;

import cn.com.git.cbs.model.DataObject;

public interface TranMain {

	DataObject execute(DataObject inParam);

	void executeBatch(DataObject inParam);

}