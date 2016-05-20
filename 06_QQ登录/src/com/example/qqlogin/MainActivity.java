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
		
		//��ʼ���ؼ�
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
		//����������д��ֵ
		String number = qqnumber.getText().toString().trim();
		String password = qqpassword.getText().toString().trim();
		
		//�ж��Ƿ�������number�Լ�password��ֵ
		if(TextUtils.isEmpty(number)||TextUtils.isEmpty(password)){
			//���û���ʾ��������������Ϊ��
			Toast.makeText(this,"������QQ���������",0).show();
			return;
		}
		//�ж��Ƿ�ѡ��checkbox�������ѡ�򱣴������ֵ
		boolean isChecked = checkBox.isChecked();
		
		if(isChecked){
			//˵����ѡ��
			//�������û�����һ��toast(��˾)
			//context:������
			//text:��Ҫ���û���ʾ����Ϣ
			//duration:��Ϣ������ʱ��
			Toast.makeText(this,"��ѡ��" ,0).show();
			
			
			//�ڹ�ѡ��checkBox��ʱ�򣬽����ݱ�������
				
			try {
				//ϸ��һ:
				//��ʱ��sdcard�Ǵ��ڵ�������δ����״̬��Ϊ������û��ĸ��ܣ������ڽ������ݱ���֮ǰ����ǰ��ȡsdcard״̬��ֻ��
				//��sdcard���ڹ���״̬�£��Ż�ȥдsdcard������
				String status = Environment.getExternalStorageState();
				//�����status��̬�ķ���sd����״̬
				
				if(!Environment.MEDIA_MOUNTED.equals(status)){
					//��ʾsd��δ����
					Toast.makeText(this,"����sd����״̬" ,0).show();
					return;
				}
				
				//ϸ�ڶ�:
				//���ؿ��õĿ��пռ��С---in bytes
				long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
				
				String formatFileSize = Formatter.formatFileSize(this,freeSpace);
				
				Toast.makeText(this, formatFileSize, 0).show();
				//���ݲ�ͬ���󣬴洢��CacheDir����FileDir��
//				File file=new File(this.getCacheDir(),"info.txt");
//				File file=new File("/mnt/sdcard/info.txt");
				File file=new File(Environment.getExternalStorageDirectory(),"info.txt");
				OutputStream out =new FileOutputStream(file);
				
				String value=number+"#yz"+password;
				out.write(value.getBytes());
				out.close();
				Toast.makeText(this, "����ɹ�", 0).show();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(this, "���治�ɹ�", 0).show();
			}
			
			
			
		}else{
			//˵��û�й�ѡ
			Toast.makeText(this,"û�й�ѡ" ,0).show();
		}
		
	}

}
