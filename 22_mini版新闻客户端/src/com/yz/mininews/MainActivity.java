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
	 * 对于新闻客户端的编写，务必注意一下事项，否则半天找不到错误所在！！
	 * 1、最大的错误：用XmlpullParser解析一个流，在setinput以后，必须在while内部进行javabean类的新建，否则会报错，而且会导致所有的
	 * 条目都变成相同内容，同样，对于一次得到的items也必须在相对应的位置新建
	 * 2、旧版本的模拟器对于listview可能会出现不兼容的问题，因此建议使用高版本调试
	 * 3、对于请求的xml地址，如果用的是本地德尔，务必要用本地ip而不是回测地址127.0.0.1或者localhost
	 * 4、Xml解析过程中，获得eventtype以及parser.next()必不可少
	 * 5、对于元素中的内容的获得使用nexttext，当然可以分开使用
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
		//访问服务器，把数据显示出来
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
			//拿到当前位置的newsItem对象
			NewsItem newsItem = items.get(position);
			
			//找到item中的每个控件
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
