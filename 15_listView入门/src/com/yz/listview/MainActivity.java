package com.yz.listview;

import android.os.Bundle;
import android.app.Activity;
import android.database.DataSetObserver;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//view
		lv = (ListView) findViewById(R.id.lv);
		
		lv.setAdapter(new MyAdapter());
		
		//m:就是你要绑定到屏幕的数据
		//v:就是listview
		//c:就是MyAdapter
	}

	//ListAdapter---simplexxx,Basexxx
	class MyAdapter extends BaseAdapter{

		//这个方法最开始被调用的，用来计算到底有多少个item需要显示在listview上面
		@Override
		public int getCount() {
			return 1000;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
		
		//每个item需要显示在lv上的时候会被调用到的方法 
		//谁调用呢？？----是由android系统去调用，不是你调用的
		//position:当前的item是出于第几个位置给传递进来了
		//convertView用于做优化的
		//parent:当前的父控件
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView v;
			//用于优化,如果不用于优化，则会导致new的组件过多从而使内存溢出
			if(convertView==null){
				v=new TextView(MainActivity.this);
     		}else{
				v=(TextView) convertView;
   			}
			
			//v=new TextView(MainActivity.this);
			v.setText("我是第"+position+"item");
			
			return v;
		}

	}
}
