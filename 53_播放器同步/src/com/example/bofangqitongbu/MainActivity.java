package com.example.bofangqitongbu;

import android.os.Bundle;
import android.text.TextUtils;

import java.io.IOException;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class MainActivity extends Activity  implements OnSeekBarChangeListener{

	private MediaPlayer player=null;
	private EditText etpath;
	private SeekBar seekbar;
	private boolean tracking=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etpath=(EditText) findViewById(R.id.etPath);
		seekbar=(SeekBar) findViewById(R.id.seekbar);
		
		seekbar.setOnSeekBarChangeListener(this);
		
	}

	public void pause(View view){
		if(player!=null&&player.isPlaying()){
			player.pause();
			((Button)view).setText("����");
		}else if(player!=null){
			player.start();
			performProgress();
			((Button)view).setText("��ͣ");
		}
	}
	
	public void stop(View v){
		if(player!=null){
			player.stop();
			player.release();
			player=null;
		}
	}
	public void play(View v){
		String path=etpath.getText().toString().trim();
		
		if(TextUtils.isEmpty(path)){
			Toast.makeText(MainActivity.this,"·������Ϊ�գ���", 0).show();
			return;
		}
		
		//��������
		if(player==null){
		player = new MediaPlayer();
		}
		
		//���ò�����
		player.reset();
		
		//���ò��ŵ���Դ
		try {
			
			player.setOnErrorListener(new OnErrorListener() {
				
				@Override
				public boolean onError(MediaPlayer mp, int what, int extra) {
					System.out.println("what:"+what);
					return false;
				}
			});
			player.setDataSource(path);
			
			//��ʼ������
//			player.prepare();
			player.setOnPreparedListener(new OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					player.start();
					performProgress();
				}
			});
			player.prepareAsync();//�첽׼��
			
//			player.start();
//			
//			
//			performProgress();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void performProgress() {
		//�����½������ĳ���
		seekbar.setMax(player.getDuration());
		//��һ���߳�ȥ����
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(player!=null&&player.isPlaying()){
					if(!tracking){
					//��õ�ǰ�Ľ���
					int currentPosition=player.getCurrentPosition();
					
					seekbar.setProgress(currentPosition);
					}
				}
			}
		}).start();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	//��ʼ�϶�
		tracking=true;
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		//�����϶�
		tracking=false;
		if(player!=null){
			player.seekTo(seekbar.getProgress());
		}
	}

}
