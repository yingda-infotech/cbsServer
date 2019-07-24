package cn.com.git.cbs.biz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;

public class wirterTxt {
	
	private final static int BUFF_SIZE = 1024 * 128;
	
	public static void main(String[] args) throws IOException {
		File fout = new File("./test/test.txt");
		fout.createNewFile();
		FileOutputStream fos = new FileOutputStream(fout);
		FileChannel foc = fos.getChannel();
		BufferedWriter bw = new BufferedWriter(Channels.newWriter(foc, "UTF-8"), BUFF_SIZE);
		for(int i=0;i<6000000;i++) {
			bw.write("1111");
			bw.newLine();
		}
		bw.close();
		foc.close();
		fos.close();
	}

}
