package com.yz.domain;

import android.test.AndroidTestCase;
import junit.framework.Assert;

public class TestPerson extends AndroidTestCase{
/*
 * 在android中测试的环境变了，由以前的jvm变为现在手机环境。。。
 * 
 * 	谷歌将junit测试框架集成进来，并且提供好了一个现成的类，去写测试程序的时候，只需要
 *
 *只需要集成这个类就可以了
 */
	public void testAdd(){
		Person p=new Person();
		int result = p.add(3,4);
		
		Assert.assertEquals(0, result);
	}
}
