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
				System.out.println("第一个按钮被点击了");
			}

		});

		Button bt2 = (Button) findViewById(R.id.bt2);
		bt2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		System.out.println("第二个按钮被点击了");
	}

	// View:系统会把出发这个方法的那个组件的对象作为view
	public void getScore(View view) {
		// 通过对于view对象的判断来确定用户点击的是哪一个按钮
		// 拿到按钮的id
		int id = view.getId();
		switch (id) {
		case R.id.king:
			System.out.println("做梦！");
			break;
		case R.id.master:
			System.out.println("想想就好");
			break;
		case R.id.diamond:
			System.out.println("有可能哦");
			break;
		}

	}
}
