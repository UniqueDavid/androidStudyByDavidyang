package com.example.kof;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private ImageView player, boss;
	private ProgressBar blood;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		player = (ImageView) findViewById(R.id.mr);
	    boss = (ImageView) findViewById(R.id.boss);
		blood = (ProgressBar) findViewById(R.id.progressbar);
		blood.setProgress(100);
	}

	public void softHand(View v) {
		player.setImageResource(R.drawable.qq);
		int progress = blood.getProgress();
		progress-=5;
		blood.setProgress(progress);
		if(progress<=0){
			blood.setProgress(0);
			boss.setImageResource(R.drawable.die);
		}
	}


	public void heavyHand(View v) {
		player.setImageResource(R.drawable.zq);
		int progress = blood.getProgress();
		progress-=8;
		blood.setProgress(progress);
		if(progress<=0){
			blood.setProgress(0);
			boss.setImageResource(R.drawable.die);
		}
	}

	public void heavyFeet(View v) {
		player.setImageResource(R.drawable.zj);
		int progress = blood.getProgress();
		progress-=10;
		blood.setProgress(progress);
		if(progress<=0){
			blood.setProgress(0);
			boss.setImageResource(R.drawable.die);
		}
	}

}
