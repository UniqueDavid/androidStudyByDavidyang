package com.example.bank;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BankDBOpenHelper extends SQLiteOpenHelper {

	//创建数据库
	public BankDBOpenHelper(Context context) {
		super(context,"bank.db", null, 1);
		// TODO Auto-generated constructor stub
	}

	//创建数据库的表
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table bank(_id integer primary key autoincrement,name varchar(30),money varchar(30))");
		}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
