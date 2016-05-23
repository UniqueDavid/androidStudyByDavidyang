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
			"小年到来拜年早，鞭炮噼啪人欢笑，短信滴滴祝福叫，温馨问候来滋扰，愿你快乐健康保，生意兴隆财运照，天天开心无烦恼，好运福运吉祥绕，万事如意忧愁消，来年生活步步高！",
			"红日高照人间暖，转眼冬去又一年。蛇年风景更美好，姹紫嫣红满家园。生意兴隆通四海，财源滚滚达三江。家庭和睦人欢笑，子孝妻贤心欢畅。祝你蛇年事事顺，快乐幸福歌声扬！",
			"新店开业，吉祥相伴，好运相随，财运相拥，喜气相抱，特送短信，真心祝愿，生意兴隆，日进斗金，大吉大利，财源滚滚。",
			"我生龙活虎的朋友，送你一条本命年红色短信：身体健康事业红，爱情美满人际红，生意兴隆收获红，一年四季红红火火旺到底！",
			"火红的事业财源广进，温馨的祝愿繁荣昌隆，美好的祝福送上来，愿您在收到我的短信后，事业顺利，公司兴旺。",
			"高山上的人总比平原上的人先看到日出。您高瞻远瞩，您的事业必然前景辉煌。祝您鹏程万里!",
			"恭喜贵公司成功上市!在新的一年里大展宏图! 祝您在新的一年中，吉祥如意、笑口常开;也预祝我们在新的一年中，合作愉快，万事如意。"
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms);
		
		lv=(ListView) findViewById(R.id.lv);
		//设置一个适配器
		lv.setAdapter(new MyAdapater(this,R.layout.item,msg));
		//这里是点击每个条目触发的事件，不可以用setOncliklistener
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
			//这里的position是被点击的位置
				Intent intent=new Intent();
				intent.putExtra("msg",msg[position]);
				//intent.setClass(SmsActivity.this,MainActivity.class);
				//startActivity(intent);此处不能再用这个了，否则会跳转到一个新的activity
				//一旦调用setResult就会回到启动当前Activity所在的界面
				setResult(0,intent);
				//结束当前的Activity
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
