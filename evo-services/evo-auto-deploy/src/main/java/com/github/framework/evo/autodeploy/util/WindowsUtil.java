package com.github.framework.evo.autodeploy.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * User: Guosd
 * Date: 2019-07-27 08:48
 */
public class WindowsUtil {
	
	private static InputStream errorStream;
	private static InputStream inputStream ;
	private static PrintWriter printWriter;
	
	private static String result;
	
	static {
		try {
			Process process = Runtime.getRuntime().exec("cmd");
			printWriter = new PrintWriter(process.getOutputStream());
			errorStream = process.getErrorStream();
			inputStream = process.getInputStream();
			StreamThread1 thread1 = new WindowsUtil().new StreamThread1(errorStream);
			thread1.start();
			StreamThread1 thread2 = new WindowsUtil().new StreamThread1(inputStream);
			thread2.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void execute(String string) {
		printWriter.println(string);
		printWriter.println();
		printWriter.flush();
	}

	/**
	 * 打印操作
	 * @author Administrator
	 *
	 */
	private class StreamThread1 extends Thread{
		BufferedReader bufferedReader ;
		public StreamThread1(InputStream is) {
			bufferedReader = new BufferedReader(new InputStreamReader(is,Charset.forName(System.getProperty("sun.jnu.encoding"))));
		}
		
		@Override
		public void run() {
			int b ;
			try {
				String line = null;
				while((line = bufferedReader.readLine())!=null) {
					result = line;
					System.out.println(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			};
		}
	}
	
	public static void close() {
		printWriter.close();
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			errorStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getMessage(){
		return result;
	}
}
