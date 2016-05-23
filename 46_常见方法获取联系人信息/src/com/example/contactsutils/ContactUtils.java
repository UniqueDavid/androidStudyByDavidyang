package com.example.contactsutils;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.ContactInfo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class ContactUtils {

	public static List<ContactInfo> list = new ArrayList<ContactInfo>();

	public static List<ContactInfo> displayContacts(Context context) {
		
		list.clear();

		Uri contact_uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri data_uri = Uri.parse("content://com.android.contacts/data");

		// 获得与后门程序打交道的resolver
		ContentResolver resolver = context.getContentResolver();

		Cursor contact_cursor = resolver.query(contact_uri, new String[] { "contact_id" }, null, null, null);

		ContactInfo contact = null;
		while (contact_cursor.moveToNext()) {

			String id = contact_cursor.getString(0);
			if(id==null){
				continue;
			}
			Cursor data_cursor = resolver.query(data_uri, new String[] { "data1", "mimetype" }, "contact_id=?",
					new String[] { id }, null);

			contact = new ContactInfo();
			while (data_cursor.moveToNext()) {

				String data = data_cursor.getString(0);
				/*
				 * vnd.android.cursor.item/phone_v2:1 234-567-8899
				 * vnd.android.cursor.item/email_v2:sssssww
				 * 
				 */
				String mimetype = data_cursor.getString(1);

				if ("vnd.android.cursor.item/phone_v2".equals(mimetype)) {
					contact.setNumber(data);
				} else if ("vnd.android.cursor.item/email_v2".equals(mimetype)) {
					contact.setEmail(data);
				} else if ("vnd.android.cursor.item/name".equals(mimetype)) {
					contact.setName(data);
				}
				
			}
			data_cursor.close();
			list.add(contact);
			
		}
		contact_cursor.close();
		return list;
	}

}
