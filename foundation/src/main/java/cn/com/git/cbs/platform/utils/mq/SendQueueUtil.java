package cn.com.git.cbs.platform.utils.mq;

import java.io.Serializable;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.ExceptionUtils;
import cn.com.git.cbs.platform.utils.JSONUtils;
import cn.com.git.cbs.platform.utils.SpringContextUtils;

/**
 * 发送队列消息的类 
 * @author xia
 *
 */
public class SendQueueUtil {
		
	/**
	 * 发送json字符串类型消息（把DataObject转换成json后发送）
	 * @param queueName 配置文件中定义的队列的名称(如果queueName在配置文件中没有配置，那么mq会创建一个默认的队列来保存，不会报错)
	 * @param obj 发送的消息
	 * @param options 消息的参数
	 */
	public static void sendDataObjectByJson(String queueName,DataObject obj,Map<String,String> options){	   	
		String jsonString = JSONUtils.dataObject2String(obj);
//		JmsTemplate queueJmsTemplate = (JmsTemplate) SpringContextUtils.getBean(queueName);
		JmsTemplate queueJmsTemplate = (JmsTemplate) SpringContextUtils.getBean("queueJmsTemplate");
		
		if(null == queueJmsTemplate){
			throw ExceptionUtils.returnError("MQ0001",queueName);
		}
		
		queueJmsTemplate.send(queueName, new MessageCreator() {  
          public Message createMessage(Session session) throws JMSException {
        	  TextMessage textMessage =  session.createTextMessage(jsonString);  
        	  //设置消息的属性
        	  if(options!=null){
        		  for (String key : options.keySet()) {
        			  textMessage.setStringProperty(key, options.get(key));
				}
        	  }
        	  return textMessage;
          }           
        
      }); 
	}
	
	/**
	 * 使用java序列化方式发送一个Serializable对象
	 * @param queueName 配置文件中定义的队列的名称(如果queueName在配置文件中没有配置，那么mq会创建一个默认的队列来保存，不会报错)
	 * @param message 发送的对象
	 * @param options 消息的参数
	 */
	public static void sendSerializable(String queueName,Serializable message,Map<String,String> options){ 
//		JmsTemplate queueJmsTemplate = (JmsTemplate) SpringContextUtils.getBean(queueName);
		JmsTemplate queueJmsTemplate = (JmsTemplate) SpringContextUtils.getBean("queueJmsTemplate");
			
		if(null == queueJmsTemplate){
			throw ExceptionUtils.returnError("MQ0001",queueName);
		}
		
		queueJmsTemplate.send(queueName, new MessageCreator() {  
          public Message createMessage(Session session) throws JMSException {
        	  ObjectMessage objMessage =  session.createObjectMessage(message);
        	  //设置消息的属性
        	  if(options!=null){
        		  for (String key : options.keySet()) {
        			  objMessage.setStringProperty(key, options.get(key));
				}
        	  }
        	  return objMessage;
          }           
        
      }); 
	}
	
	/**
	 * 
	 * 系统暂时都是用TextMessage，效率更高
	 * 
	 * 发送对象消息
	 * @param obj DataObject对象
	 */
//	public static void sendQueueObj(DataObject obj){		
//		JmsTemplate queueJmsTemplate = (JmsTemplate) SpringContextUtils.getBean("queueJmsTemplate");
//		queueJmsTemplate.send(new MessageCreator() {  
//          public Message createMessage(Session session) throws JMSException {
//        	  return session.createObjectMessage((Serializable)obj);  
//          }  
//      }); 
//	}
	
}
