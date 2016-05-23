package com.example.sdcardstatus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootComplet extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("开机已经完成！！");
	}

}
