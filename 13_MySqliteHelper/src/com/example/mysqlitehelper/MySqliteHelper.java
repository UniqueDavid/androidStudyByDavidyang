package com.example.mysqlitehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper{

	public MySqliteHelper(Context context) {
		//context:应用上下文
		//name:数据库的名称
		//factory:创建游标的工厂
		//version:数据库的版本
		super(context,"mydb1", null, 1);
		// TODO Auto-generated constructor stub
	}

	//在数据首次被创建的时候会调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("onCreate执行了..");
		db.execSQL("create table students(_id integer primary key autoincrement,name varchar(30),sex varchar(10))");
	}

	//在数据库升级的时候会调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("onUpgrade执行了..");
	}

}
