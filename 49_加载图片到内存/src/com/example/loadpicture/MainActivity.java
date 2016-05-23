package com.example.loadpicture;

import android.os.Bundle;
import android.util.DisplayMetrics;

import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.ExifInterface;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv=(ImageView) findViewById(R.id.iv);
	}

	public void loadBitmap(View v) throws IOException{
		String path="/mnt/sdcard/img_big_1.jpg";
		
		//通过手机屏幕的宽高和图片的宽高来计算采样率
		
		//1、屏幕的宽高
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		int screenWidth=metrics.widthPixels;
		int screenHeight=metrics.heightPixels;
		
		
		//2、图片的宽高
		ExifInterface exif=new ExifInterface(path);
		int picWidth = exif.getAttributeInt(ExifInterface.TAG_IMAGE_WIDTH, 0);
		int picHeight= exif.getAttributeInt(ExifInterface.TAG_IMAGE_LENGTH, 0);
		
		//用图片的宽度/屏幕的宽度
		int widthSample=(int)(picWidth*1f/screenWidth+0.5f);//四舍五入
		int heightSample=(int)(picHeight*1f/screenHeight+0.5f);
		
		
		int sample=(int)Math.sqrt((widthSample*widthSample+heightSample*heightSample)+0.5f);
		
		
		Options opts=new Options();
		opts.inSampleSize=sample;
		
		//加载图片
		Bitmap bitmap = BitmapFactory.decodeFile(path,opts);
		
		iv.setImageBitmap(bitmap);
	}

}
