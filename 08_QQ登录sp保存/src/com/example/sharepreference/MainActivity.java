package com.example.sharepreference;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Formatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;

import com.example.sharepreference.R;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	SharedPreferences sp;
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
		
		sp = getSharedPreferences("config",0);
		
		//如果找到了number值就返回number值，否则返回后面的默认值
		String numberValue = sp.getString("number","");
		String passwordValue = sp.getString("password", "");
		
		qqnumber.setText(numberValue);
		qqpassword.setText(passwordValue);
		
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
			//将数据保存的时候格式是xml
			try{
				sp = getSharedPreferences("config",0);
				//拿到一个编辑器
				Editor editor = sp.edit();
				
				editor.putString("number", number);
				editor.putString("password", password);
				
				editor.commit();
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
