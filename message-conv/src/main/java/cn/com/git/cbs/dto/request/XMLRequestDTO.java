/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.dto.request;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;

import cn.com.git.cbs.dto.xmlmapping.ReqFldMapping;
import cn.com.git.cbs.dto.xmlmapping.ReqListFldMapping;
import cn.com.git.cbs.dto.xmlmapping.ReqMsgMapping;
import cn.com.git.cbs.exception.CbsRunTimeException;
import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * xml请求数据转换对象
 * @author DengJia
 *
 */
public class XMLRequestDTO extends XMLConfigBasedRequestDTO {
	/**
	 * 构造器
	 * @param mappingFilePath 映射文件路径
	 */
	public XMLRequestDTO(String mappingFilePath) {
		super(mappingFilePath);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see cn.com.git.cbs.engine.dto.xml.IRequestDTO#decode(java.lang.String)
	 */
	/**
	 *解码，XML字符串解析为 DataObject
	 *@param requestMsg XML字符串
	 *@return DataObject对象
	 */
	@Override
	public DataObject decode(String requestMsg) {
		DataObject ret = new DataObject();
		org.dom4j.Document msgDoc = null;
		try {
			msgDoc = DocumentHelper.parseText(requestMsg);
		} catch (DocumentException e) {
			throw ExceptionUtils.returnError("PL1002", e, e.getMessage());
		}
		ret.importObject(parseXML(msgDoc, txnMapping));
		return ret;
	}

	/**
	 * 解析xml 
	 * @param msgDoc Document对象
	 * @param msgMapping  请求报文映射类
	 * @return DataObject对象
	 * @exception CbsRunTimeException xml解析错误
	 */
	@SuppressWarnings("unchecked")
	private DataObject parseXML(org.dom4j.Document msgDoc,
			ReqMsgMapping msgMapping) {
		DataObject ret = new DataObject();
		try {
			org.dom4j.Node currentNode = msgDoc.getRootElement();
			// 取到msgMapping对象后
			for (ReqFldMapping ele : msgMapping.getElement()) {
				// 根据xPath取出节点值
				List<org.dom4j.Node> eleNodes = currentNode.selectNodes(ele
						.getXpath());
				if (eleNodes.size() >= 1) {
					String val = eleNodes.get(0).getText();
					if (val != null && val.trim().length() != 0) {
						ret.set(ele.getName().trim().toLowerCase(), val);
					}
				}
			}

			for (ReqListFldMapping lst : msgMapping.getList()) {
				ret.setList(lst.getName().trim().toLowerCase(),
						genList(lst, currentNode));
			}
		} catch (Exception e) {
			throw ExceptionUtils.returnError("PL1002", e, e.getMessage());
		}
		return ret;
	}

	/**
	 * 获取模板中的list节点信息，只支持List节点下为element的情况，不支持递归
	 * @param lstCfg List节点映射信息
	 * @param currentNode 当前XML节点
	 * @return DataObject列表
	 * @throws Exception XML解析出错
	 */
	@SuppressWarnings("unchecked")
	private java.util.List<DataObject> genList(ReqListFldMapping lstCfg,
			org.dom4j.Node currentNode) throws Exception {
		java.util.List<DataObject> retList = new ArrayList<DataObject>();
		// 根据xPath取出节点值
		java.util.List<org.dom4j.Node> eleNodes = currentNode
				.selectNodes(lstCfg.getXpath());
		for (Node lstNode : eleNodes) {
			DataObject obj = new DataObject();
			for (ReqFldMapping ele : lstCfg.getElement()) {
				// 根据xPath取出节点值
				java.util.List<org.dom4j.Node> elNodes = lstNode
						.selectNodes(ele.getXpath());
				if (elNodes.size() >= 1) {
					String val = elNodes.get(0).getText();
					if (val != null && val.trim().length() != 0) {
						obj.set(ele.getName().trim().toLowerCase(), val);
					}
				}
			}
			retList.add(obj);
		}
		return retList;
	}
}
