package com.example.bank;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class BankBackDoor extends ContentProvider{

	/*
	 * 后门程序具有一个保安，在有程序访问时，检查是否具有足够的权限
	 *	UriMatch这个对象
	 */
	
	private static final int SUCCESS = 1;
	//这里的UriMatch就是一个保安
	private static UriMatcher matcher=new UriMatcher(UriMatcher.NO_MATCH);
	static{
		
		matcher.addURI("com.yz.1234","accounts", SUCCESS);
	}
	
	@Override
	public boolean onCreate() {
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		return null;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int result = matcher.match(uri);
		if(SUCCESS==result){
		System.out.println("使用后门程序修改了数据库中的数据");
		}else{
			throw new RuntimeException("暗号不对，滚犊子！！！");
		}
		//这里可以通知上级数据发生了变化！
		notify();
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}

}
