package com.example.myttpod3.fragment;

import java.util.List;

import com.example.myttpod3.R;
import com.example.myttpod3.common.entity.MusicInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LocalMusicAlbumFragment extends BaseFragment {
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
	}
	public void initData(List<MusicInfo> queryMusic) {
		// TODO Auto-generated method stub
		
	}
}
