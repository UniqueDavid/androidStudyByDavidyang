package com.yz.dbCRUD;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/*
 * 增删改查有两套API了
 * 第一套（经常用）
 * 增删改：db.execSQL()
 * 查询:db.rawQuery()
 * 
 * 
 * 第二套：谷歌推荐使用
 * db.insert()
 * db.delete()
 * db.update()
 * db.query()
 * 
 * 
 * 	实际开发过程中:数据库用的多不多？？不多
 * 
 * 提供保存数据的方式很多
 * 
 * 	常见的方式：
 * 	1、应用内部的私有文件夹下面：/data/data/com.yz.example/caches或者files
 * 		直接用输入输出流就可以了
 * 
 * 	2、sharedPreference:/data/data/com.yz.example/share_prefs
 * 
 * 	3、在sd卡公共部分:/mnt/sdcard
 * 
 * 	4、保存到数据库:sqlite数据库
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
		//table:插入的表
		//nullColumnHack:用于指定哪几列的值不插入的时候调用null
		//values:内部是一个map的结构
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
		//distinct:排重用的
		db.query("student",new String[]{"number","name","sex"},"name=?", new String[]{name}, null, null, null);
	}
}
