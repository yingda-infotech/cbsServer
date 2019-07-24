package cn.com.git.cbs.biz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.com.git.cbs.batch.BatchTask;
import cn.com.git.cbs.batch.message.BatchRequest;
import cn.com.git.cbs.batch.message.BatchResponse;
import cn.com.git.cbs.log.PlatformLogger;

@Component(value = "biz10001")
public class BatchBiz implements BatchTask {
	@Value("${batch-biz.biz10001.filePath}")
	private String filePath;

	// @Autowired
	// private MenuInfoService menuService;
	// 读取文件BUFF块
	private final static int BUFF_SIZE = 1024 * 128;

	private final static PlatformLogger LOGGER = PlatformLogger.create();

	@Override
	public BatchResponse execute(BatchRequest request) {
		LOGGER.info("获得inParam:" + request.getPara());
		String inParam=request.getPara();
		String[] param = inParam.split("~");
		BatchResponse res = new BatchResponse(request);
		LOGGER.info("开始数据库取数操作!");
		// List<MenuInfo> menulist = menuService.getMainMenuOrder();
		// LOGGER.info("数据库查询获得菜单大小："+menulist.size());
		String infilename = param[2];
		int fileCount = Integer.parseInt(param[3]);
		LOGGER.info("文件名称是：" + infilename);
		String outfilename = param[4];
		LOGGER.info("开始切分文件");
		this.fileSplit(fileCount, infilename, outfilename);
		LOGGER.info("文件切分完毕");
		res.setRspCode("000000");
		res.setRspMsg("交易成功");
		return res;
	}

	public void fileSplit(int fileCount, String infileName, String outfileName) {
		LOGGER.debug("文件输出位置：[%s]", filePath);
		LOGGER.debug("输入文件：[%s] 输出文件：[%s] 文件份数：[%d]", infileName, outfileName, fileCount);
		// 获得文件总行数
		// int totalRow=readCmdLine(infileName);
		int totalRow = 800000;
		int rows = totalRow / fileCount;
		try {
			File fin = new File(filePath + infileName + ".txt");
			FileInputStream fis = new FileInputStream(fin);
			FileChannel fic = fis.getChannel();
			BufferedReader reader = new BufferedReader(Channels.newReader(fic, "UTF-8"), BUFF_SIZE);
			int i = 1;
			int line = 0;
			String tempString;
			boolean falg = true;// 标示是否最后一个文件false:是，true:不是

			// int index = infileName.lastIndexOf('.');
			// String filenamehouzhui = infileName.substring(
			// index, infileName.length());

			File fout = new File(filePath + outfileName + StringUtils.leftPad(String.valueOf(i), 2, "0") + ".txt");
			fout.createNewFile();
			FileOutputStream fos = new FileOutputStream(fout);
			FileChannel foc = fos.getChannel();
			BufferedWriter bw = new BufferedWriter(Channels.newWriter(foc, "UTF-8"), BUFF_SIZE);

			while ((tempString = reader.readLine()) != null) {
				bw.write(tempString);
				bw.newLine();
				line++;
				if (falg) {
					if (i != fileCount) {// 写到最后一个文件全部写入，不用分文件了
						if (line == rows) {
							i++;
							if (i == fileCount) {
								falg = false;// 切分到最后一个文件标示为false
							}
							bw.close();
							foc.close();
							fos.close();
							fout = new File(
									filePath + outfileName + StringUtils.leftPad(String.valueOf(i), 2, "0") + ".txt");
							fout.createNewFile();
							fos = new FileOutputStream(fout);
							foc = fos.getChannel();
							bw = new BufferedWriter(Channels.newWriter(foc, "UTF-8"), BUFF_SIZE);
							line = 0;
						}
					}
				}
			}
			bw.close();
			foc.close();
			fos.close();
			reader.close();
			fis.close();
		} catch (FileNotFoundException e) {
			LOGGER.error("文件未找到%s！", infileName, e);
		} catch (IOException e) {
			LOGGER.error("切割文件失败%s！", infileName, e);
		}
	}

	public int readCmdLine(String fileName) {
		int totalRow = 0;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("wc -l " + fileName);
			int exitValue = process.waitFor();// shell执行结果
			if (0 == exitValue) {
				BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line = input.readLine();
				totalRow = Integer.parseInt(line.split(" ")[0]);
				input.close();
				LOGGER.info("读取文件[%s]总行数为:[%d]", fileName, totalRow);
			} else {
				LOGGER.error("调用 shell失败. error code is :[%s]", exitValue);
			}
		} catch (IOException e) {
			LOGGER.error("读取命令行失败！", e);
		} catch (InterruptedException e) {
			LOGGER.error("调用shell脚本失败%s！", fileName, e);
		}
		return totalRow;
	}

	public static void main(String[] args) {
		String inParam = "1~2~2~e~g~";
		String[] param = inParam.split("~");
		System.out.println(param[4]);
		// String fileName="asdadsa.csv";
		// int index = fileName.lastIndexOf('.');
		// String filenamehouzhui = fileName.substring(
		// index, fileName.length());
		// String name = fileName.substring(0,index);
		// System.out.println(filenamehouzhui);
		// System.out.println(name);
	}
}
