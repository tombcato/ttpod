package com.example.myttpod3.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myttpod3.R;
import com.example.myttpod3.adapter.LocalMusicFragViewPagerAdapter;
import com.example.myttpod3.common.AppConstant;
import com.example.myttpod3.common.PagerSlidingTabStrip;
import com.example.myttpod3.common.entity.MusicInfo;
import com.example.myttpod3.service.MusicUtils;

public class LocalMusicFragment extends BaseFragment implements OnPageChangeListener ,AppConstant{
	public static Fragment newInstance() {
		return new LocalMusicFragment();
	}
	private DisplayMetrics dm;//获取当前屏幕的密度
	private List<Fragment> fragments;
	private PagerSlidingTabStrip tabStrip;
	private ViewPager mViewPager;
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				((LocalMusicMyFragment)fragments.get(0)).initData(MusicUtils.queryMusic(getActivity(), START_FROM_LOCAL));
				vp_adapter.notifyDataSetChanged();
				break;
			case 1:
				((LocalMusicArtistFragment)fragments.get(1)).initData(MusicUtils.queryMusic(getActivity(), START_FROM_ARTIST));
				vp_adapter.notifyDataSetChanged();
				break;
			case 2:
				((LocalMusicAlbumFragment)fragments.get(2)).initData(MusicUtils.queryMusic(getActivity(), START_FROM_ALBUM));
				vp_adapter.notifyDataSetChanged();
				break;
			case 3:
				((LocalMusicFolderFragment)fragments.get(3)).initData(MusicUtils.queryMusic(getActivity(), START_FROM_FOLDER));
				vp_adapter.notifyDataSetChanged();
				break;
			default:
				break;
			}
		};
	};
	private LocalMusicFragViewPagerAdapter vp_adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		fragments = new ArrayList<Fragment>();
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_localmusic, container,false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		initView(view);
		initReceicer();
	}
	private void initReceicer() {
		
	}
	@SuppressWarnings("deprecation")
	private void initView(View view) {
		tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.local_slidingtabshost);
		mViewPager = (ViewPager) view.findViewById(R.id.local_music_viewpager);
//		LocalMusicFolderWidget localMusicFolderWidget = new LocalMusicFolderWidget(getContext());
//		LocalMusicMyWidget localMusicMyWidget = new LocalMusicMyWidget(getContext());
//		LocalMusicSingerWidget localMusicSingerWidget = new LocalMusicSingerWidget(getContext());
//		LocalMusicSpecialWidget localMusicSpecialWidget = new LocalMusicSpecialWidget(getContext());
		LocalMusicAlbumFragment localMusicAlbumFragment = new LocalMusicAlbumFragment();
		LocalMusicArtistFragment localMusicArtistFragment = new LocalMusicArtistFragment();
		LocalMusicFolderFragment localMusicFolderFragment = new LocalMusicFolderFragment();
		LocalMusicMyFragment localMusicMyFragment = new LocalMusicMyFragment();
		fragments.add(localMusicMyFragment);
		fragments.add(localMusicArtistFragment);
		fragments.add(localMusicAlbumFragment); 
		fragments.add(localMusicFolderFragment);
		
		vp_adapter = new LocalMusicFragViewPagerAdapter(getActivity().getSupportFragmentManager(), fragments);
		mViewPager.setAdapter(vp_adapter);
		mViewPager.setCurrentItem(0);
		mViewPager.setOnPageChangeListener(this);

		setTabsValue();  
		tabStrip.setViewPager(mViewPager);  
		
	}
	
	private void setTabsValue() {
		dm = getResources().getDisplayMetrics();
		 // 设置Tab是自动填充满屏幕的  
		tabStrip.setShouldExpand(true);  
        // 设置Tab的分割线是透明的  
		tabStrip.setDividerColor(Color.TRANSPARENT);  
        // 设置Tab底部线的高度  
		tabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm));  
        // 设置Tab Indicator的高度  
		tabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小  
		tabStrip.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, dm));  
		tabStrip.setTextColor(Color.GRAY);
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法)  
		tabStrip.setSelectedTextColor(Color.WHITE);
        // 设置Tab Indicator的颜色  
		tabStrip.setIndicatorColor(Color.WHITE);  
        // 取消点击Tab时的背景色  
		tabStrip.setTabBackground(0);  
	}
	private void initData() {
		List<MusicInfo> queryMusic = MusicUtils.queryMusic(getActivity(), START_FROM_LOCAL);
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}
	@Override
	public void onPageSelected(int position) {
		switch (position) {
		case 0:
			mHandler.sendEmptyMessage(0);
			break;
		case 1:
			mHandler.sendEmptyMessage(1);
			break;
		case 2:
			mHandler.sendEmptyMessage(2);
			break;
		case 3:
			mHandler.sendEmptyMessage(3);
			break;
		default:
			break;
		}
	}
	
}
