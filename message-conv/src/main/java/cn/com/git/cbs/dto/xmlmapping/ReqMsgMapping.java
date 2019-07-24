/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/

package cn.com.git.cbs.dto.xmlmapping;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "element",
    "list"
})
@XmlRootElement(name = "inMessage")

/**
 * 请求报文映射类
 * @author DengJia
 *
 */
public class ReqMsgMapping {

    @XmlElement(required = true)
    protected java.util.List<ReqFldMapping> element;
    protected java.util.List<cn.com.git.cbs.dto.xmlmapping.ReqListFldMapping> list;

    /**
     * 获取该节点下的element节点列表
     * @return 该节点下的element节点列表
     */
    public java.util.List<ReqFldMapping> getElement() {
        if (element == null) {
            element = new ArrayList<ReqFldMapping>();
        }
        return this.element;
    }
    /**
     * 获取该节点下的list节点列表
     * @return 该节点下的list节点列表
     */
    public java.util.List<cn.com.git.cbs.dto.xmlmapping.ReqListFldMapping> getList() {
        if (list == null) {
            list = new ArrayList<cn.com.git.cbs.dto.xmlmapping.ReqListFldMapping>();
        }
        return this.list;
    }

}
