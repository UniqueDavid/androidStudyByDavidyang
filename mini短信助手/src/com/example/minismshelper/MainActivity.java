package com.example.minismshelper;

import android.os.Bundle;
import android.telephony.SmsManager;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_msgcontent,et_contacternumber;
	private String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_msgcontent = (EditText) findViewById(R.id.et_msgcontent);
		et_contacternumber=(EditText) findViewById(R.id.et_contacternumber);
	}
	
	public void sendMsg(View v){
		SmsManager manager=SmsManager.getDefault();
		ArrayList<String> list = manager.divideMessage(et_msgcontent.getText().toString().trim());
		for(String str:list){
			manager.sendTextMessage(et_contacternumber.getText().toString().trim(), null, str, null, null);
		}
		Toast.makeText(this,"���͸�"+name+"�Ķ��ųɹ�����", 0).show();
	}

	public void selectContacter(View v) {
		Intent intent = new Intent();
		intent.setClass(this, ContacterActivity.class);
		// startActivity(intent);
		// ����ĳ��activityΪ��ĳ�����
		startActivityForResult(intent, 1);
	}

	public void selectMsg(View v) {
		// �������B activity���ڽ���

		// ʹ����ʾ��ͼȥ����smsActivity
		Intent intent = new Intent();
		intent.setClass(this, SmsActivity.class);
		// startActivity(intent);
		// ����ĳ��activityΪ��ĳ�����
		startActivityForResult(intent, 0);
	}

	// �������ڴ����ĳ��activity���صĽ��
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (data != null) {
			if (requestCode == 0) {
				try {
					String msg = data.getStringExtra("msg");
					String newmsg=new String(msg.getBytes(),"utf-8");
					et_msgcontent.setText(newmsg);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} else if (requestCode == 1) {
				String number = data.getStringExtra("number");
				name=data.getStringExtra("name");
				et_contacternumber.setText(number);
			}
		}
	}
}
