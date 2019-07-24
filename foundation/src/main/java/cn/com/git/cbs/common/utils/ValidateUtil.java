/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.common.utils;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import cn.com.git.cbs.model.PersistObject;
import cn.com.git.cbs.model.ReturnObj;

/**
 * 执行sql执行，对对象进行检查
 * 
 * @author xia
 *
 */

public class ValidateUtil {
	private static ValidatorFactory factory;
	private static Validator validator;
	static {
		// 测试JSR-303进行校验
		factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	/**
	 * 插入和修改之前，对对象进行校验
	 * 
	 * @param t
	 *            需要校验的对象
	 * @return ReturnObj 校验结果
	 */
	public static ReturnObj<Set<ConstraintViolation<PersistObject>>> validateObject(PersistObject t) {
		ReturnObj<Set<ConstraintViolation<PersistObject>>> returnObj = new ReturnObj<Set<ConstraintViolation<PersistObject>>>();

		Set<ConstraintViolation<PersistObject>> violations = validator.validate(t);

		if (violations.size() == 0) {
			returnObj.setRetValue(ReturnObj.SUCCESSFUL);
		} else {
			returnObj.setRetValue(ReturnObj.FAILURE);
		}

		returnObj.setData(violations);

		return returnObj;

	}

	/**
	 * 把pojo校验结果集解析成字符串返回
	 * 
	 * @param violations
	 *            pojo校验的结果集
	 * @return 校验结果的字符串，多个信息中间用##拼接
	 */
	public static String generateErrorMsg(Set<ConstraintViolation<PersistObject>> violations) {
		StringBuffer sb = new StringBuffer();
		sb.append("##");
		for (ConstraintViolation<PersistObject> cv : violations) {
			sb.append(cv.getPropertyPath().toString());
			sb.append(cv.getMessage());
			sb.append("##");
		}
		return sb.toString();
	}

}
