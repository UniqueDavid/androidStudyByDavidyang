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
	// ����С��
	public Handler myHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch(msg.what){
				case SUCCESS:// ȡ����Ϣ�е�����
					bitmaps = (List<Bitmap>) msg.obj;
					refreshView();break;
				case NETWORKERROR:
					Toast.makeText(MainActivity.this,"��������ʧ��....", 0).show();
					break;
				case SERVERERROR:
					Toast.makeText(MainActivity.this,"��������������ʧ��....", 0).show();
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
		// ���������api URL��
		new Thread() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					if (TextUtils.isEmpty(path)) {
						Toast.makeText(MainActivity.this, "·������Ϊ�գ���", 0).show();
						return;
					}
					try {

						URL url = new URL(pathfront + path + pathrear);
						// ���ڴ˴�ʹ�õ���httpЭ�飬���Խ��յ���HttpURLConnection
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();

						// ��������ķ�ʽ
						conn.setRequestMethod("GET");
						// ���ӳ�ʱ����
						conn.setConnectTimeout(5000);

						// ��÷���������Ӧ�𣬴Ӷ�ȷ���Ƿ�ɹ�
						int code = conn.getResponseCode();

						// ��������
						String contentType = conn.getContentType();

						// ���ݳ���
						int contentLength = conn.getContentLength();

						String server = conn.getHeaderField("Server");
						System.out.println("��������" + contentType + "\n���ݳ���" + contentLength + "\nʹ�÷�����:" + server);

						if (code == 200) {
							// ������ʾ�ɹ��Ĵ������󣬷���������

							// ���ͼƬ��������
							InputStream is = conn.getInputStream();

							// ����ͼƬ����ᵼ��oom����������ͼ����
							BitmapFactory.Options opt = new BitmapFactory.Options();
							// ����ͼ��ʾΪԭʼͼƬ�ļ���֮һ
							opt.inSampleSize = 4;
							// ���ȥ����---ֱ�������Ѿ��е�api
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
						//�����ڲ�Ҳ���ڸ���ui����
//						Toast.makeText(MainActivity.this,"��������ʧ��....", 0).show();
						Message msg =Message.obtain();
						msg.what=NETWORKERROR;
						myHandler.sendMessage(msg);
						e.printStackTrace();
					}
					long j = Long.parseLong(path) + 1;
					path = String.valueOf(j);
				}
				// ���߳���ͨ��С�ط�һ����Ϣ,������õ���ģʽ��Ϊ�˱���ķ���Դ�����ж��Ƿ���message���еĻ��Ͳ����´�����
				Message msg = Message.obtain();
				msg.what=SUCCESS;
				msg.obj = bitmaps;

				// �ŵ���Ϣ������messageQueue�У���ѭ����looperȥȡ����Ϣ
				// Ȼ��֪ͨС��ȥ����һ��
				myHandler.sendMessage(msg);
			};

		}.start();

	}

	private void refreshView() {
		if (myadapter == null) {
			// ���Ϊ�գ���ȥ�½�һ��
			myadapter = new MyAdapter();
			lv.setAdapter(myadapter);
		} else {
			// ֪ͨ������adapter����������
			myadapter.notifyDataSetChanged();
		}
	}

	// �˴���һ���ڲ���
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
			// �����Ż�
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
