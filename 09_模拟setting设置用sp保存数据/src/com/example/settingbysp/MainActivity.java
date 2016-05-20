package com.example.settingbysp;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {
	private SeekBar sb;
	private CheckBox cbx;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化控件
		cbx=(CheckBox) findViewById(R.id.checkbox);
		sb=(SeekBar) findViewById(R.id.seekbar);
		
		//创建一个用于保存数据的config文件
		sp=getSharedPreferences("config", 0);
		
		//进入应用后读取config信息，对于各个组件的初值进行初始化
		boolean isCheckedStatus=sp.getBoolean("isChecked",false);
		int progress=sp.getInt("progress",0);
		cbx.setChecked(isCheckedStatus);
		sb.setProgress(progress);
		
		//设置事件监听
		cbx.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				//获得编辑器
				Editor edit = sp.edit();
				
				edit.putBoolean("isChecked", isChecked);
				
				//要commit
				edit.commit();
			}
		});
		
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			//停止拖动seekBar
			public void onStopTrackingTouch(SeekBar seekBar) {
				int progress=seekBar.getProgress();
				//将进度保存到sharedPreference中
				Editor edit = sp.edit();//每次都要调用，因为一commit，这个对象就结束了
				edit.putInt("progress", progress);
				//要commit
				edit.commit();
			}
			
			//开始拖动seekBar
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			//seekBar拖动中
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			}
		});
		
	}
}
