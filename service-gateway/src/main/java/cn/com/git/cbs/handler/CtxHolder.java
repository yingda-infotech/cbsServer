package cn.com.git.cbs.handler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import io.netty.channel.ChannelHandlerContext;

public class CtxHolder {
	public static ConcurrentHashMap<String, ChannelHandlerContext> ctxMap;
	public static AtomicInteger id;
	static {
		//TODO:ctxmap可能需要放入额外的信息
		ctxMap=new ConcurrentHashMap<>();
		id=new AtomicInteger();
	}
}
