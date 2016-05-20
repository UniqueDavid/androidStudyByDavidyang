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
		// �õ��û�����ĺ��������
		EditText et_phone = (EditText) findViewById(R.id.et1);
		EditText et_content = (EditText) findViewById(R.id.et2);

		String phone = et_phone.getText().toString();
		String content = et_content.getText().toString();

		// 1����ȡ���Ź�����
		SmsManager sm = SmsManager.getDefault();

		// 2���и���ţ��ѳ����ŷֳ����ɸ�С����
		ArrayList<String> smss = sm.divideMessage(content);

		// 3��forѭ���Ѽ��������ж���ȫ������ȥ
		for (String string : smss) {
			sm.sendTextMessage(phone, null, string, null, null);
		}
	}
}
