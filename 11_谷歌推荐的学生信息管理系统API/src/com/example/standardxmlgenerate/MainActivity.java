package com.example.standardxmlgenerate;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.xmlpull.v1.XmlSerializer;

import com.example.standardxmlgenerate.R;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_studentnumber;
	private EditText ed_studentname;
	private RadioGroup rg;
	private File file;
	private StringBuilder sb=null;
	private ImageView iv;
	AnimationDrawable logoAnimation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//初始化控件
		ed_studentname=(EditText) findViewById(R.id.studentname);
		ed_studentnumber=(EditText) findViewById(R.id.studentnumber);
		rg=(RadioGroup) findViewById(R.id.radiogroup);
		
		
		iv = (ImageView) findViewById(R.id.iv);
		iv.setBackgroundResource(R.drawable.logo);
		//获得设置的背景
		logoAnimation=(AnimationDrawable) iv.getBackground();
		
		logoAnimation.start();
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
		
		if(file==null){
			file = new File(getFilesDir(),studentname+".xml");
			try {
				OutputStream os=new FileOutputStream(file);
				//专门生成xml文件的序列化器
				generateXml(studentname, studentnumber, sex, os);
				Toast.makeText(this, "保存"+studentname+"的信息成功", 0).show();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "保存"+studentname+"的信息失败", 0).show();
			}
		}else{
			try {
				 FileOutputStream fos = openFileOutput(studentname+".xml",0);
				 generateXml(studentname, studentnumber, sex, fos);
				Toast.makeText(this, "保存"+studentname+"的信息成功", 0).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "保存"+studentname+"的信息失败", 0).show();
			}
		}
	}

	/**
	 * @param studentname
	 * @param studentnumber
	 * @param sex
	 * @param os
	 * @throws IOException
	 */
	public void generateXml(String studentname, String studentnumber, String sex, OutputStream os){
		XmlSerializer serializer = Xml.newSerializer();
		try {
			serializer.setOutput(os,"utf-8");
			serializer.startDocument("utf-8", true);
			
			serializer.startTag(null,"student");
			//设置文本的学生姓名
			serializer.startTag(null,"name");
			serializer.text(studentname.trim());
			serializer.endTag(null,"name");
			//设置学号
			serializer.startTag(null, "number");
			serializer.text(studentnumber.trim());
			serializer.endTag(null, "number");
			//设置性别
			serializer.startTag(null,"sex");
			serializer.text(sex.trim());
			serializer.endTag(null,"sex");
			
			serializer.endTag(null,"student");
			
			serializer.endDocument();
			
			os.close();
			Toast.makeText(this, "保存"+studentname+"的信息成功", 0).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "保存"+studentname+"的信息失败", 0).show();
		}
		
	}

}
