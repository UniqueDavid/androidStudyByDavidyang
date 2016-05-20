package com.example.standardxmlgenerate;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import com.example.standardxmlgenerate.R;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText ed_studentnumber;
	private EditText ed_studentname;
	private RadioGroup rg;
	private TextView result;
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
		result=(TextView) findViewById(R.id.result);
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
	
	public void find(View v) throws IOException{
		InputStream is=new FileInputStream(file);
		try {
			XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
			//设置名称空间
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(is,"utf-8");
			//获得事件号码
			int eventType=xpp.getEventType();
			
			while(eventType!=xpp.END_DOCUMENT){
				//先解析第一个记录
				if(eventType==xpp.START_TAG){
					sb.append("<"+xpp.getName()+">");
				}else if(eventType==xpp.END_TAG){
					sb.append("</"+xpp.getName()+">");
				}else if(eventType==xpp.TEXT){
					sb.append(xpp.getText());
				}
				//获得下一个标记
				eventType=xpp.next();
			}
			result.setText(sb.toString());
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setText("对不起没有该同学的记录！！");
		}
		is.close();
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
