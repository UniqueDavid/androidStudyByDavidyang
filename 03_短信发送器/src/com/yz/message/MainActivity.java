package com.yz.message;

import android.os.Bundle;
import android.telephony.SmsManager;

import java.util.ArrayList;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void send(View view) {
		// 拿到用户输入的号码和内容
		EditText et_phone = (EditText) findViewById(R.id.et1);
		EditText et_content = (EditText) findViewById(R.id.et2);

		String phone = et_phone.getText().toString();
		String content = et_content.getText().toString();

		// 1、获取短信管理器
		SmsManager sm = SmsManager.getDefault();

		// 2、切割短信，把长短信分成若干个小短信
		ArrayList<String> smss = sm.divideMessage(content);

		// 3、for循环把集合中所有短信全部发出去
		for (String string : smss) {
			sm.sendTextMessage(phone, null, string, null, null);
		}
	}
}
