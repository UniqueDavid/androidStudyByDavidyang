package com.example.getContacterInfo;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import com.example.contactsutils.ContactUtils;
import com.example.domain.ContactInfo;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private List<ContactInfo> list;
	private ListView lv;
	private MyArrayAdapater myadapater;
	private TextView tv_name, tv_number, tv_email;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void getContacterInfo(View v) {
		if(list!=null)
		{
			list.clear();
		}
		list = ContactUtils.displayContacts(this);
		System.out.println(list.size());
		lv = (ListView) findViewById(R.id.lv);
		if (myadapater == null) {
			myadapater = new MyArrayAdapater(MainActivity.this, R.layout.item, list);
			lv.setAdapter(myadapater);
		} else {
			myadapater.notifyDataSetChanged();
		}
	}

	class MyArrayAdapater extends ArrayAdapter<ContactInfo> {
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			if (convertView == null) {
				v = View.inflate(MainActivity.this, R.layout.item, null);
			} else {
				v = convertView;
			}
			tv_name = (TextView) v.findViewById(R.id.tv_name);
			tv_number = (TextView) v.findViewById(R.id.tv_number);
			tv_email = (TextView) v.findViewById(R.id.tv_email);

			tv_name.setText(list.get(position).getName());
			tv_number.setText(list.get(position).getNumber());
			tv_email.setText(list.get(position).getEmail());
			return v;
		}

		public MyArrayAdapater(Context context, int textViewResourceId, List<ContactInfo> objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}

	}
}
