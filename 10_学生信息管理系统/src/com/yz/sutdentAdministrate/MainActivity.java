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
		
		//��ʼ���ؼ�
		ed_studentname=(EditText) findViewById(R.id.studentname);
		ed_studentnumber=(EditText) findViewById(R.id.studentnumber);
		rg=(RadioGroup) findViewById(R.id.radiogroup);
	}

	public void save(View v){
		String studentname = ed_studentname.getText().toString().trim();
		String studentnumber = ed_studentnumber.getText().toString().trim();
	
		if(TextUtils.isEmpty(studentname)||TextUtils.isEmpty(studentnumber))
		{
			Toast.makeText(this, "�û�������ѧ�Ų���Ϊ��", 0).show();
			return;
		}
		
		//���ѧ�����Ա�
		int id = rg.getCheckedRadioButtonId();
		
		String sex="��";
		if(id==R.id.radiomale){
			sex="��";
		}else if(id==R.id.radiofemale){
			sex="Ů";
		}else if(id==R.id.radiounknown){
			sex="����";
		}
		
		//�ߵ����ѧ������Ϣ�����˽��䱣������
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
				Toast.makeText(this, "����"+studentname+"����Ϣ�ɹ�", 0).show();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "����"+studentname+"����Ϣʧ��", 0).show();
			}
		}else{
			try {
				 FileOutputStream fos = openFileOutput(studentname+".xml",0);
				 fos.write(sb.toString().getBytes());
				 fos.close();
				Toast.makeText(this, "����"+studentname+"����Ϣ�ɹ�", 0).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "����"+studentname+"����Ϣʧ��", 0).show();
			}
		}
	}

}
