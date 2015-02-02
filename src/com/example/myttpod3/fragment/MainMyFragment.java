package com.example.myttpod3.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.myttpod3.R;
import com.example.myttpod3.activity.HomeActivity;
import com.example.myttpod3.activity.MainActivity;

public class MainMyFragment extends BaseFragment implements OnClickListener {
	public static MainMyFragment newInstance(){
		return new MainMyFragment();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_main_my, container,false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
		initData();
	}

	private void initView(View view) {
		RelativeLayout rl_loacl = (RelativeLayout) view.findViewById(R.id.rl_frag_my_local);
		rl_loacl.setOnClickListener(this);
	}

	private void initData() {
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.rl_frag_my_local:
			((HomeActivity)getActivity()).Intent2MyLocalMusic();
			break;
		default:
			break;
		}
	}
}
