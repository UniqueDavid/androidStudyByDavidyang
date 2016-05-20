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
		builder.setTitle("哈哈");
		builder.setMessage("你是一个什么样的人？？？");
		
		builder.setPositiveButton("很好的人",new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,"怎么样的好呢？？", 0).show();
			}
		});
		
		builder.setNegativeButton("坏人",null);
		
		//最后要show下
		builder.show();
	}

	public void createDialog02(View v) {
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setTitle("haha");
		final String[] items={"小红","小龙","小芳"};
		builder.setSingleChoiceItems(items, -1, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(MainActivity.this,items[which]+"被点击了", 0).show();
			}
		});
		
		builder.setNegativeButton("nono", null);
		builder.show();
		

	}

	public void createDialog03(View v) {
		AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
		builder.setTitle("多选");
		//这里不要设置message，否则会覆盖多选项
		//builder.setMessage("你可以选择多个工作方向");
		
		final String[] items={"android","ios","javaee","php","c"};
		boolean[] checkedItems={true,true,false,false,false};
		
		builder.setMultiChoiceItems(items, checkedItems, new OnMultiChoiceClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which, boolean isChecked) {
			
				Toast.makeText(MainActivity.this,"被点击了"+items[which]+"位置是:"+which+"值是"+isChecked, 0).show();
			}
		});
		
		builder.setNegativeButton("取消",null);
		
		builder.show();
	}

	public void createDialog04(View v) {
		//用到比较耗时的会用进度条
		ProgressDialog dialog=new ProgressDialog(MainActivity.this);
		
		dialog.setTitle("下载中");
		dialog.setMessage("正在拼命加载中....");
		
		dialog.show();
		
		

	}
}
