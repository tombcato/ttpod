package com.example.myttpod3.widget;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.myttpod3.R;
import com.example.myttpod3.adapter.ViewPageAdapter;
import com.example.myttpod3.common.PagerSlidingTabStrip;
import com.example.myttpod3.widget.LocalMusic.LocalMusicFolderWidget;
import com.example.myttpod3.widget.LocalMusic.LocalMusicMyWidget;
import com.example.myttpod3.widget.LocalMusic.LocalMusicSingerWidget;
import com.example.myttpod3.widget.LocalMusic.LocalMusicSpecialWidget;

public class LocalMusicView extends RelativeLayout {

	private View view;
	private PagerSlidingTabStrip tabStrip;
	private ViewPager mViewPager;


	public LocalMusicView(Context context) {
		super(context);
		init();
	}

	public LocalMusicView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LocalMusicView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	

	private void init() {
		LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = mInflater.inflate(R.layout.fragment_localmusic, null);
		
		addView(view);
		initView();
		setListener();
	}

	private void initView() {
		List<View> views = new ArrayList<View>();
		
		tabStrip = (PagerSlidingTabStrip) view.findViewById(R.id.local_slidingtabshost);
		mViewPager = (ViewPager) view.findViewById(R.id.local_music_viewpager);
		LocalMusicFolderWidget localMusicFolderWidget = new LocalMusicFolderWidget(getContext());
		LocalMusicMyWidget localMusicMyWidget = new LocalMusicMyWidget(getContext());
		LocalMusicSingerWidget localMusicSingerWidget = new LocalMusicSingerWidget(getContext());
		LocalMusicSpecialWidget localMusicSpecialWidget = new LocalMusicSpecialWidget(getContext());
		
		views.add(localMusicMyWidget);
		views.add(localMusicSingerWidget);
		views.add(localMusicSpecialWidget);
		views.add(localMusicFolderWidget);
		
		ViewPageAdapter pagerAdapter = new ViewPageAdapter(views);
		mViewPager.setAdapter(pagerAdapter);

		setTabsValue();  
		tabStrip.setViewPager(mViewPager);  
	}
	/**
	 * 获取当前屏幕的密度
	 */
	private DisplayMetrics dm;
	private List<View> views;
	private void setTabsValue() {
		dm = getResources().getDisplayMetrics();
		 // 设置Tab是自动填充满屏幕的  
		tabStrip.setShouldExpand(true);  
        // 设置Tab的分割线是透明的  
		tabStrip.setDividerColor(Color.TRANSPARENT);  
        // 设置Tab底部线的高度  
		tabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm));  
//		tabStrip.setUnderlineHeight(resolution.px2dp2px(1, false));  
        // 设置Tab Indicator的高度  
//		tabStrip.setIndicatorHeight(resolution.px2dp2px(5, false));  
		tabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm));
        // 设置Tab标题文字的大小  
//		tabStrip.setTextSize(resolution.px2sp2px(80));  
		tabStrip.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, dm));  
		tabStrip.setTextColor(Color.GRAY);
		// 设置选中Tab文字的颜色 (这是我自定义的一个方法) 
		tabStrip.setSelectedTextColor(Color.WHITE);
//		tabStrip.setIndicatorSelectedTextColor(Color.WHITE);
        // 设置Tab Indicator的颜色  
		tabStrip.setIndicatorColor(Color.WHITE);  
        // 取消点击Tab时的背景色  
		tabStrip.setTabBackground(0);  
	}
	private void setListener() {
		
	}
	

}
