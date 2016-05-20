package com.example.setdialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void createDialog01(View v) {
		AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("����");
		builder.setMessage("����һ��ʲô�����ˣ�����");
		
		builder.setPositiveButton("�ܺõ���",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,"��ô���ĺ��أ���", 0).show();
			}
		});
		
		builder.setNegativeButton("����",null);
		
		//���Ҫshow��
		builder.show();
	}

	public void createDialog02(View v) {
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("haha");
		final String[] items={"С��","С��","С��"};
		builder.setSingleChoiceItems(items, -1, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,items[which]+"�������", 0).show();
			}
		});
		
		builder.setNegativeButton("nono", null);
		builder.show();
		

	}

	public void createDialog03(View v) {
		AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("��ѡ");
		//���ﲻҪ����message������Ḳ�Ƕ�ѡ��
		//builder.setMessage("�����ѡ������������");
		
		final String[] items={"android","ios","javaee","php","c"};
		boolean[] checkedItems={true,true,false,false,false};
		
		builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			
				Toast.makeText(MainActivity.this,"�������"+items[which]+"λ����:"+which+"ֵ��"+isChecked, 0).show();
			}
		});
		
		builder.setNegativeButton("ȡ��",null);
		
		builder.show();
	}

	public void createDialog04(View v) {
		//�õ��ȽϺ�ʱ�Ļ��ý�����
		ProgressDialog dialog=new ProgressDialog(MainActivity.this);
		
		dialog.setTitle("������");
		dialog.setMessage("����ƴ��������....");
		
		dialog.show();
		
		

	}
}
