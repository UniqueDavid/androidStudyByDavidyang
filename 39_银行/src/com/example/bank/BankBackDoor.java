package com.example.bank;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class BankBackDoor extends ContentProvider{

	/*
	 * ���ų������һ�����������г������ʱ������Ƿ�����㹻��Ȩ��
	 *	UriMatch�������
	 */
	
	private static final int SUCCESS = 1;
	//�����UriMatch����һ������
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
		System.out.println("ʹ�ú��ų����޸������ݿ��е�����");
		}else{
			throw new RuntimeException("���Ų��ԣ������ӣ�����");
		}
		//�������֪ͨ�ϼ����ݷ����˱仯��
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
