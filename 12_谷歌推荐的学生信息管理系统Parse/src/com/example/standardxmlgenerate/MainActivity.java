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
		
		//��ʼ���ؼ�
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
		
		if(file==null){
			file = new File(getFilesDir(),studentname+".xml");
			try {
				OutputStream os=new FileOutputStream(file);
				//ר������xml�ļ������л���
				generateXml(studentname, studentnumber, sex, os);
				Toast.makeText(this, "����"+studentname+"����Ϣ�ɹ�", 0).show();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "����"+studentname+"����Ϣʧ��", 0).show();
			}
		}else{
			try {
				 FileOutputStream fos = openFileOutput(studentname+".xml",0);
				 generateXml(studentname, studentnumber, sex, fos);
				Toast.makeText(this, "����"+studentname+"����Ϣ�ɹ�", 0).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "����"+studentname+"����Ϣʧ��", 0).show();
			}
		}
	}
	
	public void find(View v) throws IOException{
		InputStream is=new FileInputStream(file);
		try {
			XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
			//�������ƿռ�
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(is,"utf-8");
			//����¼�����
			int eventType=xpp.getEventType();
			
			while(eventType!=xpp.END_DOCUMENT){
				//�Ƚ�����һ����¼
				if(eventType==xpp.START_TAG){
					sb.append("<"+xpp.getName()+">");
				}else if(eventType==xpp.END_TAG){
					sb.append("</"+xpp.getName()+">");
				}else if(eventType==xpp.TEXT){
					sb.append(xpp.getText());
				}
				//�����һ�����
				eventType=xpp.next();
			}
			result.setText(sb.toString());
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setText("�Բ���û�и�ͬѧ�ļ�¼����");
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
			//�����ı���ѧ������
			serializer.startTag(null,"name");
			serializer.text(studentname.trim());
			serializer.endTag(null,"name");
			//����ѧ��
			serializer.startTag(null, "number");
			serializer.text(studentnumber.trim());
			serializer.endTag(null, "number");
			//�����Ա�
			serializer.startTag(null,"sex");
			serializer.text(sex.trim());
			serializer.endTag(null,"sex");
			
			serializer.endTag(null,"student");
			
			serializer.endDocument();
			
			os.close();
			Toast.makeText(this, "����"+studentname+"����Ϣ�ɹ�", 0).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "����"+studentname+"����Ϣʧ��", 0).show();
		}
		
	}

}
