package com.example.fragment;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	FragmentManager manager;
	FragmentTransaction transaction;
	SoundFragment sf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// �õ�һ��fragment��manager����
		manager = getFragmentManager();

		// ����,��ֹ����
		transaction = manager.beginTransaction();

		// fragment����ֱ��new���������Ҳ���Ҫȥ�嵥�ļ���ע��
		sf = new SoundFragment();
		
		transaction.replace(R.id.container, sf);
		transaction.commit();
	}

	@SuppressLint("NewApi")
	public void sound(View v) {
		transaction = manager.beginTransaction();
		// ��ʾ��soundFragmentȥ�滻֮ǰ��container
		transaction.replace(R.id.container, sf);

		// �ύ����
		transaction.commit();
	}

	public void show(View v) {

		transaction = manager.beginTransaction();
		// fragment����ֱ��new���������Ҳ���Ҫȥ�嵥�ļ���ע��
		ShowFragment shf = new ShowFragment();

		// ��ʾ��soundFragmentȥ�滻֮ǰ��container
		transaction.replace(R.id.container, shf);

		// �ύ����
		transaction.commit();
	}

	public void storage(View v) {
		transaction = manager.beginTransaction();
		// fragment����ֱ��new���������Ҳ���Ҫȥ�嵥�ļ���ע��
		StorageFragment stf = new StorageFragment();

		// ��ʾ��soundFragmentȥ�滻֮ǰ��container
		transaction.replace(R.id.container, stf);

		// �ύ����
		transaction.commit();
	}

}
