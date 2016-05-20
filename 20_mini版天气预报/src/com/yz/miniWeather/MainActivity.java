package com.yz.miniWeather;

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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
	private String[] weather=null;
	private EditText et_city;
	private TextView tv_date, tv_fengxiang, tv_fengli, tv_low, tv_high, tv_type;
	private Handler myhandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			weather=(String[]) msg.obj;
			switch (msg.what) {
			case SUCCESS:
				if (weather == null) {
					Toast.makeText(MainActivity.this, "�Բ��𣬸ó��е�����������ʧ�����Ԫ���ˣ���", 0).show();
					break;
				} else {
					tv_date.setText(weather[5]);
					tv_fengxiang.setText(weather[0]);
					tv_fengli.setText(weather[1]);
					tv_high.setText(weather[2]);
					tv_type.setText(weather[3]);
					tv_low.setText(weather[4]);
					break;
				}
			case NETERROR:
				Toast.makeText(MainActivity.this, "�Բ����������Ӵ��󣡣�", 0).show();
				break;
			case SERVERERROR:
				Toast.makeText(MainActivity.this, "�Բ��𣬷��������Ӵ��󣡣�", 0).show();
				break;
			}

		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		et_city = (EditText) findViewById(R.id.et_city);

	}

	String path;

	public void getWeather(View v) {

		new Thread() {
			public void run() {
				try {
					// �˴�Ҫ��URLEncoder�࣬�м�
					path = "http://wthrcdn.etouch.cn/weather_mini?city="
							+ URLEncoder.encode(et_city.getText().toString().trim(), "utf-8");

					if (TextUtils.isEmpty(et_city.getText().toString())) {
						Toast.makeText(MainActivity.this, "�Բ��𣬳���������Ϊ��", 0).show();
					}
					URL url = new URL(path);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					// ���ó�ʱ��Ϣ
					conn.setConnectTimeout(5000);
					// ��������ʽ
					conn.setRequestMethod("GET");
					// ��÷�����
					int code = conn.getResponseCode();

					if (code == 200) {
						InputStream is = conn.getInputStream();

						String data = StreamTool.decodeStream(is);

						// ����json����,�϶�Ҫ������Դ�ģ�����
						JSONObject jsonObj = new JSONObject(data);

						// ���desc��ֵ
						String result = jsonObj.getString("desc");
						if ("OK".equals(result)) {
							// ������Ч����������Ҫ������
							JSONObject dataObj = jsonObj.getJSONObject("data");
							JSONArray jsonArray = dataObj.getJSONArray("forecast");
							JSONObject Obj = jsonArray.getJSONObject(0);
							String fengxiang = Obj.getString("fengxiang");
							String fengli = Obj.getString("fengli");
							String high = Obj.getString("high");
							String type = Obj.getString("type");
							String low = Obj.getString("low");
							String date = Obj.getString("date");

							weather=new String[]{fengxiang,fengli,high,type,low,date};

							Message msg = Message.obtain();
							msg.obj=weather;
							msg.what = SUCCESS;
							myhandler.sendMessage(msg);
						}

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
