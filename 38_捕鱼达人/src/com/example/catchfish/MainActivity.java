package com.example.catchfish;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.alipay.IAlipayService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void bindservice(View v){
		Intent intent=new Intent();
		intent.setAction("com.yz.alipay");
		bindService(intent, new MyConnection(), BIND_AUTO_CREATE);
	}
	public void buyBullet(View v){
		try {
			int result = agent.callPayInService("xiaoming",1000);
			if(result==1){
				Toast.makeText(this,"Ö§¸¶³É¹¦£¡£¡", 0).show();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private IAlipayService agent;
	public class MyConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			agent=IAlipayService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
		
	} 
}
