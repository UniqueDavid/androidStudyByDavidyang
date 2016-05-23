package com.example.xutilsdownload;

import android.os.Bundle;
import android.os.Environment;

import java.io.File;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText et_downloadurl;
	private TextView tv_status;
	public static String path="http://192.168.1.100:8080/file.txt";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化控件
		et_downloadurl=(EditText) findViewById(R.id.et_downloadurl);
		tv_status=(TextView) findViewById(R.id.tv_status);
	}
	public void download(View v){
		path=et_downloadurl.getText().toString().trim();
		
		HttpUtils http=new HttpUtils();
		http.download(path,Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"1.exe", true,new RequestCallBack<File>() {
			
			@Override
			public void onSuccess(ResponseInfo<File> responseInfo) {
			System.out.println("下载成功");
			}
			
			@Override
			public void onFailure(HttpException error, String msg) {
				System.out.println("下载失败");
			}
		});
		
	}

}
