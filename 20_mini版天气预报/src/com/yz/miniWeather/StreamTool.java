package com.yz.miniWeather;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamTool {

	public static String decodeStream(InputStream is) {
	
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			
			int len=0;
			byte[] buf=new byte[1024];
			
			while((len=is.read(buf))>0){
				baos.write(buf, 0, len);
			}
			String data=baos.toString();
			
			return data;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
