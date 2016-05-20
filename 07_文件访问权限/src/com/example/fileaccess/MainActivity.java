package com.example.fileaccess;

import android.os.Bundle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	private Button btn_private;
	private Button btn_readonly;
	private Button btn_writeonly;
	private Button btn_public;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//��ʼ�����ʿؼ�
		btn_private=(Button) findViewById(R.id.btn_private);
		btn_readonly=(Button) findViewById(R.id.btn_readonly);
		btn_writeonly=(Button) findViewById(R.id.btn_writeonly);
		btn_public=(Button) findViewById(R.id.btn_public);
		
		//Ϊ�����õ���¼�
		btn_private.setOnClickListener(this);
		btn_readonly.setOnClickListener(this);
		btn_writeonly.setOnClickListener(this);
		btn_public.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_private:
			createPrivateFile();break;
		case R.id.btn_readonly:
			createReadOnlyFile();break;
		case R.id.btn_writeonly:
			createWriteOnlyFile();break;
		case R.id.btn_public:
			createPublicFile();break;
		default:break;
		}
	}
	
	//����˽���ļ������Լ�Ӧ�ó���Ŀ¼��
	private void createPrivateFile() {
		File file=new File(this.getFilesDir(),"private.txt");
		try {
			OutputStream os=new FileOutputStream(file);
			os.write("private".getBytes());
			os.close();
			Toast.makeText(this, "����˽���ļ��ɹ�", 0).show();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "����˽���ļ�ʧ��", 0).show();
		}
	}
	
	//ʹ��FileOutputStream������OpenFileOutput
	@SuppressWarnings("deprecation")
	private void createWriteOnlyFile() {
		try {
			FileOutputStream os = openFileOutput("readonly.txt",Context.MODE_WORLD_READABLE);
			os.write("readonly".getBytes());
			os.close();
			Toast.makeText(this, "����ֻ���ļ��ɹ�", 0).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "����ֻ���ļ�ʧ��", 0).show();
		}
	}
	
	private void createReadOnlyFile() {
		try {
			FileOutputStream os = openFileOutput("writeonly.txt",Context.MODE_WORLD_WRITEABLE);
			os.write("writeonly".getBytes());
			os.close();
			Toast.makeText(this, "����ֻд�ļ��ɹ�", 0).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "����ֻд�ļ�ʧ��", 0).show();
		}
	}
	private void createPublicFile() {
		try {
			FileOutputStream os = openFileOutput("public.txt",Context.MODE_WORLD_READABLE+this.MODE_WORLD_WRITEABLE);
			os.write("public".getBytes());
			os.close();
			Toast.makeText(this, "���������ļ��ɹ�", 0).show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "���������ļ�ʧ��", 0).show();
		}
	}

}
