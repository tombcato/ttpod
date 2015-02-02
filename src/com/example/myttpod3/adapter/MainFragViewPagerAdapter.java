package com.example.myttpod3.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myttpod3.fragment.MainClassifyFragment;
import com.example.myttpod3.fragment.MainMyFragment;
import com.example.myttpod3.fragment.MainRankFragment;
import com.example.myttpod3.fragment.MainRecommendFragment;

public class MainFragViewPagerAdapter extends FragmentPagerAdapter {

	private static final int FRAGMENG_MY = 0;
	private static final int FRAGMENG_RECOMMEND = 1;
	private static final int FRAGMENG_RANK = 2;
	private static final int FRAGMENG_CLASSIFY = 3;
	
	private Fragment fragment;
	private final String[] titles = { "我的", "推荐", "排行","分类" }; 

	public MainFragViewPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case FRAGMENG_MY:
			fragment = MainMyFragment.newInstance();
			break;
		case FRAGMENG_RECOMMEND:
			fragment = MainRecommendFragment.newInstance();
			break;
		case FRAGMENG_RANK:
			fragment = MainRankFragment.newInstance();
			break;
		case FRAGMENG_CLASSIFY:
			fragment = MainClassifyFragment.newInstance();
			break;
		default:
			fragment = MainMyFragment.newInstance();
			break;
		}
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
