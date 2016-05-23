package com.example.minismshelper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SmsActivity extends Activity {
	private ListView lv;
	private TextView tv_smscontent;
	private ImageView iv_fenge;
	String[] msg={
			"С�굽�������磬������ž�˻�Ц�����ŵε�ף���У���ܰ�ʺ������ţ�Ը����ֽ�������������¡�����գ����쿪���޷��գ����˸��˼����ƣ����������ǳ���������������ߣ�",
			"���ո����˼�ů��ת�۶�ȥ��һ�ꡣ����羰�����ã�����̺�����԰��������¡ͨ�ĺ�����Դ��������������ͥ�����˻�Ц����Т�����Ļ�����ף����������˳�������Ҹ������",
			"�µ꿪ҵ��������飬�������棬������ӵ��ϲ���౧�����Ͷ��ţ�����ףԸ��������¡���ս����𣬴󼪴�������Դ������",
			"������������ѣ�����һ���������ɫ���ţ����彡����ҵ�죬���������˼ʺ죬������¡�ջ�죬һ���ļ�����������ף�",
			"������ҵ��Դ�������ܰ��ףԸ���ٲ�¡�����õ�ף����������Ը�����յ��ҵĶ��ź���ҵ˳������˾������",
			"��ɽ�ϵ����ܱ�ƽԭ�ϵ����ȿ����ճ�������հԶ����������ҵ��Ȼǰ���Ի͡�ף����������!",
			"��ϲ��˾�ɹ�����!���µ�һ�����չ��ͼ! ף�����µ�һ���У��������⡢Ц�ڳ���;ҲԤף�������µ�һ���У�������죬�������⡣"
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		
		lv=(ListView) findViewById(R.id.lv);
		//����һ��������
		lv.setAdapter(new MyAdapater(this,R.layout.item,msg));
		//�����ǵ��ÿ����Ŀ�������¼�����������setOncliklistener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			//�����position�Ǳ������λ��
				Intent intent=new Intent();
				intent.putExtra("msg",msg[position]);
				//intent.setClass(SmsActivity.this,MainActivity.class);
				//startActivity(intent);�˴�������������ˣ��������ת��һ���µ�activity
				//һ������setResult�ͻ�ص�������ǰActivity���ڵĽ���
				setResult(0,intent);
				//������ǰ��Activity
				finish();
			}
		});
	}
	
	class MyAdapater extends ArrayAdapter<Object>{

		public MyAdapater(Context context, int textViewResourceId, Object[] objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			if(convertView==null){
				v=View.inflate(SmsActivity.this,R.layout.item, null);
			}else{
				v=convertView;
			}
			tv_smscontent = (TextView) v.findViewById(R.id.tv_smscontent);
			iv_fenge=(ImageView) v.findViewById(R.id.iv_fenge);
			
			tv_smscontent.setText(msg[position]);
			iv_fenge.setImageResource(R.drawable.fengexian);
			return v;
		}
		
	}

}
