package com.example.qqlogin;

import android.os.Bundle;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	protected static final int SUCCESS = 0;
	protected static final int NETERROR = 1;
	protected static final int SERVERERROR = 2;
	private TextView tv_logininfo;
	private EditText et_qqnumber,et_qqpassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//³õÊ¼»¯¿Ø¼þ
		et_qqnumber=(EditText) findViewById(R.id.et_qqnumber);
		et_qqpassword=(EditText) findViewById(R.id.et_qqpassword);
		tv_logininfo=(TextView) findViewById(R.id.tv_logininfo);
	}
	public void login(View v) throws UnsupportedEncodingException{
		String path="http://192.168.1.100:8080/QQLogin/loginServlet?number="+URLEncoder.encode(et_qqnumber.getText().toString().trim(),"utf-8")+"&password="+URLEncoder.encode(et_qqpassword.getText().toString().trim(),"utf-8");
		
		AsyncHttpClient client=new AsyncHttpClient();
	
		client.get(path, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				tv_logininfo.setText(new String(responseBody));
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
			}
		});
	}

}
