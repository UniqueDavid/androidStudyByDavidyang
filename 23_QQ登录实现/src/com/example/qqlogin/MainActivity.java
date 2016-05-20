package com.example.qqlogin;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.util.EncodingUtils;

import com.example.qqlogin.utils.StreamTool;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int SUCCESS = 0;
	protected static final int NETERROR = 1;
	protected static final int SERVERERROR = 2;
	private TextView tv_logininfo;
	private EditText et_qqnumber,et_qqpassword;
	private Handler myhandler=new Handler(){
		public void handleMessage(Message msg) {
			String data=(String) msg.obj;
			switch(msg.what){
			case SUCCESS:tv_logininfo.setText(data);break;
			case NETERROR:Toast.makeText(MainActivity.this,"�������Ӵ��󣡣�", 0).show();break;
			case SERVERERROR:Toast.makeText(MainActivity.this,"���������Ӵ��󣡣�", 0).show();break;
			}
			
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//��ʼ���ؼ�
		et_qqnumber=(EditText) findViewById(R.id.et_qqnumber);
		et_qqpassword=(EditText) findViewById(R.id.et_qqpassword);
		tv_logininfo=(TextView) findViewById(R.id.tv_logininfo);
	}
	public void login(View v){
		final String path="http://192.168.1.100:8080/QQLogin/loginServlet?";
		new Thread(){
			@Override
			public void run() {
				try {
					String number=URLEncoder.encode(et_qqnumber.getText().toString().trim(),"utf-8");
					String password=URLEncoder.encode(et_qqpassword.getText().toString().trim(),"utf-8");
					URL url=new URL(path+"number="+number+"&password="+password);
					
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5000);
//					conn.setRequestMethod("GET");
					//����post��ʽ�Ļ��������趨����Ӧ�Ĳ�������Ϊpost��ʽ�ǲ����в�����
					conn.setRequestMethod("POST");
					
					//content-type:application/x-www-form-urlencoded
					conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
					
					//׼��������
					String data="number="+number+"&password="+password;
					
					//���ݳ���
					conn.setRequestProperty("Content-Length", data.length()+"");
					
					//����ط���ʾ���߼���һ����־��Ҫ��������д������
					conn.setDoOutput(true);
					
					conn.getOutputStream().write(data.getBytes());
					
					//��Ҫ���http post����Ĺ��̱Ƚ���Ϥ
					
					
					int code = conn.getResponseCode();
					if(code==200){
						InputStream is = conn.getInputStream();
						String data2=StreamTool.decodeStream(is);
						
						Message msg = Message.obtain();
						msg.obj=data2;
						msg.what=SUCCESS;
						myhandler.sendMessage(msg);
						
					}else{
						Message msg = Message.obtain();
						msg.what=SERVERERROR;
						myhandler.sendMessage(msg);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Message msg = Message.obtain();
					msg.what=NETERROR;
					myhandler.sendMessage(msg);
				}
				
			}
		}.start();
	}

}
