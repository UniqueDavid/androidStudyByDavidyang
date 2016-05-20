package com.yz.mininews;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import com.yz.domain.NewsItem;
import com.yz.service.NewsService;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	/*
	 * �������ſͻ��˵ı�д�����ע��һ�������������Ҳ����������ڣ���
	 * 1�����Ĵ�����XmlpullParser����һ��������setinput�Ժ󣬱�����while�ڲ�����javabean����½�������ᱨ�����һᵼ�����е�
	 * ��Ŀ�������ͬ���ݣ�ͬ��������һ�εõ���itemsҲ���������Ӧ��λ���½�
	 * 2���ɰ汾��ģ��������listview���ܻ���ֲ����ݵ����⣬��˽���ʹ�ø߰汾����
	 * 3�����������xml��ַ������õ��Ǳ��ص¶������Ҫ�ñ���ip�����ǻز��ַ127.0.0.1����localhost
	 * 4��Xml���������У����eventtype�Լ�parser.next()�ز�����
	 * 5������Ԫ���е����ݵĻ��ʹ��nexttext����Ȼ���Էֿ�ʹ��
	 */
	private List<NewsItem> items=new ArrayList<NewsItem>();
	private String path = "http://news.qq.com/newsgn/rss_newsgn.xml";
	private ListView lv;
	private MyAdapater myadpater;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lv=(ListView) findViewById(R.id.lv);
		//���ʷ���������������ʾ����
		items = NewsService.getAllNewsItem(path);
		
		loadData();
		
	}
	private void loadData() {
		
		if(myadpater==null){
			myadpater=new MyAdapater();
			lv.setAdapter(myadpater);
		}else{
			myadpater.notifyDataSetChanged();
		}
		
		
	}
	class MyAdapater extends BaseAdapter{
		View v;
		@Override
		public int getCount() {
			return items.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				v=View.inflate(MainActivity.this,R.layout.item,null);
			}else{
				v=convertView;
			}
			//�õ���ǰλ�õ�newsItem����
			NewsItem newsItem = items.get(position);
			
			//�ҵ�item�е�ÿ���ؼ�
			TextView tv_title=(TextView) v.findViewById(R.id.tv_title);
			TextView tv_link=(TextView) v.findViewById(R.id.tv_link);
			TextView tv_description=(TextView) v.findViewById(R.id.tv_description);
			
			tv_title.setText(newsItem.getTitle());
			tv_link.setText(newsItem.getLink());
			tv_description.setText(newsItem.getDescription());
			
			return v;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}
		
	}
}
