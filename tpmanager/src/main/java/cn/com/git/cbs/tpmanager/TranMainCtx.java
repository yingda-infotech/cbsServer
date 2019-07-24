package cn.com.git.cbs.tpmanager;


/***
 * 基于ThreadLocal的交易上下文.
 * 用于交易过程中数据缓冲池和公共数据的获取与保持，通常只在平台内部使用
 * 
 * @author DengJia
 *
 */
public class TranMainCtx {
	/***
	 * 数据缓冲池对象
	 */
	private DataPool datapool;
	/***
	 * TreadLocal对象，用于存放上下文
	 */
	private final static ThreadLocal<TranMainCtx> _threadlocal = new ThreadLocal<TranMainCtx>();

	/***
	 * 默认构造器
	 */
	private TranMainCtx() {
		
	}

	/***
	 * 获取当前线程对应的上下文实例
	 * 
	 * @return 上下文实例
	 */
	private static TranMainCtx instance() {
		TranMainCtx ret = _threadlocal.get();
		if (ret == null) {
			ret = new TranMainCtx();
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
	 * 获取上下文中的数据缓冲池
	 * 
	 * @return 数据缓冲池
	 */
	public static DataPool getDataPool() {
		return instance().datapool;
	}
	
	/**
	 * 设置上下文中的数据缓冲池
	 * @param pool 数据缓冲池
	 */
	public static void setDataPool(DataPool pool) {
		instance().datapool=pool;
	}

}
