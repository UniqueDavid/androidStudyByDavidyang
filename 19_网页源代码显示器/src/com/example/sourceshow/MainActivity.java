package com.example.sourceshow;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int SUCCESS = 0;
	protected static final int NETERROR = 1;
	protected static final int SERVERERROR = 2;
	private TextView tv_source;
	private EditText et_sourceurl;
	private Handler myhandler = new Handler() {
		public void handleMessage(Message msg) {
			String data = (String) msg.obj;
			switch (msg.what) {
			case SUCCESS:
				tv_source.setText(data);
				break;
			case NETERROR:
				Toast.makeText(MainActivity.this, "网络连接错误", 0).show();
				break;
			case SERVERERROR:
				Toast.makeText(MainActivity.this, "服务器响应错误", 0).show();
				break;

			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_sourceurl = (EditText) findViewById(R.id.et_sourceurl);
		tv_source = (TextView) findViewById(R.id.tv_source);
	}

	public void viewSource(View v) {

		new Thread() {
			public void run() {
				String path = et_sourceurl.getText().toString().trim();
				if(TextUtils.isEmpty(path)){
					Toast.makeText(MainActivity.this,"路径不能为空！！", 0).show();
				}
				try {
					URL url = new URL(path);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					// 设置连接超时为5秒钟
					conn.setConnectTimeout(5000);
					// 设置请方式
					conn.setRequestMethod("GET");

					String contentType = conn.getContentType();

					int code = conn.getResponseCode();
					if (code == 200) {
						InputStream is = conn.getInputStream();
						// 如何将一个流转为一个字符串
						String data = StreamTool.decodeStream(is);

						is.close();
						Message msg = Message.obtain();
						msg.what = SUCCESS;
						msg.obj = data;
						myhandler.sendMessage(msg);
					} else {
						Message msg = Message.obtain();
						msg.what = SERVERERROR;
						myhandler.sendMessage(msg);
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Message msg = Message.obtain();
					msg.what = NETERROR;
					myhandler.sendMessage(msg);
				}
			};
		}.start();

	}

}
