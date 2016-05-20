package com.example.qqlogin;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
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
			case NETERROR:Toast.makeText(MainActivity.this,"网络连接错误！！", 0).show();break;
			case SERVERERROR:Toast.makeText(MainActivity.this,"服务器连接错误！！", 0).show();break;
			}
			
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化控件
		et_qqnumber=(EditText) findViewById(R.id.et_qqnumber);
		et_qqpassword=(EditText) findViewById(R.id.et_qqpassword);
		tv_logininfo=(TextView) findViewById(R.id.tv_logininfo);
	}
	public void login(View v){
		new Thread(){
			@Override
			public void run() {
				try {
					
					String path="http://192.168.1.100:8080/QQLogin/loginServlet?number="+URLEncoder.encode(et_qqnumber.getText().toString().trim(),"utf-8")+"&password="+URLEncoder.encode(et_qqpassword.getText().toString().trim(),"utf-8");
				
					//此处利用apache封装好的API
					HttpClient client=new DefaultHttpClient();
					
					HttpGet get = new HttpGet(path);
					
					HttpResponse response = client.execute(get);
					
					//http的响应分为相应行、相应头、响应体
					int code = response.getStatusLine().getStatusCode();
					

					if(code==200){
						InputStream is = response.getEntity().getContent();
						String data=StreamTool.decodeStream(is);
						
						Message msg = Message.obtain();
						msg.obj=data;
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
