package com.example.myttpod3.widget.LocalMusic;

import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myttpod3.APP;
import com.example.myttpod3.R;


public class LocalMusicMyWidget extends RelativeLayout implements OnItemClickListener{

	private View view;
	private ListView lv_myMusic;
//	private MyAdapter myAdapter;


	public LocalMusicMyWidget(Context context) {
		super(context);
		init();
	}

	public LocalMusicMyWidget(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LocalMusicMyWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	

	private void init() {
		LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = mInflater.inflate(R.layout.localmusic_music_layout, null);
		
		addView(view);
		initView();
		initData();
		setListener();
	}

	private void initView() {
		lv_myMusic = (ListView) view.findViewById(R.id.lv_localmusic_my);
	}
	
	public void initData(){
//		myAdapter = new MyAdapter(getContext(), APP.mServiceManager);
//		List<MusicInfo> queryMusic = MusicUtils.queryMusic(getContext(), START_FROM_LOCAL);
//		Toast.makeText(getContext(), queryMusic + ":", 0).show();
//		myAdapter.setData(queryMusic);
//		lv_myMusic.setAdapter(myAdapter);
//		
//		lv_myMusic.setOnItemClickListener(this);
	}
	private void setListener() {
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
	}
	

}
