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
		
		//��ʼ���ؼ�
		cbx=(CheckBox) findViewById(R.id.checkbox);
		sb=(SeekBar) findViewById(R.id.seekbar);
		
		//����һ�����ڱ������ݵ�config�ļ�
		sp=getSharedPreferences("config", 0);
		
		//����Ӧ�ú��ȡconfig��Ϣ�����ڸ�������ĳ�ֵ���г�ʼ��
		boolean isCheckedStatus=sp.getBoolean("isChecked",false);
		int progress=sp.getInt("progress",0);
		cbx.setChecked(isCheckedStatus);
		sb.setProgress(progress);
		
		//�����¼�����
		cbx.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				//��ñ༭��
				Editor edit = sp.edit();
				
				edit.putBoolean("isChecked", isChecked);
				
				//Ҫcommit
				edit.commit();
			}
		});
		
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			//ֹͣ�϶�seekBar
			public void onStopTrackingTouch(SeekBar seekBar) {
				int progress=seekBar.getProgress();
				//�����ȱ��浽sharedPreference��
				Editor edit = sp.edit();//ÿ�ζ�Ҫ���ã���Ϊһcommit���������ͽ�����
				edit.putInt("progress", progress);
				//Ҫcommit
				edit.commit();
			}
			
			//��ʼ�϶�seekBar
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			//seekBar�϶���
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			}
		});
		
	}
}
