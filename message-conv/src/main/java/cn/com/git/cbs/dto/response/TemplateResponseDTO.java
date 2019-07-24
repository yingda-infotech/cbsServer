/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.dto.response;

import java.io.StringWriter;

import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.model.DataObject;
import cn.com.git.cbs.platform.utils.ExceptionUtils;
import cn.com.git.cbs.platform.utils.SpringContextUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 响应模板 数据转换对象
 * @author DengJia
 *
 */
public class TemplateResponseDTO implements ResponseDTO {
	private final static PlatformLogger LOGGER = PlatformLogger.create();
	private String templateFileName;
	private Template template;

	public TemplateResponseDTO(String templateFileName) {
		setTemplateFileName(templateFileName);
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
		Configuration cfg = SpringContextUtils.getBean(Configuration.class);
		try {
			LOGGER.debug("templateFileName=[%s]",templateFileName);
			this.template = cfg.getTemplate(templateFileName);
		} catch (Exception e) {
			throw ExceptionUtils.returnError("PL1003", e, templateFileName);
		}
	}

	/* (non-Javadoc)
	 * @see cn.com.git.cbs.engine.dto.xml.IResponseDTO#encode(cn.com.git.cbs.model.DataObject)
	 */
	@Override
	public String encode(DataObject obj) {
		if (this.template == null) {
			throw ExceptionUtils.returnError("PL1004", templateFileName);
		}
		try {
			
			StringWriter result = new StringWriter();
			template.process(obj.getData(), result);
			return result.toString();
		} catch (Exception e) {
			throw ExceptionUtils.returnError("PL1005", e, templateFileName,
					e.getMessage());
		}
	}

}
