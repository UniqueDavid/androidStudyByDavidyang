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
		//Ϊfragment����layout�ļ���Ȼ��jianglayout�ļ�����ʾת��Ϊһ��View����
		
		return inflater.inflate(R.layout.storagefragment,null);
	}
}
