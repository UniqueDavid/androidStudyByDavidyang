package com.example.alipay;

import java.security.InvalidAlgorithmParameterException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AlipayService extends Service {
	
	public class AlipayAgent extends IAlipayService.Stub{

		@Override
		public int callPayInService(String account,float money) throws RemoteException {
			int result=pay(account, money);
			return result;
		}
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return new AlipayAgent();
	}
	@Override
	public void onCreate() {
		super.onCreate();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	public int pay(String account,float money){
		System.out.println("支付宝支付成功！！");
		return 1;
	}
}
