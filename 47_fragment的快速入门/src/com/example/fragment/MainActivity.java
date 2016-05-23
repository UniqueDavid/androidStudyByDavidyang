package com.example.fragment;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
@SuppressLint("NewApi")
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@SuppressLint("NewApi")
	public void sound(View v) {
		//fragment����ֱ��new���������Ҳ���Ҫȥ�嵥�ļ���ע��
		SoundFragment sf=new SoundFragment();
		
		//�õ�һ��fragment��manager����
		FragmentManager manager = getFragmentManager();
		
		//����,��ֹ����
		FragmentTransaction transaction = manager.beginTransaction();
	    //��ʾ��soundFragmentȥ�滻֮ǰ��container
		transaction.replace(R.id.container, sf);
		
		//�ύ����
		transaction.commit();
	}

	
	public void show(View v) {
		
		//fragment����ֱ��new���������Ҳ���Ҫȥ�嵥�ļ���ע��
				ShowFragment shf=new ShowFragment();
				
				//�õ�һ��fragment��manager����
				FragmentManager manager = getFragmentManager();
				
				//����,��ֹ����
				FragmentTransaction transaction = manager.beginTransaction();
			    //��ʾ��soundFragmentȥ�滻֮ǰ��container
				transaction.replace(R.id.container, shf);
				
				//�ύ����
				transaction.commit();
	}

	public void storage(View v) {
		//fragment����ֱ��new���������Ҳ���Ҫȥ�嵥�ļ���ע��
		StorageFragment stf=new StorageFragment();
		
		//�õ�һ��fragment��manager����
		FragmentManager manager = getFragmentManager();
		
		//����,��ֹ����
		FragmentTransaction transaction = manager.beginTransaction();
	    //��ʾ��soundFragmentȥ�滻֮ǰ��container
		transaction.replace(R.id.container, stf);
		
		//�ύ����
		transaction.commit();
	}

}
