package cn.com.git.cbs.batch.context;

import cn.com.git.cbs.model.DataObject;

/***
 * 基于ThreadLocal的批处理上下文. 用于批处理过程中数据上下文的获取与保持
 * 
 * @author DengJia
 *
 */
public class BatchDataHolder {
	/***
	 * 数据缓冲池对象
	 */
	private DataObject data;
	/***
	 * TreadLocal对象，用于存放上下文
	 */
	private final static ThreadLocal<BatchDataHolder> _threadlocal = new ThreadLocal<BatchDataHolder>();

	/***
	 * 默认构造器
	 */
	private BatchDataHolder() {

	}

	/***
	 * 获取当前线程对应的上下文实例
	 * 
	 * @return 上下文实例
	 */
	private static BatchDataHolder instance() {
		BatchDataHolder ret = _threadlocal.get();
		if (ret == null) {
			ret = new BatchDataHolder();
			_threadlocal.set(ret);
		}
		return ret;
	}

	/***
	 * 清理上下文，在线程结束前必须调用，以避免内存泄露
	 */
	public static void remove() {
		_threadlocal.remove();
	}

	/***
	 * 获取上下文中的数据对象
	 * 
	 * @return 数据对象
	 */
	public static DataObject getData() {
		return instance().data;
	}

	/**
	 * 设置上下文中的数据对象
	 * 
	 * @param data
	 *            数据对象
	 */
	public static void setDataObject(DataObject data) {
		instance().data = data;
	}

}
