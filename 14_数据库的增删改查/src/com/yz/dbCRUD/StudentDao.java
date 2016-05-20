package com.yz.dbCRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/*
 * ��ɾ�Ĳ�������API��
 * ��һ�ף������ã�
 * ��ɾ�ģ�db.execSQL()
 * ��ѯ:db.rawQuery()
 * 
 * 
 * �ڶ��ף��ȸ��Ƽ�ʹ��
 * db.insert()
 * db.delete()
 * db.update()
 * db.query()
 * 
 * 
 * 	ʵ�ʿ���������:���ݿ��õĶ಻�ࣿ������
 * 
 * �ṩ�������ݵķ�ʽ�ܶ�
 * 
 * 	�����ķ�ʽ��
 * 	1��Ӧ���ڲ���˽���ļ������棺/data/data/com.yz.example/caches����files
 * 		ֱ��������������Ϳ�����
 * 
 * 	2��sharedPreference:/data/data/com.yz.example/share_prefs
 * 
 * 	3����sd����������:/mnt/sdcard
 * 
 * 	4�����浽���ݿ�:sqlite���ݿ�
 */
public class StudentDao{
	MySqliteHelper helper=null;
	SQLiteDatabase db =null;
	
	public StudentDao(Context context){
		helper = new MySqliteHelper(context);
		db= helper.getWritableDatabase();
	}
	public void Add(Student st){
//		helper.Add(db,st);
		//table:����ı�
		//nullColumnHack:����ָ���ļ��е�ֵ�������ʱ�����null
		//values:�ڲ���һ��map�Ľṹ
		ContentValues values=new ContentValues();
		values.put("number",st.getNumber());
		values.put("name",st.getName());
		values.put("sex",st.getSex());
		db.insert("student",null ,values);
	}
	public void Delete(String name){
//		helper.Delete(db,number);
		db.delete("student","name=?",new String[]{name});
	}
	public void Update(String name,String sex,int number){
//		helper.Update(db, name, sex, number);
		ContentValues values=new ContentValues();
		values.put("number",number);
		values.put("sex",sex);
		db.update("student", values,"name=?", new String[]{name});
	}
	public void Query(String name){
//		helper.Find(db,name);
		//distinct:�����õ�
		db.query("student",new String[]{"number","name","sex"},"name=?", new String[]{name}, null, null, null);
	}
}
