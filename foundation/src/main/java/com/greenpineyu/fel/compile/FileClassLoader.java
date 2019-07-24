package com.greenpineyu.fel.compile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import cn.com.git.cbs.log.PlatformLogger;

public class FileClassLoader extends ClassLoader {
	
	private static final PlatformLogger LOGGER = PlatformLogger.create();
	
	private String dir;
	public static final String fileType = ".class";

	public FileClassLoader(ClassLoader arg0, String dir) {
		super(arg0);
		this.dir = dir;
	}

	public Class<?> findClass(String name) {
		byte[] data = loadClassData(name);
		return defineClass(name, data, 0, data.length);
	}

	public byte[] loadClassData(String name) {
		name = name.replaceAll("\\.", "/");
		FileInputStream fis = null;
		byte[] data = null;
		try {
			fis = new FileInputStream(new File(dir + name + fileType));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int ch = 0;
			while ((ch = fis.read()) != -1) {
				baos.write(ch);
			}
			data = baos.toByteArray();
		} catch (IOException e) {
			LOGGER.error(e);
		}finally{
			if(fis!= null){
				try {
					fis.close();
				} catch (IOException e) {
					LOGGER.error(e);
				}
			}
		}
		return data;
	}
}
