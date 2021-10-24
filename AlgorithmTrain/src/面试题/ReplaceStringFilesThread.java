package 面试题;
/**
 * 多线程读取100个文件并把文件中的，换成。并输出到一个新的文件a.txt;
	面试题：读和写的线程问题:阻塞队列+异步线程做读写分离
 * @author chdn
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceStringFilesThread extends Thread {

	private static final CountDownLatch countDownLatch = new CountDownLatch(100);
	private String filepath="E:\\学习工作\\c\\";
	private List<String> fileList;
	private int fileIndex;

	public List<String> getFileList() {
		return fileList;
	}
	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}
	public int getFileIndex() {
		return fileIndex;
	}
	public void setFileIndex(int fileIndex) {
		this.fileIndex = fileIndex;
	}
	
	@Override
		public void run() {
			for (int i = 0; i < fileList.size(); i++) {
				if(i%10==fileIndex) {
					File readfile = new File(filepath+fileList.get(i));
					InputStreamReader isr = null;
					try {
						isr=new InputStreamReader(new  FileInputStream(readfile),"utf-8");
						BufferedReader br = new BufferedReader(isr);
						String str = null;
						StringBuilder sb = new StringBuilder();
						while((str=br.readLine())!=null) {
							sb.append(str);
						}
						String string = ""+sb;
						String replace = string.replaceAll("p", "w");
						File file = new File("i"+i+".txt");
						BufferedWriter bw = new BufferedWriter(new FileWriter(file));
						bw.write(replace, 0, replace.length());
						
						bw.close();
						br.close();
						isr.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			countDownLatch.countDown();
		}
	public static void main(String[] args) {
		String filepath = "E:\\学习工作\\c\\";
		File file = new File(filepath);
		String[] files =file.list();
		List<String> fileList= new ArrayList<String>();
		
		for (int i = 0; i < files.length; i++) {
			fileList.add(files[i]);
		}
		
		for (int j = 0; j < 100; j++) {
			ReplaceStringFilesThread thread = new ReplaceStringFilesThread();
			thread.setFileIndex(j);
			thread.setFileList(fileList);
			thread.start();
		}
		try {
			countDownLatch.await();//阻塞当前线程，直到锁存器计数为零
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
