/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.expression;

import java.util.Map;

import org.apache.commons.lang3.text.StrLookup;

/**
 * 表达式取值时忽略key 大小写的类
 * map中所有的key都要小写
 * @author xia
 *
 *  
 */
public class CaseInsensitiveStrLookup extends StrLookup<Object> {

private final Map<String, Object> map;   

/**
 * 表达式取值时忽略key 大小写
 * @param map
 */
CaseInsensitiveStrLookup(final Map<String, Object> map) {
    this.map = map;
}

/**
 * 表达式取值时忽略key 大小写,返回表达式的值
 */
@Override
public String lookup(final String key) {
    String lowercaseKey = key.toLowerCase(); //lowercase the key you're looking for
    if (map == null) {
        return null;
    }
    final Object obj = map.get(lowercaseKey);
    if (obj == null) {
   	 return "''";
    }
    
    //返回值自动加空格
    StringBuffer sb = new StringBuffer();
    sb.append("'");
    sb.append(obj.toString());
    sb.append("'");
    return sb.toString();
}
}
