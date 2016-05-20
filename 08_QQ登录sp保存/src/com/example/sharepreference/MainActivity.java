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
		
		//��ʼ���ؼ�
		qqnumber=(EditText) findViewById(R.id.num);
		qqpassword=(EditText) findViewById(R.id.password);
		checkBox=(CheckBox) findViewById(R.id.remember);
		
		btn_login=(Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		
		sp = getSharedPreferences("config",0);
		
		//����ҵ���numberֵ�ͷ���numberֵ�����򷵻غ����Ĭ��ֵ
		String numberValue = sp.getString("number","");
		String passwordValue = sp.getString("password", "");
		
		qqnumber.setText(numberValue);
		qqpassword.setText(passwordValue);
		
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
			//�����ݱ����ʱ���ʽ��xml
			try{
				sp = getSharedPreferences("config",0);
				//�õ�һ���༭��
				Editor editor = sp.edit();
				
				editor.putString("number", number);
				editor.putString("password", password);
				
				editor.commit();
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
