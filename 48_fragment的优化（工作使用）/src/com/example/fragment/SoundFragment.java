package com.example.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressLint("NewApi")
public class SoundFragment extends Fragment {

	private Button bt;
	private EditText et_text;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//为fragment申明layout文件，然后jianglayout文件的显示转换为一个View对象
	
		View view=inflater.inflate(R.layout.soundfragment,null);
		bt = (Button) view.findViewById(R.id.bt);
		
		bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				et_text = (EditText) getActivity().findViewById(R.id.et_text);
				String data = et_text.getText().toString().trim();
				Toast.makeText(getActivity(), data, 0).show();
			}
		});
		
		return view;
	}
}
