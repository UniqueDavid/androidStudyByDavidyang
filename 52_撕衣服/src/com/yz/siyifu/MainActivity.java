package com.yz.siyifu;

import android.os.Bundle;
import android.R.color;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnTouchListener {

	private Bitmap copyBitmap;
	private ImageView iv_pre, iv_after;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		iv_after = (ImageView) findViewById(R.id.iv_after);
		iv_pre = (ImageView) findViewById(R.id.iv_pre);

		iv_after.setImageResource(R.drawable.after1);
		iv_pre.setImageResource(R.drawable.pre1);

		// ��������ͼƬ��touch�¼�
		iv_pre.setOnTouchListener(this);
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pre1);
		copyBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
		Canvas canvas = new Canvas(copyBitmap);
		Paint paint = new Paint();
		Matrix matrix = new Matrix();
		canvas.drawBitmap(bitmap, matrix, paint);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// ���������У�ȡ��ԭͼ�Ŀ������޸Ŀ���ͼƬ����ɫ

		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			// ���£���ɫȥ��
			int downX = (int) (event.getX() + 0.5f);
			int downY = (int) (event.getY() + 0.5f);
			// ��ɫȥ��
			clearColor(downX, downY);
			break;
		case MotionEvent.ACTION_MOVE:
			// �ƶ�����ɫȥ��
			int moveX = (int) (event.getX() + 0.5f);
			int moveY = (int) (event.getY() + 0.5f);

			clearColor(moveX, moveY);
			break;
		case MotionEvent.ACTION_UP:
			// ����

			break;
		default:
			break;
		}
		// �����¼�����
		return true;
	}

	/**
	 * @param downX
	 * @param downY
	 */
	public void clearColor(int downX, int downY) {
		// for(int i=-6;i<=6;i++)
		// {
		// for(int j=-6;j<=6;j++){
		// try{
		// copyBitmap.setPixel(downX+i, downY+j, Color.TRANSPARENT);
		// }catch(Exception e){
		//
		// }
		// }
		//
		// }
		// ���Ѵ���
		for (int i = -5; i <= 5; i++) {
			for (int j = -5; j <= 5; j++) {
				if (Math.sqrt(i * i + j * j) <= 5) {
					if (downX + i < copyBitmap.getWidth() && downY + j < copyBitmap.getHeight() && downX + i >= 0
							&& downY + j >= 0) {
						copyBitmap.setPixel(downX + i, downY + j, Color.TRANSPARENT);
						iv_pre.setImageBitmap(copyBitmap);
					}
				}
			}

		}
	}
}
