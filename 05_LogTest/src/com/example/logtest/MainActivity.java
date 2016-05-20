package com.example.logtest;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.v(TAG, "俺是一个详细的日志信息");
		Log.i(TAG, "俺是一个一般的日志信息");
		Log.d(TAG, "俺是一个调试的日志信息");
		Log.w(TAG, "俺是一个警告的日志信息");
		Log.e(TAG, "俺是一个错误的日志信息");
	}
}
