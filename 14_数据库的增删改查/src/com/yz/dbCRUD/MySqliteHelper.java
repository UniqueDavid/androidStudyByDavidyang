package com.yz.dbCRUD;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper {

	public MySqliteHelper(Context context) {
		super(context,"student.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table student(number integer primary key autoincrement,name varchar(30),sex varchar(10))");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
	
	public void Add(SQLiteDatabase db,Student st){
		db.execSQL("insert into student values(?,?,?)", new Object[]{st.getNumber(),st.getName(),st.getSex()});
	}
	public void Delete(SQLiteDatabase db,int number){
		db.execSQL("delete from student where number=?",new Object[]{number});
	}
	public void Update(SQLiteDatabase db,String name,String sex,int number){
		db.execSQL("update student set name=?,sex=? where number=?", new Object[]{name,sex,number});
	}
	public Student Find(SQLiteDatabase db,String name){
		Student st=new Student();
		Cursor cursor = db.rawQuery("select * from student where name=?" , new String[]{name});
		if(cursor.moveToNext()){
			int studentNumber=cursor.getInt(cursor.getColumnIndex("id"));
			String studentName=cursor.getString(cursor.getColumnIndex("name"));
			String studentSex=cursor.getString(cursor.getColumnIndex("sex"));
			
			st.setNumber(studentNumber);
			st.setName(studentName);
			st.setSex(studentSex);
			return st;
		}
		cursor.close();
		return null;
	}

}
