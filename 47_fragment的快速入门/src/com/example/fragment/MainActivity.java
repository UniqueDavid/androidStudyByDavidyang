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
		//fragment可以直接new出来，并且不需要去清单文件中注册
		SoundFragment sf=new SoundFragment();
		
		//拿到一个fragment的manager对象
		FragmentManager manager = getFragmentManager();
		
		//事务,防止花屏
		FragmentTransaction transaction = manager.beginTransaction();
	    //表示用soundFragment去替换之前的container
		transaction.replace(R.id.container, sf);
		
		//提交事务
		transaction.commit();
	}

	
	public void show(View v) {
		
		//fragment可以直接new出来，并且不需要去清单文件中注册
				ShowFragment shf=new ShowFragment();
				
				//拿到一个fragment的manager对象
				FragmentManager manager = getFragmentManager();
				
				//事务,防止花屏
				FragmentTransaction transaction = manager.beginTransaction();
			    //表示用soundFragment去替换之前的container
				transaction.replace(R.id.container, shf);
				
				//提交事务
				transaction.commit();
	}

	public void storage(View v) {
		//fragment可以直接new出来，并且不需要去清单文件中注册
		StorageFragment stf=new StorageFragment();
		
		//拿到一个fragment的manager对象
		FragmentManager manager = getFragmentManager();
		
		//事务,防止花屏
		FragmentTransaction transaction = manager.beginTransaction();
	    //表示用soundFragment去替换之前的container
		transaction.replace(R.id.container, stf);
		
		//提交事务
		transaction.commit();
	}

}
