package com.example.minimizepicture;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv_yuantu,iv_suofang;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_yuantu=(ImageView) findViewById(R.id.iv_yuantu);
		iv_suofang=(ImageView) findViewById(R.id.iv_suofang);
	}
	public void suofang(View v){
		String path="/mnt/sdcard/img_small_1.jpg";
		Bitmap srcBitmap=BitmapFactory.decodeFile(path);
		
		iv_yuantu.setImageBitmap(srcBitmap);
		
		//1、准备画纸
		Bitmap copyBitmap=Bitmap.createBitmap(srcBitmap.getWidth(),srcBitmap.getHeight(),srcBitmap.getConfig());
		
		//2、准备画板
		Canvas canvas=new Canvas(copyBitmap);
		
		//3、准备画笔
		Paint paint=new Paint();
		
		//4、设置比例
		Matrix matrix=new Matrix();
		matrix.setScale(0.6f, 0.6f);
		
		//5、开始画图
		canvas.drawBitmap(srcBitmap, matrix, paint);
		
		
		
		iv_suofang.setImageBitmap(copyBitmap);
		
	}

}
