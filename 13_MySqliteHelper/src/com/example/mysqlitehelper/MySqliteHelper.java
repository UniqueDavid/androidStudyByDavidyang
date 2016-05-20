package com.example.mysqlitehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqliteHelper extends SQLiteOpenHelper{

	public MySqliteHelper(Context context) {
		//context:Ӧ��������
		//name:���ݿ������
		//factory:�����α�Ĺ���
		//version:���ݿ�İ汾
		super(context,"mydb1", null, 1);
		// TODO Auto-generated constructor stub
	}

	//�������״α�������ʱ������
	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("onCreateִ����..");
		db.execSQL("create table students(_id integer primary key autoincrement,name varchar(30),sex varchar(10))");
	}

	//�����ݿ�������ʱ������
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("onUpgradeִ����..");
	}

}
