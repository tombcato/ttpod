package com.example.myttpod3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myttpod3.R;


public class MainRecommendFragment extends BaseFragment {

	public static MainRecommendFragment newInstance() {
		return new MainRecommendFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.test, container,false);
	}

}
