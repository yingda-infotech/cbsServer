/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import cn.com.git.cbs.log.PlatformLogger;

/**
 * 文件操作工具类
 * 
 * @author DengJia
 */
public class FileUtils {
	private final static PlatformLogger LOGGER = PlatformLogger.create();
	
	private FileUtils() {

	}
	

	/**
	 * 读取通过资源全路径读取文本文件
	 * 
	 * @param fullName
	 *            文件名
	 * @param encoding
	 *            文件编码，如果为空则使用系统默认编码
	 * @return 文件内容
	 */
	public static String readTextResource(String fullName, String encoding) {
		if (encoding == null) {
			encoding = System.getProperty("file.encoding");
		}
		StringBuilder sb = new StringBuilder();
		String str = "";
		try {
			InputStream is = new ClassPathResource(fullName).getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, Charset.forName(encoding)));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(System.lineSeparator());
				sb.append(line);
			}
			if (sb.length() > System.lineSeparator().length()) {
				sb.delete(0, System.lineSeparator().length());
			}
			reader.close();
			is.close();
			str = sb.toString();
		} catch (IOException x) {
			LOGGER.error("读取文件:[%s]失败", fullName, x);
		}
		return str;
	}

	/**
	 * 通过文件全路径读取文本文件
	 * 
	 * @param fullName
	 *            文件名
	 * @param encoding
	 *            文件编码，如果为空则使用系统默认编码
	 * @return 文件内容
	 */
	public static String readTextFile(String fullName, String encoding) {
		if (encoding == null) {
			encoding = System.getProperty("file.encoding");
		}
		StringBuilder sb = new StringBuilder();
		String str = "";
		try {
			InputStream is = new FileSystemResource(fullName).getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, Charset.forName(encoding)));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(System.lineSeparator());
				sb.append(line);
			}
			if (sb.length() > System.lineSeparator().length()) {
				sb.delete(0, System.lineSeparator().length());
			}
			reader.close();
			is.close();
			str = sb.toString();
		} catch (IOException x) {
			LOGGER.error("读取文件:[%s]失败", fullName, x);
		}
		return str;
	}
}
