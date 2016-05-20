package com.yz.event;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bt1 = (Button) findViewById(R.id.bt1);
		bt1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("��һ����ť�������");
			}

		});

		Button bt2 = (Button) findViewById(R.id.bt2);
		bt2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("�ڶ�����ť�������");
	}

	// View:ϵͳ��ѳ�������������Ǹ�����Ķ�����Ϊview
	public void getScore(View view) {
		// ͨ������view������ж���ȷ���û����������һ����ť
		// �õ���ť��id
		int id = view.getId();
		switch (id) {
		case R.id.king:
			System.out.println("���Σ�");
			break;
		case R.id.master:
			System.out.println("����ͺ�");
			break;
		case R.id.diamond:
			System.out.println("�п���Ŷ");
			break;
		}

	}
}
