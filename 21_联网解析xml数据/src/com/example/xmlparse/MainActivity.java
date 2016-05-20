package com.example.xmlparse;

import android.os.Bundle;
import android.text.TextUtils;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_phonenumber;
	private TextView tv_phonenumber;
	private TextView tv_phonelocation;
	private TextView tv_phonejx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_phonenumber=(EditText) findViewById(R.id.et_phonenumber);
		tv_phonenumber=(TextView) findViewById(R.id.tv_phonenumber);
		tv_phonelocation=(TextView) findViewById(R.id.tv_phonelocation);
		tv_phonejx=(TextView) findViewById(R.id.tv_phonejx);
	}
	String number;
	public void getPhoneJX(View v){
		number=et_phonenumber.getText().toString().trim();
		if(TextUtils.isEmpty(number)){
			Toast.makeText(MainActivity.this,"手机号码不能为空", 0).show();
			return;
		}
		
		//没有手机吉凶查找的地址
	}
}
