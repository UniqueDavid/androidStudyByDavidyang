package com.example.dial;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//����ť���õ������
		//1���õ���ť����
		Button bt=(Button)findViewById(R.id.bt_dial);
		
		//2����������
		bt.setOnClickListener(new MyListener());
	}
	
	class MyListener implements OnClickListener
	{
		//��ť�����ʱ��˷�������
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			EditText et=(EditText) findViewById(R.id.et_phone);
			String phone=et.getText().toString();
			
			//������Ҫ����ϵͳ�����ǵĶ�������Ҫ��绰
			//������ͼ����
			Intent intent=new Intent();
			//�Ѷ�����װ����ͼ������
			intent.setAction(Intent.ACTION_CALL);
			//���ô��˭
			intent.setData(Uri.parse("tel:"+phone));
			
			//�Ѷ�������ϵͳ
			startActivity(intent);
			
		}
		
	}
	
 }
