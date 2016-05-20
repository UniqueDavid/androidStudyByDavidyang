package com.yz.mininews;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamTool {

	public static String decodeStream(InputStream is) {
		try {
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			
			int len=0;
			byte[] buf=new byte[1024];
			while((len=is.read(buf))>0){
				baos.write(buf, 0, len);
			}
			String data = baos.toString();
			//此处可以解决乱码问题
			if(data.contains("gb2312")){
				return baos.toString("gb2312");
			}else
			return data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

}
