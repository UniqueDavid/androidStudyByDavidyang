package com.example.minismshelper;

import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.*;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import com.example.minismshelper.daomain.Contacter;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContacterActivity extends Activity {
	private TextView tv_contactername,tv_contacternumber;
	 /**联系人显示名称**/  
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;  
      
    /**电话号码**/  
    private static final int PHONES_NUMBER_INDEX = 1;  
      
	//获取联系人中的字段
	 private static final String[] PHONES_PROJECTION = new String[] {  
		        Phone.DISPLAY_NAME, Phone.NUMBER };  
	private ListView lv_contacter;
	//联系人的号码
	private List<Contacter> contacterLists = new ArrayList<Contacter>();  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacter);
		
		lv_contacter=(ListView) findViewById(R.id.lv_contacter);
		contacterLists = getContacterLists();
		
		lv_contacter.setAdapter(new MyAdapater(this,R.layout.contacter_item,contacterLists));
		
		lv_contacter.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent=new Intent();
				intent.putExtra("number",contacterLists.get(arg2).getNumber());
				intent.putExtra("name", contacterLists.get(arg2).getName());
				setResult(1,intent);
				finish();
			}
		});
		
	}
	//获得手机联系人信息
	private List<Contacter> getContacterLists() {
		//先说说 Phone.CONTENT_URI，获取联系人的时候需要去这个url中去找数据 。
		//它所指向的其实是“content:// com.Android.contacts/data/phones”。
		//这个url 对应着contacts表 和   raw_contacts表 以及 data表 所以说我们的数据都是从这三个表中获取的。
		ContentResolver resolver=getContentResolver();
		//获得了一个指定信息的联系人
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
		if(phoneCursor!=null){
			while(phoneCursor.moveToNext()){
				
				//先拿到联系人姓名
				String name = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);
				
				if(TextUtils.isEmpty(name)) {
					continue;
				}
					//拿到手机号码
					String number = phoneCursor.getString(PHONES_NUMBER_INDEX);
					Contacter contacter = new Contacter(name,number);
					contacterLists.add(contacter);
				}
			}
		return contacterLists;
			
		}
	class MyAdapater extends ArrayAdapter<Contacter>{

		public MyAdapater(ContacterActivity context, int contacterItem, List<Contacter> contacterLists) {
			// TODO Auto-generated constructor stub
			super(context, contacterItem, contacterLists);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			if(convertView==null){
				v=View.inflate(ContacterActivity.this,R.layout.contacter_item, null);
			}else
			{
				v=convertView;
			}
			tv_contactername=(TextView) v.findViewById(R.id.tv_contactername);
			tv_contacternumber=(TextView) v.findViewById(R.id.tv_contacternumber);
			
			tv_contactername.setText(contacterLists.get(position).getName());
			tv_contacternumber.setText(contacterLists.get(position).getNumber());
			return v;
		}
		
		
	}
}

	

