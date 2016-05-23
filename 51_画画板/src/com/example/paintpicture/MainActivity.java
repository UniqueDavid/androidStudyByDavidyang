package com.example.paintpicture;

import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnClickListener, OnSeekBarChangeListener, OnTouchListener {

	private View color_red, color_black, color_green, color_blue, color_yellow, color_orange;
	private SeekBar seekBar;
	private ImageView iv;
	private Bitmap bitmap;
	private Canvas canvas;
	private Paint paint;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		color_red = findViewById(R.id.color_red);
		color_black = findViewById(R.id.color_black);
		color_blue = findViewById(R.id.color_blue);
		color_green = findViewById(R.id.color_green);
		color_yellow = findViewById(R.id.color_yellow);
		color_orange = findViewById(R.id.color_orange);

		seekBar = (SeekBar) findViewById(R.id.seekbar);

		iv = (ImageView) findViewById(R.id.iv);

		color_red.setOnClickListener(this);
		color_black.setOnClickListener(this);
		color_blue.setOnClickListener(this);
		color_orange.setOnClickListener(this);
		color_yellow.setOnClickListener(this);
		color_green.setOnClickListener(this);

		seekBar.setOnSeekBarChangeListener(this);

		iv.setOnTouchListener(this);

		// 准备画纸、画布、画笔
		bitmap = Bitmap.createBitmap(320, 320, Config.ARGB_8888);
		canvas = new Canvas(bitmap);
		paint = new Paint();

	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.color_red:
			paint.setColor(Color.RED);
			break;
		case R.id.color_black:
			paint.setColor(Color.BLACK);
			break;
		case R.id.color_blue:
			paint.setColor(Color.BLUE);
			break;
		case R.id.color_yellow:
			paint.setColor(Color.YELLOW);
			break;
		case R.id.color_green:
			paint.setColor(Color.GREEN);
			break;
		case R.id.color_orange:
			paint.setColor(0x0ACAFF);
			break;
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// 改变画笔的粗细
		int progress = seekBar.getProgress();
		paint.setStrokeWidth(10 * progress / 100f);
	}

	float startX;
	float startY;

	// 触摸的时候需要绘制图像并且显示出来
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// 触摸的时候需要绘制图像，并且显示
		// 1、手指按下
		// 2、手指移动
		// 3、手指抬起
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:// 1次
			// 手指按下
			startX = event.getX();
			startY = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:// 0-多次
			// 手指移动
			float stopX = event.getX();
			float stopY = event.getY();
			canvas.drawLine(startX, startY, stopX, stopY, paint);
			// 画纸上面有数据了
			iv.setImageBitmap(bitmap);

			// 更新起始点
			startX = stopX;
			startY = stopY;
			break;
		case MotionEvent.ACTION_UP:// 1次
			// 手指抬起

			break;
		default:
			break;
		}
		// 消费事件
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main,menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	//菜单按钮的点击事件
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId = item.getItemId();
		
		switch(itemId){
		case R.id.bt_save:
			//将bitmap存储到本地
			File file=new File("/mnt/sdcard/"+System.currentTimeMillis()+".jpg");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
				//将图片压缩到流中
				bitmap.compress(CompressFormat.JPEG, 100, fos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(fos!=null){
					try {
						fos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				fos=null;
			}
			
			//模拟sdcard的挂载
			Intent intent=new Intent(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);
			
			break;
		}
	
		return super.onOptionsItemSelected(item);
	}
}
