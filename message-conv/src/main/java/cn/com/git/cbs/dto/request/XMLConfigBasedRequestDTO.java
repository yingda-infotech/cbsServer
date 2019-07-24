/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.dto.request;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.Resource;
import org.w3c.dom.Document;

import cn.com.git.cbs.dto.xmlmapping.ReqMsgMapping;
import cn.com.git.cbs.platform.utils.ExceptionUtils;
import cn.com.git.cbs.platform.utils.SpringContextUtils;

/**
 * xml配置 数据转换对象
 * @author DengJia
 *
 */
public abstract class XMLConfigBasedRequestDTO implements RequestDTO {
	//请求xml映射
	protected ReqMsgMapping txnMapping;

	XMLConfigBasedRequestDTO(String mappingFilePath) {
		this.txnMapping = getMapping(mappingFilePath);
	}

	/**
	 * 根据传入的xml请求路径解析请求报文映射
	 * @param mappingFilePath xml请求的路径
	 * @return ReqMsgMapping 请求报文映射类
	 */
	protected ReqMsgMapping getMapping(String mappingFilePath) {
		try {
			JAXBContext jc = JAXBContext.newInstance(ReqMsgMapping.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Resource r = SpringContextUtils.getResource(mappingFilePath);
			// 读取mappingFile
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(r.getInputStream());
			ReqMsgMapping msgMapping = (ReqMsgMapping) unmarshaller
					.unmarshal(doc.getDocumentElement());

			return msgMapping;
		} catch (Exception e) {
			throw ExceptionUtils.returnError("PL1001", e, mappingFilePath);
		}
	}
}
