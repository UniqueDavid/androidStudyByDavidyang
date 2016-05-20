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
		
		Log.v(TAG, "����һ����ϸ����־��Ϣ");
		Log.i(TAG, "����һ��һ�����־��Ϣ");
		Log.d(TAG, "����һ�����Ե���־��Ϣ");
		Log.w(TAG, "����һ���������־��Ϣ");
		Log.e(TAG, "����һ���������־��Ϣ");
	}
}
