package com.example.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("NewApi")
public class StorageFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//为fragment申明layout文件，然后jianglayout文件的显示转换为一个View对象
		
		return inflater.inflate(R.layout.storagefragment,null);
	}
}
