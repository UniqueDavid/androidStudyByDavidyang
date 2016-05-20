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
		
		//m:������Ҫ�󶨵���Ļ������
		//v:����listview
		//c:����MyAdapter
	}

	//ListAdapter---simplexxx,Basexxx
	class MyAdapter extends BaseAdapter{

		//��������ʼ�����õģ��������㵽���ж��ٸ�item��Ҫ��ʾ��listview����
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
		
		//ÿ��item��Ҫ��ʾ��lv�ϵ�ʱ��ᱻ���õ��ķ��� 
		//˭�����أ���----����androidϵͳȥ���ã���������õ�
		//position:��ǰ��item�ǳ��ڵڼ���λ�ø����ݽ�����
		//convertView�������Ż���
		//parent:��ǰ�ĸ��ؼ�
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView v;
			//�����Ż�,����������Ż�����ᵼ��new���������Ӷ�ʹ�ڴ����
			if(convertView==null){
				v=new TextView(MainActivity.this);
     		}else{
				v=(TextView) convertView;
   			}
			
			//v=new TextView(MainActivity.this);
			v.setText("���ǵ�"+position+"item");
			
			return v;
		}

	}
}
