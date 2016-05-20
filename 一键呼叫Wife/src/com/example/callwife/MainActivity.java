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
		//设置当前的应用程序的欢迎页面
		setContentView(R.layout.activity_main);
		
		//初始化button控件
		btn = (Button) findViewById(R.id.callWife);
		
		//设置相应的OnClick回调监听器
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//				System.out.println("呼叫wife=====");
				
				//给老婆打电话，没有必要自己去写个程序给老婆打电话
				
				//通过意图(intent)来告诉其他的应用程序我想打电话
				//意图:intent
				
				//打人，打球，打假，打给老婆。。。。。
				//泡茶，泡妞，泡温泉
				//前面都是动作，后面都是动作需要的对象
				
				//让可以去打电话的应用帮助我去完成这个事情
				Intent intent=new Intent();
				
				//设置打电话这个动作
				intent.setAction(Intent.ACTION_CALL);
				
				//设置打电话具体需要的数据
				//打电话也需要相应的协议:tel://电话号码
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
