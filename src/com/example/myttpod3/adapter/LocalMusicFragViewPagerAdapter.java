package com.example.myttpod3.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class LocalMusicFragViewPagerAdapter extends FragmentPagerAdapter {

//	private static final int FRAGMENG_MY = 0;
//	private static final int FRAGMENG_RECOMMEND = 1;
//	private static final int FRAGMENG_RANK = 2;
//	private static final int FRAGMENG_CLASSIFY = 3;
	
	private Fragment fragment;
	private final String[] titles = { "歌曲", "歌手", "专辑","文件夹" };
	private List<Fragment> fragments;

	public LocalMusicFragViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}
	public LocalMusicFragViewPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int position) {
//		switch (position) {
//		case FRAGMENG_MY:
//			fragment = MainMyFragment.newInstance();
//			break;
//		case FRAGMENG_RECOMMEND:
//			fragment = MainRecommendFragment.newInstance();
//			break;
//		case FRAGMENG_RANK:
//			fragment = MainRankFragment.newInstance();
//			break;
//		case FRAGMENG_CLASSIFY:
//			fragment = MainClassifyFragment.newInstance();
//			break;
//		default:
//			fragment = MainMyFragment.newInstance();
//			break;
//		}
		fragment = fragments.get(position);
		return fragment;
	}
	@Override
	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	@Override
	public int getCount() {
		return titles.length;
	}
	
	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

}
