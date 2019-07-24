/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.onlinebatch;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.JSONUtils;
import cn.com.git.cbs.tpmanager.TranMain;

/**
 * @author DengJia
 *日间批的监听，获取到json的object之后启动tranMain流程
 */
@Component("onlinebatchConsumer")
public class BatchCommandReciver implements MessageListener {
	
	@Autowired
	TranMain tranMain;
	
	private static final PlatformLogger LOGGER = PlatformLogger.create();
	
	public void onMessage(Message message) {
		if(message instanceof TextMessage){
        	TextMessage msg = (TextMessage) message;  
         	try {         		
         		String jsonString = msg.getText();    
         		DataObject data = JSONUtils.string2DataObject(jsonString);
         		LOGGER.debug(data.toString());  
         		tranMain.executeBatch(data);
 			} catch (JMSException e1) {
 				LOGGER.error(e1);
 			}
    	}else if(message instanceof ObjectMessage){
    		 ObjectMessage objMsg = (ObjectMessage) message; 
    		 DataObject data;
			try {
				data = (DataObject) objMsg.getObject();
				LOGGER.debug(data.toString());  
				tranMain.executeBatch(data);
			} catch (JMSException e) {
				LOGGER.error(e);
			}  
    	}           
		
	}
	
}