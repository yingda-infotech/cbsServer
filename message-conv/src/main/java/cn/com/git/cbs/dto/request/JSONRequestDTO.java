/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import cn.com.git.cbs.dto.xmlmapping.ReqFldMapping;
import cn.com.git.cbs.dto.xmlmapping.ReqListFldMapping;
import cn.com.git.cbs.dto.xmlmapping.ReqMsgMapping;
import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * json请求数据转换对象
 * @author DengJia
 *
 */
public class JSONRequestDTO extends XMLConfigBasedRequestDTO {
	
	/**
	 * 构造器
	 * @param mappingFilePath 映射文件路径
	 */
	public JSONRequestDTO(String mappingFilePath) {
		super(mappingFilePath);
		// TODO Auto-generated constructor stub
	}	

	/**
	 *解码，json 字符串解析为 DataObject
	 *@param requestMsg JSON字符串
	 *@return DataObject对象
	 */
	public DataObject decode(String requestMsg) {
		DataObject ret = new DataObject();
		JSONObject jsonObj = JSON.parseObject(requestMsg);
		ret.importObject(parseJSON(jsonObj, txnMapping));
		return ret;
	}

	/**
	 * 根据xPath取出节点值
	 * @param jsonNode json节点的对象
	 * @return json节点的值
	 */
	private String jsonNode2Text(Object jsonNode) {
		String ret = null;
		if (jsonNode != null) {
			if (jsonNode instanceof JSONArray) {
				ret = ((JSONArray) jsonNode).get(0).toString();
			} else {
				ret = jsonNode.toString();
			}
		}
		return ret;
	}

	/**
	 * 解析json对象为DataObject
	 * @param jsonObj 传入的json对象
	 * @param msgMapping 请求报文映射类
	 * @return DataObject对象
	 */
	private DataObject parseJSON(JSONObject jsonObj, ReqMsgMapping msgMapping) {
		DataObject ret = new DataObject();
		try {
			JSONObject currentObj = jsonObj;
			// 取到msgMapping对象后
			for (ReqFldMapping ele : msgMapping.getElement()) {
				// 根据xPath取出节点值
				Object o = JSONPath.eval(currentObj, ele.getXpath());
				String value=jsonNode2Text(o);
				if (value!=null) {
					ret.set(ele.getName().trim().toLowerCase(), value);
				}
			}
			for (ReqListFldMapping lst : msgMapping.getList()) {
				ret.setList(lst.getName().trim().toLowerCase(),
						genList(lst, currentObj));
			}
		} catch (Exception e) {
			throw ExceptionUtils.returnError("PL1002", e, e.getMessage());
		}
		return ret;
	}

	/**
	 * 解析json对象为DataObject
	 * @param jsonObj 传入的JSON对象
	 * @param lstFlds Field映射列表
	 * @return DataObject对象
	 */
	private DataObject json2DataObject(Object jsonObj,
			List<ReqFldMapping> lstFlds) {
		DataObject dataObj = new DataObject();
		for (ReqFldMapping ele : lstFlds) {
			Object o = JSONPath.eval(jsonObj, ele.getXpath());
			String value=jsonNode2Text(o);
			if (value!=null) {
				dataObj.set(ele.getName().trim().toLowerCase(), value);
			}
		}
		return dataObj;
	}

	/**
	 * 获取模板中的list节点信息，只支持List节点下为element的情况，不支持递归
	 * @param lstCfg List节点映射信息
	 * @param currentNode 当前JSON对象节点
	 * @return DataObject列表
	 * @throws Exception 解析错误
	 */
	private java.util.List<DataObject> genList(ReqListFldMapping lstCfg,
			JSONObject currentNode) throws Exception {
		java.util.List<DataObject> retList = new ArrayList<DataObject>();
		// 根据xPath取出节点值
		Object eleNodes = JSONPath.eval(currentNode, lstCfg.getXpath());
		if (eleNodes instanceof JSONArray) {
			for (Object lstNode : ((JSONArray) eleNodes)) {
				retList.add(json2DataObject(lstNode, lstCfg.getElement()));
			}
		} else if(eleNodes!=null) {
			retList.add(json2DataObject(eleNodes, lstCfg.getElement()));
		}
		return retList;
	}
}
