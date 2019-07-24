/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/

package cn.com.git.cbs.dto.xmlmapping;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "element"
})
@XmlRootElement(name = "list")

/**
 * 请求属性映射类，根据xml模板获取模板映射的属性（全部节点保存到list中）
 * @author DengJia
 *
 */
public class ReqListFldMapping {

    @XmlElement(required = true)
    protected java.util.List<ReqFldMapping> element;
    @XmlAttribute(name = "name", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String name;
    @XmlAttribute(name = "xpath", required = true)
    @XmlSchemaType(name = "anySimpleType")
    protected String xpath;
    
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
     * 获取name属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取xpath属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpath() {
        return xpath;
    }

    /**
     * 设置xpath属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpath(String value) {
        this.xpath = value;
    }

}
