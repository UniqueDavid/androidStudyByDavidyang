package com.yz.domain;

import android.test.AndroidTestCase;
import junit.framework.Assert;

public class TestPerson extends AndroidTestCase{
/*
 * ��android�в��ԵĻ������ˣ�����ǰ��jvm��Ϊ�����ֻ�����������
 * 
 * 	�ȸ轫junit���Կ�ܼ��ɽ����������ṩ����һ���ֳɵ��࣬ȥд���Գ����ʱ��ֻ��Ҫ
 *
 *ֻ��Ҫ���������Ϳ�����
 */
	public void testAdd(){
		Person p=new Person();
		int result = p.add(3,4);
		
		Assert.assertEquals(0, result);
	}
}
