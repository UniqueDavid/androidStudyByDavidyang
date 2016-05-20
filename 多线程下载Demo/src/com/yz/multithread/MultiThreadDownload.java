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
	// ��¼�ܵ������߳�
	public static int currentDownloadingCount = 3;
	// �涨���ط������˵���Դʹ��3���߳�ȥ����
	private static int threadCount = 3;
	// private static String path="http://192.168.1.100:8080/file.txt";
	private static String path = "http://192.168.1.100:8080/a.doc";

	public static void main(String[] args) {
		// 1�����������������õ�Ҫ���ص��ļ������Ƕ��٣���

		try {
			URL url = new URL(path);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(5000);
			conn.setRequestMethod("GET");

			int code = conn.getResponseCode();
			if (code == 200) {
				// �õ��ļ��ĳ��ȴ�С
				int length = conn.getContentLength();
				File file = new File(getFilename(path));

				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.setLength(length);
				raf.close();

				// �����߳�ȥ�����ļ���

				// ÿ���߳����ص�ƽ����С
				int blockSize = length / threadCount;

				// threadId:�̵߳�id��
				// threadCount:�������߳�ȥ����
				for (int threadId = 0; threadId < threadCount; threadId++) {
					int startIndex = threadId * blockSize;
					int endIndex = (threadId + 1) * blockSize - 1;

					// ���һ����ܴ�Сû��blockSize��ô�����Ҫ�����ж�
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

		// ÿ���̵߳�id��
		private int threadId;
		// �̵߳Ŀ�ʼλ��
		private int startIndex;
		// �̵߳Ľ���λ��
		private int endIndex;

		// ��ǰ�߳����ص�λ��
		private int currentPosition;

		public DownloadFilePartThread(int threadId, int startIndex, int endIndex) {
			this.threadId = threadId;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
		}

		@Override
		public void run() {
			System.out.println("��" + threadId + "���̴߳�" + startIndex + "--" + endIndex + "��ʼ����");
			try {
				URL url = new URL(path);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				// �ڶ��߳����ص�ʱ��ÿ���߳�ֻ��ҪĿ���ļ���һ��������
				// ���߷�������ֻ��Ҫ��һ������
				// ͨ������http������ͷ����ȥʵ��
				// startIndex-endIndex
				conn.setRequestProperty("range", "bytes=" + startIndex + "-" + endIndex);
				File file = new File(getFilename(path));
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				// ��÷��������ص�Ŀ��˵�����
				File ilf = new File(threadId + ".position");

				if (ilf.exists() && ilf.length() > 0) {
					BufferedReader br = new BufferedReader(new FileReader(ilf));
					String vl = br.readLine();
					int alreadyWritePosition = Integer.parseInt(vl);
					// ���߷�����Ҫ���ݵ�λ��
					conn.setRequestProperty("range", "bytes=" + startIndex + "-" + endIndex);
					raf.seek(alreadyWritePosition);
					System.out.println("���ع�");
				} else {
					System.out.println("֮ǰû������");
					conn.setRequestProperty("range", "bytes=" + startIndex + "-" + endIndex);
					// Ҫ���ߴ��ĸ�λ�ÿ�ʼд
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
						// ��ʵʱ��λ�ø���¼��������¼�Ժ󷽱������д
						currentPosition = currentPosition + len;
						File info = new File(threadId + ".position");
						OutputStream out = new FileOutputStream(info);

						out.write((currentPosition + "").getBytes());
						out.close();
					}

					is.close();
					raf.close();
					System.out.println("��" + threadId + "���߳��������");
					// �����е��߳����������ȥɾ����¼�ļ���Ūһ��������
					// �����ּ�����С�ڻ��ߵ���0��ʱ��˵��û���߳���������
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
