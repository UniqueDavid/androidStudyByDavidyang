package com.example.qqlogin;

import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import android.text.format.Formatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private EditText qqnumber;
	private EditText qqpassword;
	private Button btn_login;
	private CheckBox checkBox;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化控件
		qqnumber=(EditText) findViewById(R.id.num);
		qqpassword=(EditText) findViewById(R.id.password);
		checkBox=(CheckBox) findViewById(R.id.remember);
		
		btn_login=(Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		
//		File file=new File(this.getCacheDir(),"info.txt");
//		File file=new File("/mnt/sdcard/info.txt");
		File file=new File(Environment.getExternalStorageDirectory(),"info.txt");
		
		if(file.exists()&&file.length()>0){
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				//12#yz1111
				String data = br.readLine();
				String[] splitData = data.split("#yz");
				qqnumber.setText(splitData[0]);
				qqpassword.setText(splitData[1]);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public void onClick(View v) {
		//获得输入框中写入值
		String number = qqnumber.getText().toString().trim();
		String password = qqpassword.getText().toString().trim();
		
		//判断是否填入了number以及password的值
		if(TextUtils.isEmpty(number)||TextUtils.isEmpty(password)){
			//给用户提示，输入号码和密码为空
			Toast.makeText(this,"请输入QQ号码和密码",0).show();
			return;
		}
		//判断是否勾选了checkbox，如果勾选则保存上面的值
		boolean isChecked = checkBox.isChecked();
		
		if(isChecked){
			//说明勾选了
			//经常给用户弹出一个toast(土司)
			//context:上下文
			//text:你要给用户提示的信息
			//duration:消息持续的时间
			Toast.makeText(this,"勾选了" ,0).show();
			
			
			//在勾选了checkBox的时候，将数据保存起来
				
			try {
				//细节一:
				//有时候sdcard是处于弹出或者未插入状态，为了提高用户的感受，所以在进行数据保存之前会提前读取sdcard状态，只有
				//在sdcard处于挂载状态下，才会去写sdcard！！！
				String status = Environment.getExternalStorageState();
				//这里的status动态的返回sd卡的状态
				
				if(!Environment.MEDIA_MOUNTED.equals(status)){
					//表示sd卡未挂载
					Toast.makeText(this,"请检查sd卡的状态" ,0).show();
					return;
				}
				
				//细节二:
				//返回可用的空闲空间大小---in bytes
				long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
				
				String formatFileSize = Formatter.formatFileSize(this,freeSpace);
				
				Toast.makeText(this, formatFileSize, 0).show();
				//根据不同需求，存储到CacheDir或者FileDir中
//				File file=new File(this.getCacheDir(),"info.txt");
//				File file=new File("/mnt/sdcard/info.txt");
				File file=new File(Environment.getExternalStorageDirectory(),"info.txt");
				OutputStream out =new FileOutputStream(file);
				
				String value=number+"#yz"+password;
				out.write(value.getBytes());
				out.close();
				Toast.makeText(this, "保存成功", 0).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "保存不成功", 0).show();
			}
			
			
			
		}else{
			//说明没有勾选
			Toast.makeText(this,"没有勾选" ,0).show();
		}
		
	}

}
