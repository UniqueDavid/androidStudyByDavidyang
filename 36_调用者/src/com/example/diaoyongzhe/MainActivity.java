package com.example.diaoyongzhe;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.remoteservice.IService;

import android.app.Activity;
import android.app.IntentService;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	MyConnection conn=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void bindservice(View v){
		if(conn==null){
			conn=new MyConnection();
		}
		Intent intent=new Intent();
		intent.setAction("com.yz.rm");
		bindService(intent, conn,BIND_AUTO_CREATE);
		
		
	}
	
	public void unbindservice(View v){
		Intent intent=new Intent();
		intent.setAction("com.yz.rm");
		if(conn!=null){
			unbindService(conn);
		}
	}
	private IService agent;
	private class MyConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			agent=IService.Stub.asInterface(service);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
		}
		
	}
	public void call(View v){
		try {
			System.out.println("调用远程服务中的方法");
			agent.callMethodInService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
