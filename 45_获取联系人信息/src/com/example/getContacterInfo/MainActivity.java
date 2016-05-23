package com.example.getContacterInfo;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void getContacterInfo(View v){
		ContentResolver resolver=getContentResolver();
		
		Uri contacts_uri=Uri.parse("content://com.android.contacts/raw_contacts");
		Uri data_uri=Uri.parse("content://com.android.contacts/data");
		
		Cursor contact_cursor = resolver.query(contacts_uri,new String[]{"contact_id"},null, null, null);
	
		while(contact_cursor.moveToNext()){
			String id = contact_cursor.getString(0);
			System.out.println("id:"+id);
			//根据id去查询data,只要是id相同的data
			Cursor data_cursor = resolver.query(data_uri, new String[]{"data1","mimetype"},"raw_contact_id=?",new String[]{id},null);
			
			while(data_cursor.moveToNext()){
				
				String data = data_cursor.getString(0);
				String mimetype=data_cursor.getString(1);
				
				
				System.out.println(mimetype+":"+data);
			}
			data_cursor.close();
			
		}
	}
}
