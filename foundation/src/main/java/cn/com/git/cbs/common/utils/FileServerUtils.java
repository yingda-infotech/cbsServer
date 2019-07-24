/***
 * Copyright &copy; 2016 <a href="http://www.git.com.cn/">GIT</a> All rights reserved.
 **/
package cn.com.git.cbs.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import cn.com.git.cbs.log.PlatformLogger;
import cn.com.git.cbs.platform.utils.ConfigUtils;
import cn.com.git.cbs.platform.utils.ExceptionUtils;

/**
 * 文件操作工具类
 * 
 * @author DengJia
 */
public class FileServerUtils {
	private final static PlatformLogger LOGGER = PlatformLogger.create();
	
	private FileServerUtils() {

	}
	
	/**
	 * 从本地获取文件，并且作为输入流返回
	 * @param fileName 文件名称
	 * @return InputStream 文件输入流
	 */
	public static InputStream getFileStream(String fileName) {	
		try {
			File file = new File(ConfigUtils.getAppProperty("file.url")+fileName);
			return new FileInputStream(file);
		} catch (Exception e) {
			throw ExceptionUtils.returnError("FE0003", fileName, e);
		}
	}
		
	/**
	 * 上传文件到服务器
	 * @param upLoadUrl 上传文件的url 例如http://10.100.21.201/upload.do
	 * @param fileName 文件名
	 * @return 上传标识，true表示上传成功，false表示上传失败
	 */
	public static boolean uploadFile(String upLoadUrl,String fileName) {
		CloseableHttpClient client = null;
		try {		
			if(fileName == null || "".equals(fileName.trim())) {
				throw ExceptionUtils.returnError("FE0001");
			}
			if(upLoadUrl == null || "".equals(upLoadUrl.trim())) {
				throw ExceptionUtils.returnError("FE0002");
			}
			client = HttpClients.createDefault();
			HttpPost post = new HttpPost(upLoadUrl);
			File file = new File(fileName);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			builder.addBinaryBody("filename", file);
			HttpEntity entity = builder.build();
			post.setEntity(entity);
			HttpResponse response = client.execute(post);		
			if(response.getStatusLine().getStatusCode() == 200) {
				return true;
			} else {
				return false;
			}
		 } catch (Exception e) {
			 LOGGER.error("文件名为%s上传失败！", fileName, e);
			 return false;
		 } finally {
			 if(client != null) {
				 try {
					client.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			 }
		 }
	}
}
