package com.example.myttpod3.fragment;

import java.util.List;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.myttpod3.APP;
import com.example.myttpod3.R;
import com.example.myttpod3.adapter.LocalMusicMyListAdapter;
import com.example.myttpod3.common.AppConstant;
import com.example.myttpod3.common.entity.MusicInfo;
import com.example.myttpod3.service.ServiceManager;

public class LocalMusicMyFragment extends BaseFragment implements AppConstant, OnItemClickListener{
	private ListView lv_music;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			processData();
		};
	};
	private List<MusicInfo> musicData;
	private ServiceManager mServiceManager;
	private LocalMusicMyListAdapter localMusicMyListAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.localmusic_music_layout, container,false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		lv_music = (ListView) view.findViewById(R.id.lv_localmusic_my);
		lv_music.setOnItemClickListener(this);
		mServiceManager = APP.mServiceManager;
	}
	public void initData(List<MusicInfo> musics) {
		this.musicData = musics;
		mHandler.sendEmptyMessage(1);
	}
	private void processData() {
		if(localMusicMyListAdapter == null ){
			localMusicMyListAdapter = new LocalMusicMyListAdapter(getActivity(),mServiceManager,musicData,START_FROM_LOCAL);
			lv_music.setAdapter(localMusicMyListAdapter);
		}else{
			localMusicMyListAdapter.changeAdapterData(musicData);
			localMusicMyListAdapter.notifyDataSetChanged();
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		
	}
	
}
