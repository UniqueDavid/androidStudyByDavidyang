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

					// ���ó�ʱ
					conn.setConnectTimeout(5000);
					// ��������ʽ
					conn.setRequestMethod("GET");
					// �����Ӧ��
					int code = conn.getResponseCode();

					if (code == 200) {
						InputStream is = conn.getInputStream();
						// String data = StreamTool.decodeStream(is);
						XmlPullParser parser = Xml.newPullParser();
						/*
						 * �����������˼�룬�����ݷ�װ��javabean��--NewsItem
						 * 
						 */
						parser.setInput(is,"utf-8");
						// ��ʼ����,�����¼�����
						int eventType = parser.getEventType();
						NewsItem item=null;

						while (eventType != XmlPullParser.END_DOCUMENT) {
							if (eventType == XmlPullParser.START_TAG) {
								String tag=parser.getName();
								if (tag.equals("item")) {
									//�˴����������ֱ��newһ��������ô���е�item������ͬһ����Ϣ������
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
