package com.example.notepad;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_content;
	SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_content=(EditText) findViewById(R.id.et_content);
		sp = getSharedPreferences("content",0);
		String vl = sp.getString("content","");
		
		et_content.setText(vl);
	}
	@Override
	protected void onDestroy() {
		String content=et_content.getText().toString().trim();
		sp=getSharedPreferences("content",0);
		Editor editor=sp.edit();
		editor.putString("content",content);
		editor.commit();
		super.onDestroy();
		
		Toast.makeText(MainActivity.this,"∏Á√«£¨∞Ôƒ„±£¥Ê¡À£°£°", 0).show();
	}

}
