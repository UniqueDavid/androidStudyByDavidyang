package com.example.surfaceview;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity {

	//用来处理渲染频繁的控件
	private SurfaceView sv;
	
	private Paint paint=new Paint();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sv=(SurfaceView) findViewById(R.id.sv);
		
		paint.setColor(Color.RED);
	}
	
	public void xuanran(View v){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				SurfaceHolder holder = sv.getHolder();
				//锁住画布
				Canvas canvas = holder.lockCanvas();
				
				canvas.drawArc(new RectF(10, 10, 200,200), 0, 90, true, paint);
			
			
				//通知更新,解锁画布
				holder.unlockCanvasAndPost(canvas);
			}
		}).start();
		
	}

}
