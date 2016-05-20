package com.example.qqlogin.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {

	public static String decodeStream(InputStream is) throws IOException {
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		int len=0;
		byte[] buf=new byte[1024];
		
		while((len=is.read(buf))>0){
			baos.write(buf);
		}
		String data = baos.toString();
		return data;
	}

}
