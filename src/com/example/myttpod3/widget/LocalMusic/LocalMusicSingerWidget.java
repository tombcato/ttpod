package com.example.myttpod3.widget.LocalMusic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.myttpod3.R;


public class LocalMusicSingerWidget extends RelativeLayout {

	private View view;


	public LocalMusicSingerWidget(Context context) {
		super(context);
		init();
	}

	public LocalMusicSingerWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LocalMusicSingerWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	

	private void init() {
		LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = mInflater.inflate(R.layout.localmusic_music_layout, null);
		
		addView(view);
		initView();
		setListener();
	}

	private void initView() {
		
	}

	private void setListener() {
		
	}
	

}
