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
		
		//设置一个控制器
		MediaController mc=new MediaController(this);
		
		//将控制器绑定到一个视图上面，可以是videoview也可以是当前activity
		mc.setAnchorView(vv);
		

		
		vv.setMediaController(mc);
	
		
		vv.setVideoPath("/mnt/sdcard/hi.3gp");
		
		vv.start();
		
	}


}
