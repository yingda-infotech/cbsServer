/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.dto;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;

import cn.com.git.cbs.common.utils.StringUtils;
import cn.com.git.cbs.dto.request.JSONRequestDTO;
import cn.com.git.cbs.dto.request.RequestDTO;
import cn.com.git.cbs.dto.request.XMLRequestDTO;
import cn.com.git.cbs.dto.response.ResponseDTO;
import cn.com.git.cbs.dto.response.TemplateResponseDTO;
import freemarker.template.Configuration;

/**
 * 消息 数据转换对象 工厂
 * 
 * @author DengJia
 *
 */
@Component
public class MessageDTOFactory {

	private final static String CACHE_NAME = "tx_def";
	@Value("${dto.response.template.path}")
	private String rspTemplatePath;
	@Value("${dto.request.template.path}")
	private String reqTemplatePath;
	@Value("${message.template.path}")
	private String templatePath;

	@Cacheable(value = CACHE_NAME, key = "'cn.com.git.cbs.dto.MessageDTOFactory.getEncoder#'+ #tranCode +'#' +#msgType")

	/**
	 * 编码，根据交易代码和类型，解析模板，
	 * 
	 * @param tranCode
	 *            交易代码
	 * @param msgType
	 *            消息类型
	 * @return 响应模板
	 * @throws UnsupportedMessageTypeException
	 */
	public ResponseDTO getEncoder(String tranCode, String msgType) throws UnsupportedMessageTypeException {
		if (msgType == null) {
			throw new UnsupportedMessageTypeException(msgType);
		}
		String templateFileName = null;
		switch (msgType.toLowerCase()) {
		case "xml":
			// 根据tranCode找到对应xml映射
			templateFileName = StringUtils.join(rspTemplatePath, "xml/PRS", tranCode, ".map");
			break;
		case "jsn":
			// 根据tranCode找到对应xml映射
			templateFileName = StringUtils.join(rspTemplatePath, "json/PRS", tranCode, ".map");
			break;
		default:
			throw new UnsupportedMessageTypeException(msgType);
		}
		return new TemplateResponseDTO(templateFileName);
	}

	/**
	 * 解码，根据交易代码和类型，解析模板；
	 * 
	 * @param tranCode
	 *            交易代码
	 * @param msgType
	 *            消息类型
	 * @return 请求模板
	 */
	@Cacheable(value = CACHE_NAME, key = "'cn.com.git.cbs.dto.MessageDTOFactory.getDecoder#'+#tranCode +'#' +#msgType")
	public RequestDTO getDecoder(String tranCode, String msgType) {
		if (msgType == null) {
			throw new UnsupportedMessageTypeException(msgType);
		}
		switch (msgType.toLowerCase()) {
		case "xml":
			// 根据tranCode找到对应xml映射
			String mappingFileName = StringUtils.join(templatePath, reqTemplatePath, "xml/URQ", tranCode, ".map");
			return new XMLRequestDTO(mappingFileName);
		case "jsn":
			// 根据tranCode找到对应xml映射
			String jsonMappingFileName = StringUtils.join(templatePath, reqTemplatePath, "json/URQ", tranCode, ".map");
			return new JSONRequestDTO(jsonMappingFileName);
		default:
			throw new UnsupportedMessageTypeException(msgType);
		}
	}

	public String getRspTemplatePath() {
		return rspTemplatePath;
	}

	public void setRspTemplatePath(String rspTemplatePath) {
		this.rspTemplatePath = rspTemplatePath;
	}

	public String getReqTemplatePath() {
		return reqTemplatePath;
	}

	public void setReqTemplatePath(String reqTemplatePath) {
		this.reqTemplatePath = reqTemplatePath;
	}

}

@SpringBootConfiguration
class MessageDTOConfig {
	@Value("${message.template.path}")
	private String templateLoaderPath;
	@Bean("freeMarkerConfigurationFactory")
	public FreeMarkerConfigurationFactory getFreeMarkerConfigurationFactory() {
		FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
		factory.setTemplateLoaderPath(templateLoaderPath);
		factory.setPreferFileSystemAccess(false);
		Properties settings = new Properties();
		settings.setProperty("template_update_delay", "0");
		settings.setProperty("default_encoding", "UTF-8");
		settings.setProperty("locale", "zh_CN");
		factory.setFreemarkerSettings(settings);

		return factory;
	}
		
	@Bean
	public Configuration getFreemarkerConfiguration(@Qualifier("freeMarkerConfigurationFactory") FreeMarkerConfigurationFactory factory) throws Exception {
		Configuration conf= factory.createConfiguration();
		conf.setClassicCompatible(true);
		return conf;
	}
}
