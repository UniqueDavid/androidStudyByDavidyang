package com.example.callwife;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//���õ�ǰ��Ӧ�ó���Ļ�ӭҳ��
		setContentView(R.layout.activity_main);
		
		//��ʼ��button�ؼ�
		btn = (Button) findViewById(R.id.callWife);
		
		//������Ӧ��OnClick�ص�������
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				System.out.println("����wife=====");
				
				//�����Ŵ�绰��û�б�Ҫ�Լ�ȥд����������Ŵ�绰
				
				//ͨ����ͼ(intent)������������Ӧ�ó��������绰
				//��ͼ:intent
				
				//���ˣ����򣬴�٣�������š���������
				//�ݲ裬��椣�����Ȫ
				//ǰ�涼�Ƕ��������涼�Ƕ�����Ҫ�Ķ���
				
				//�ÿ���ȥ��绰��Ӧ�ð�����ȥ����������
				Intent intent=new Intent();
				
				//���ô�绰�������
				intent.setAction(Intent.ACTION_CALL);
				
				//���ô�绰������Ҫ������
				//��绰Ҳ��Ҫ��Ӧ��Э��:tel://�绰����
				intent.setData(Uri.parse("tel://5201314"));
				
				startActivity(intent);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
