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
 
public class ReadCmdLine {
	//读取文件BUFF块
	private final static int BUFF_SIZE = 1024 * 128;
	public void fileSplit(int fileCount,String infileName,String outfileName) {
		//获得文件总行数
		int totalRow=readCmdLine(infileName);
		int rows =totalRow/fileCount;
		try {
			File fin = new File(infileName);
			FileInputStream fis = new FileInputStream(fin);
			FileChannel fic = fis.getChannel();
			BufferedReader reader = new BufferedReader(Channels.newReader(fic, "UTF-8"), BUFF_SIZE);
			int i = 1;
			int line = 0;
			String tempString;
			boolean falg=true;//标示是否最后一个文件false:是，true:不是
			
			int index = infileName.lastIndexOf('.');
			String filenamehouzhui = infileName.substring(
					index, infileName.length());
			
			File fout = new File("./test/"+outfileName + i + ".txt");
			fout.createNewFile();
			FileOutputStream fos = new FileOutputStream(fout);
			FileChannel foc = fos.getChannel();
			BufferedWriter bw = new BufferedWriter(Channels.newWriter(foc, "UTF-8"), BUFF_SIZE);
			
			while ((tempString = reader.readLine()) != null) {
				bw.write(tempString);
				bw.newLine();
				line++;
				if(falg) {
					if(i!=fileCount) {//写到最后一个文件全部写入，不用分文件了
						if (line == rows) {
							i++;
							System.out.println(i);
							if(i==fileCount) {
								falg=false;//切分到最后一个文件标示为false
							}
							bw.close();
							foc.close();
							fos.close();
							fout = new File("./test/"+outfileName + i + ".txt");
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int readCmdLine(String fileName) {
		int totalRow=0;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("wc -l "+fileName);
			int exitValue = process.waitFor();//shell执行结果
			if (0 == exitValue) {
					BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
					String line = input.readLine();
					totalRow =Integer.parseInt(line.split(" ")[0]);
					input.close();
			}else{
				System.out.println("调用 shell失败. error code is :[%s]");
			}
		} catch (IOException e) {
			 e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return totalRow;
	}
	
	public static void main(String args[]) {
		ReadCmdLine readcmd=new ReadCmdLine();
		readcmd.fileSplit(4, "test.txt", "str");
	}
}