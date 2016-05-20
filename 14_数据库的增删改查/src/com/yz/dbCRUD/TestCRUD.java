package com.yz.dbCRUD;

import android.test.AndroidTestCase;

public class TestCRUD extends AndroidTestCase {
	StudentDao sdao=null; 
	
	public void testAdd(){
		sdao=new StudentDao(getContext());
		sdao.Add(new Student(123,"xiaomin","��"));
	}
	
	public void testDelete(){
		sdao=new StudentDao(getContext());
		sdao.Delete("xiaomin");
	}
	
	public void testUpdate(){
		sdao=new StudentDao(getContext());
		sdao.Update("xiaoli","Ů", 123);
	}
	
	public void testFind(){
		sdao=new StudentDao(getContext());
		sdao.Query("xiaoli");
	}

}
