package com.example.myttpod3.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.myttpod3.R;

public class PlayerLyricsView extends RelativeLayout {

	private View view;


	public PlayerLyricsView(Context context) {
		super(context);
		init();
	}

	public PlayerLyricsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public PlayerLyricsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	

	private void init() {
		LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = mInflater.inflate(R.layout.test, null);
		
		addView(view);
		initView();
		setListener();
	}

	private void initView() {
		
	}

	private void setListener() {
		
	}
	

}
