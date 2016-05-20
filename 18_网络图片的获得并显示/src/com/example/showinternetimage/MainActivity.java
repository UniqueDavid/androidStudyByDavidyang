package com.example.showinternetimage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SearchViewCompat.OnCloseListenerCompat;
import android.text.TextUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int SUCCESS = 0;
	protected static final int NETWORKERROR = 1;
	protected static final int SERVERERROR = 2;
	private TextView tv_imagefront;
	private TextView tv_imagerear;
	private EditText ed_imageurl;
	private Button btn_showimage;
	private ImageView iv;
	private ListView lv;
	private List<Bitmap> bitmaps;
	String path, pathfront, pathrear;
	private MyAdapter myadapter;
	// 设置小秘
	public Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what){
				case SUCCESS:// 取出消息中的数据
					bitmaps = (List<Bitmap>) msg.obj;
					refreshView();break;
				case NETWORKERROR:
					Toast.makeText(MainActivity.this,"网络连接失败....", 0).show();
					break;
				case SERVERERROR:
					Toast.makeText(MainActivity.this,"服务器请求数据失败....", 0).show();
					break;
			}
			
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ed_imageurl = (EditText) findViewById(R.id.ed_imageurl);
		tv_imagefront = (TextView) findViewById(R.id.tv_imagefront);
		tv_imagerear = (TextView) findViewById(R.id.tv_imagerear);
		lv = (ListView) findViewById(R.id.lv);
		refreshView();
	}

	public void getImage(View v) {
		bitmaps = new ArrayList<Bitmap>();
		pathfront = tv_imagefront.getText().toString().trim();
		pathrear = tv_imagerear.getText().toString().trim();
		path = ed_imageurl.getText().toString().trim();
		// 连接网络的api URL类
		new Thread() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					if (TextUtils.isEmpty(path)) {
						Toast.makeText(MainActivity.this, "路径不能为空！！", 0).show();
						return;
					}
					try {

						URL url = new URL(pathfront + path + pathrear);
						// 由于此处使用的是http协议，所以接收的是HttpURLConnection
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();

						// 设置请求的方式
						conn.setRequestMethod("GET");
						// 连接超时设置
						conn.setConnectTimeout(5000);

						// 获得服务器的响应吗，从而确定是否成功
						int code = conn.getResponseCode();

						// 数据类型
						String contentType = conn.getContentType();

						// 数据长度
						int contentLength = conn.getContentLength();

						String server = conn.getHeaderField("Server");
						System.out.println("数据类型" + contentType + "\n数据长度" + contentLength + "\n使用服务器:" + server);

						if (code == 200) {
							// 进来表示成功的处理请求，返回了数据

							// 获得图片的流数据
							InputStream is = conn.getInputStream();

							// 由于图片过大会导致oom，采用缩略图方法
							BitmapFactory.Options opt = new BitmapFactory.Options();
							// 缩略图表示为原始图片的几分之一
							opt.inSampleSize = 4;
							// 如何去解析---直接利用已经有的api
							Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);

							bitmaps.add(bitmap);

							is.close();
						}else
						{
							Message msg =Message.obtain();
							msg.what=SERVERERROR;
							myHandler.sendMessage(msg);
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						//代码内部也是在更新ui界面
//						Toast.makeText(MainActivity.this,"网络连接失败....", 0).show();
						Message msg =Message.obtain();
						msg.what=NETWORKERROR;
						myHandler.sendMessage(msg);
						e.printStackTrace();
					}
					long j = Long.parseLong(path) + 1;
					path = String.valueOf(j);
				}
				// 子线程中通过小秘发一个消息,这里采用单列模式，为了避免耗费资源，先判断是否有message，有的话就不重新创建了
				Message msg = Message.obtain();
				msg.what=SUCCESS;
				msg.obj = bitmaps;

				// 放到消息队列中messageQueue中，由循环器looper去取出消息
				// 然后通知小秘去处理一下
				myHandler.sendMessage(msg);
			};

		}.start();

	}

	private void refreshView() {
		if (myadapter == null) {
			// 如果为空，则去新建一个
			myadapter = new MyAdapter();
			lv.setAdapter(myadapter);
		} else {
			// 通知控制器adapter更新下数据
			myadapter.notifyDataSetChanged();
		}
	}

	// 此处是一个内部类
	private class MyAdapter extends BaseAdapter {
		View v;

		@Override
		public int getCount() {
			if (bitmaps != null) {
				return bitmaps.size();
			} else {
				return 0;
			}
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 用于优化
			if (convertView == null) {

				v = View.inflate(MainActivity.this, R.layout.items, null);
			} else {
				v = convertView;
			}
			iv = (ImageView) v.findViewById(R.id.iv);
			iv.setImageBitmap(bitmaps.get(position));
			return v;
		}

	}
}
