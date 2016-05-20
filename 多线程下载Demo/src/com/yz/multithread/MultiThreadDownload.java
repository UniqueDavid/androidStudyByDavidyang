package com.yz.multithread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.lang.management.ThreadInfo;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MultiThreadDownload {
	// 记录总的下载线程
	public static int currentDownloadingCount = 3;
	// 规定下载服务器端的资源使用3条线程去下载
	private static int threadCount = 3;
	// private static String path="http://192.168.1.100:8080/file.txt";
	private static String path = "http://192.168.1.100:8080/a.doc";

	public static void main(String[] args) {
		// 1、服务器发送请求，拿到要下载的文件长度是多少？？

		try {
			URL url = new URL(path);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");

			int code = conn.getResponseCode();
			if (code == 200) {
				// 拿到文件的长度大小
				int length = conn.getContentLength();
				File file = new File(getFilename(path));

				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.setLength(length);
				raf.close();

				// 启动线程去下载文件了

				// 每块线程下载的平均大小
				int blockSize = length / threadCount;

				// threadId:线程的id号
				// threadCount:开几条线程去下载
				for (int threadId = 0; threadId < threadCount; threadId++) {
					int startIndex = threadId * blockSize;
					int endIndex = (threadId + 1) * blockSize - 1;

					// 最后一块可能大小没有blockSize那么大，因此要单独判断
					if (threadId == threadCount - 1) {
						endIndex = length - 1;
					}
					new DownloadFilePartThread(threadId, startIndex, endIndex).start();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static class DownloadFilePartThread extends Thread {

		// 每个线程的id号
		private int threadId;
		// 线程的开始位置
		private int startIndex;
		// 线程的结束位置
		private int endIndex;

		// 当前线程下载的位置
		private int currentPosition;

		public DownloadFilePartThread(int threadId, int startIndex, int endIndex) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void run() {
			System.out.println("第" + threadId + "条线程从" + startIndex + "--" + endIndex + "开始下载");
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				// 在多线程下载的时候，每条线程只需要目标文件的一部分内容
				// 告诉服务器，只需要那一段数据
				// 通过设置http的请求头可以去实现
				// startIndex-endIndex
				conn.setRequestProperty("range", "bytes=" + startIndex + "-" + endIndex);
				File file = new File(getFilename(path));
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				// 获得服务器返回的目标端的数据
				File ilf = new File(threadId + ".position");

				if (ilf.exists() && ilf.length() > 0) {
					BufferedReader br = new BufferedReader(new FileReader(ilf));
					String vl = br.readLine();
					int alreadyWritePosition = Integer.parseInt(vl);
					// 告诉服务器要数据的位置
					conn.setRequestProperty("range", "bytes=" + startIndex + "-" + endIndex);
					raf.seek(alreadyWritePosition);
					System.out.println("下载过");
				} else {
					System.out.println("之前没有下载");
					conn.setRequestProperty("range", "bytes=" + startIndex + "-" + endIndex);
					// 要告诉从哪个位置开始写
					raf.seek(startIndex);
				}
				// 206---
				int code = conn.getResponseCode();
				if (code == 206) {

					InputStream is = conn.getInputStream();
					int len = 0;
					byte[] buf = new byte[1024];
					while ((len = is.read(buf)) > 0) {
						raf.write(buf, 0, len);
						// 将实时的位置给记录下来，记录以后方便紧接着写
						currentPosition = currentPosition + len;
						File info = new File(threadId + ".position");
						OutputStream out = new FileOutputStream(info);

						out.write((currentPosition + "").getBytes());
						out.close();
					}

					is.close();
					raf.close();
					System.out.println("第" + threadId + "条线程下载完成");
					// 等所有的线程下载完成再去删除记录文件，弄一个计数器
					// 当发现计数器小于或者等于0的时候，说明没有线程正在下载
					synchronized (MultiThreadDownload.class) {
						currentDownloadingCount--;
						if (currentDownloadingCount <= 0) {
							for (threadId = 0; threadId < threadCount; threadId++) {
								File fff = new File(threadId + ".position");
								fff.delete();
							}
						}
					}
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static String getFilename(String path) {
		int index = path.lastIndexOf('/');

		return path.substring(index + 1);
	}

}
