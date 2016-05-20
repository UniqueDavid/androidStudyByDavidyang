package com.example.downloadbymanythread;

import android.os.Bundle;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static int currentDownloadingCount=3;
	private  static int threadCount = 3;
	private EditText et_downloadurl;
	private TextView tv_status;
	public static String path="http://192.168.1.100:8080/file.txt";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ���ؼ�
		et_downloadurl=(EditText) findViewById(R.id.et_downloadurl);
		tv_status=(TextView) findViewById(R.id.tv_status);
		
		
		
	}
	public void download(View v){
		path=et_downloadurl.getText().toString().trim();
		
		AsyncHttpClient client=new AsyncHttpClient();
		client.get(path, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				//�õ��ļ�����
				int length = responseBody.length;
				File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+getFilename(path));
				
				try {
					RandomAccessFile raf=new RandomAccessFile(file,"rw");
					raf.setLength(length);
					raf.close();
					
					//�����߳�ȥ�����ļ�
					
					//ÿ���߳����صĴ�С
					int blockSize=length/threadCount;
					
					//threadId:�̵߳�id��
					for(int threadId=0;threadId<threadCount;threadId++){
						int startIndex=threadId*blockSize;
						int endIndex=(threadId+1)*blockSize-1;
						
						//ע�����һ���С����û���趨����ô��
						if(threadId==threadCount-1){
							endIndex=length-1;
						}
						new DownloadFilePartThread(threadId,startIndex,endIndex);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		

			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
			}
		});
		tv_status.setText("�������");
		
	}
	
	private static class DownloadFilePartThread extends Thread{
		public DownloadFilePartThread(int threadId, int startIndex, int endIndex) {
			this.threadId=threadId;
			this.startIndex=startIndex;
			this.endIndex=endIndex;
		}
		//ÿ���̵߳�id��
		private int threadId;
		//�̵߳Ŀ�ʼλ��
		private int startIndex;
		//�̵߳Ľ���λ��
		private int endIndex;
		// ��ǰ�߳����ص�λ��
		private int currentPosition;
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
				File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+getFilename(path));
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				// ��÷��������ص�Ŀ��˵�����
				File ilf = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+threadId + ".position");

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
						File info = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+threadId + ".position");
						OutputStream out = new FileOutputStream(info);

						out.write((currentPosition + "").getBytes());
						out.close();
					}

					is.close();
					raf.close();
					System.out.println("��" + threadId + "���߳��������");
					// �����е��߳����������ȥɾ����¼�ļ���Ūһ��������
					// �����ּ�����С�ڻ��ߵ���0��ʱ��˵��û���߳���������
					synchronized (MainActivity.class) {
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
		int index = path.lastIndexOf("/");
		String filename = path.substring(index+1);
		return filename;
	}
}
