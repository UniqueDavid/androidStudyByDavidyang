package com.yz.sutdentAdministrate;

import android.os.Bundle;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_studentnumber;
	private EditText ed_studentname;
	private RadioGroup rg;
	private File file;
	private StringBuilder sb=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化控件
		ed_studentname=(EditText) findViewById(R.id.studentname);
		ed_studentnumber=(EditText) findViewById(R.id.studentnumber);
		rg=(RadioGroup) findViewById(R.id.radiogroup);
	}

	public void save(View v){
		String studentname = ed_studentname.getText().toString().trim();
		String studentnumber = ed_studentnumber.getText().toString().trim();
	
		if(TextUtils.isEmpty(studentname)||TextUtils.isEmpty(studentnumber))
		{
			Toast.makeText(this, "用户名或者学号不能为空", 0).show();
			return;
		}
		
		//获得学生的性别
		int id = rg.getCheckedRadioButtonId();
		
		String sex="男";
		if(id==R.id.radiomale){
			sex="男";
		}else if(id==R.id.radiofemale){
			sex="女";
		}else if(id==R.id.radiounknown){
			sex="保密";
		}
		
		//走到这里，学生的信息都有了将其保存下来
		/*
		 <?xml version="1.0" encoding="utf-8"?>
		 <student>
		 		<name></name>
		 		<number></number>
		 		<sex></sex>
		 </student>
		 */
		sb = new StringBuilder();
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>");
		sb.append("<student>");
		sb.append("<name>");
		sb.append(studentname);
		sb.append("</name>");
		sb.append("<number>");
		sb.append(studentnumber);
		sb.append("</number>");
		sb.append("<sex>");
		sb.append(sex);
		sb.append("</sex>");
		sb.append("</student>");
		
		if(file==null){
			file = new File(getFilesDir(),studentname+".xml");
			try {
				OutputStream os=new FileOutputStream(file);
				os.write(sb.toString().getBytes());
				os.close();
				Toast.makeText(this, "保存"+studentname+"的信息成功", 0).show();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "保存"+studentname+"的信息失败", 0).show();
			}
		}else{
			try {
				 FileOutputStream fos = openFileOutput(studentname+".xml",0);
				 fos.write(sb.toString().getBytes());
				 fos.close();
				Toast.makeText(this, "保存"+studentname+"的信息成功", 0).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "保存"+studentname+"的信息失败", 0).show();
			}
		}
	}

}
