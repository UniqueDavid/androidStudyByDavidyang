package com.example.video;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	private VideoView vv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		vv=(VideoView) findViewById(R.id.vv);
		
		//����һ��������
		MediaController mc=new MediaController(this);
		
		//���������󶨵�һ����ͼ���棬������videoviewҲ�����ǵ�ǰactivity
		mc.setAnchorView(vv);
		

		
		vv.setMediaController(mc);
	
		
		vv.setVideoPath("/mnt/sdcard/hi.3gp");
		
		vv.start();
		
	}


}
