package com.yz.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import com.yz.domain.NewsItem;

import android.util.Xml;

public class NewsService {


	public static List<NewsItem> getAllNewsItem(final String path) {
		final List<NewsItem> items = new ArrayList<NewsItem>();
		new Thread() {
			public void run() {

				try {
					URL url = new URL(path);

					HttpURLConnection conn = (HttpURLConnection) url.openConnection();

					// 设置超时
					conn.setConnectTimeout(5000);
					// 设置请求方式
					conn.setRequestMethod("GET");
					// 获得响应码
					int code = conn.getResponseCode();

					if (code == 200) {
						InputStream is = conn.getInputStream();
						// String data = StreamTool.decodeStream(is);
						XmlPullParser parser = Xml.newPullParser();
						/*
						 * 按照面向对象思想，将数据封装到javabean中--NewsItem
						 * 
						 */
						parser.setInput(is,"utf-8");
						// 开始解析,基于事件驱动
						int eventType = parser.getEventType();
						NewsItem item=null;

						while (eventType != XmlPullParser.END_DOCUMENT) {
							if (eventType == XmlPullParser.START_TAG) {
								String tag=parser.getName();
								if (tag.equals("item")) {
									//此处如果在外面直接new一个对象，那么所有的item都会是同一个消息！！！
									item = new NewsItem();
								}else if(tag.equals("title")){
									item.setTitle(parser.nextText());
								}else if(tag.equals("link")){
									item.setLink(parser.nextText());
								}else if(tag.equals("author")){
									
									parser.nextText();
								}
								else if(tag.equals("category")){
									parser.nextText();
								}else if(tag.equals("pubDate")){
								
									parser.nextText();
								}else if(tag.equals("comments")){
								
									parser.nextText();
								}else if(tag.equals("description")){
									
									item.setDescription(parser.nextText());
								}
							}else if(eventType == XmlPullParser.END_TAG){
								if(item!=null)
								{
									items.add(item);
								}
							}
							eventType = parser.next();
				
						}

					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			};
		}.start();
		return items;
	}

}
