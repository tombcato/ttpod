package com.example.myttpod3.activity;


import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myttpod3.APP;
import com.example.myttpod3.R;
import com.example.myttpod3.adapter.MainFragViewPagerAdapter;
import com.example.myttpod3.adapter.ViewPageAdapter;
import com.example.myttpod3.aidl.IMediaService;
import com.example.myttpod3.common.AppConstant;
import com.example.myttpod3.common.PagerSlidingTabStrip;
import com.example.myttpod3.common.ParallaxViewPager;
import com.example.myttpod3.common.dao.AlbumInfoDao;
import com.example.myttpod3.common.dao.ArtistInfoDao;
import com.example.myttpod3.common.dao.FavoriteInfoDao;
import com.example.myttpod3.common.dao.FolderInfoDao;
import com.example.myttpod3.common.dao.MusicInfoDao;
import com.example.myttpod3.common.entity.MusicInfo;
import com.example.myttpod3.common.utils.FastBlur;
import com.example.myttpod3.fragment.LocalMusicFragment;
import com.example.myttpod3.indicator.CirclePageIndicator;
import com.example.myttpod3.interfaces.IOnServiceConnectComplete;
import com.example.myttpod3.service.ServiceManager;
import com.example.myttpod3.widget.PlayerDisplayView;
import com.example.myttpod3.widget.PlayerLyricsView;
import com.example.myttpod3.widget.PlayerSpectrumView;

public class HomeActivity extends BaseActivity implements IOnServiceConnectComplete,AppConstant{

	private DisplayMetrics dm;
	private PagerSlidingTabStrip tabStrip;
	private ImageView iv_playerDrag;
	private RelativeLayout main;
	private ParallaxViewPager playViewPager;
	private ViewPager mainViewPager;
	private CirclePageIndicator cpi_indicator;
	private FragmentManager fm;
	private ServiceManager mServiceManager;
	private MusicInfoDao mMusicDao;
	private FolderInfoDao mFolderDao;
	private ArtistInfoDao mArtistDao;
	private AlbumInfoDao mAlbumDao;
	private FavoriteInfoDao mFavoriteDao;
	private MusicPlayBroadcast mPlayBroadcast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initView();
		initDB();
		initData();
	}

	private void initDB() {
		mMusicDao = new MusicInfoDao(this);
		mFolderDao = new FolderInfoDao(this);
		mArtistDao = new ArtistInfoDao(this);
		mAlbumDao = new AlbumInfoDao(this);
		mFavoriteDao = new FavoriteInfoDao(this);
		
		mServiceManager = APP.mServiceManager;
		mServiceManager.connectService();
		mServiceManager.setOnServiceConnectComplete(this);
		
		mPlayBroadcast = new MusicPlayBroadcast();
		IntentFilter filter = new IntentFilter(BROADCAST_NAME);
		filter.addAction(BROADCAST_NAME);
		registerReceiver(mPlayBroadcast, filter);
	}

	private void initView() {
		List<View> playDragViews = new ArrayList<View>();
		fm = getSupportFragmentManager();
		main = (RelativeLayout) findViewById(R.id.main); 
		playViewPager = (ParallaxViewPager) findViewById(R.id.viewpager);
//		playViewPager = (ViewPager) findViewById(R.id.viewpager);
		mainViewPager = (ViewPager) findViewById(R.id.mainviewpager);
		iv_playerDrag = (ImageView) findViewById(R.id.playerDrag_bg);
		cpi_indicator = (CirclePageIndicator) findViewById(R.id.viewpager_indictor);
		iv_playerDrag.setScaleType(ScaleType.CENTER_CROP);
		tabStrip = (PagerSlidingTabStrip) findViewById(R.id.main_slidingtabshost);
		main.setBackgroundResource(R.drawable.mybg);
		iv_playerDrag.setBackgroundResource(R.drawable.singer);
		playViewPager.setScaleType(ParallaxViewPager.FIT_HEIGHT);
		playViewPager.setOverlapPercentage(ParallaxViewPager.OVERLAP_HALF);
		
		MainFragViewPagerAdapter pagerAdapter = new MainFragViewPagerAdapter(fm);
		mainViewPager.setAdapter(pagerAdapter);
		
		//初始化PlayDrag
		PlayerDisplayView playerDisplayView = new PlayerDisplayView(this);
		PlayerLyricsView playerLyricsView = new PlayerLyricsView(this);
		PlayerSpectrumView playerSpectrumView = new PlayerSpectrumView(this);
		
		playDragViews.add(playerSpectrumView);
		playDragViews.add(playerDisplayView);
		playDragViews.add(playerLyricsView);
		
		ViewPageAdapter playDragAdapter = new ViewPageAdapter(playDragViews);
		playViewPager.setAdapter(playDragAdapter);
		playViewPager.setCurrentItem(1);
		
		setTabsValue();  
		tabStrip.setViewPager(mainViewPager); 
		cpi_indicator.setViewPager(playViewPager);
		cpi_indicator.setFillColor(Color.WHITE);
		cpi_indicator.setPageColor(Color.DKGRAY);
		cpi_indicator.setRadius(10);
		cpi_indicator.setSnap(true);
		cpi_indicator.setmInterval(20);
		cpi_indicator.setStrokeColor(Color.WHITE);
		cpi_indicator.setStrokeWidth(0);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				applyBlur(iv_playerDrag);
			}
		}, 1000);
        
	}

	private void initData() {
		
	}
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
		tabStrip.setSelectedTextColor(Color.WHITE);
//		tabStrip.setIndicatorSelectedTextColor(Color.WHITE);
        // 设置Tab Indicator的颜色  
		tabStrip.setIndicatorColor(Color.WHITE);  
        // 设置选中Tab文字的颜色 (这是我自定义的一个方法)  
        // 取消点击Tab时的背景色  
		tabStrip.setTabBackground(0);  
	}
	private void applyBlur(View view) {
		view.setDrawingCacheEnabled(true);
		view.buildDrawingCache();
		Bitmap bmp = view.getDrawingCache();
		blur(bmp,view);
		iv_playerDrag.setDrawingCacheEnabled(false);
	}
	 @SuppressWarnings("deprecation")
	private void blur(Bitmap bkg, View view) {
	        float scaleFactor = 10;
	        float radius = 15;
            Bitmap overlay = Bitmap.createBitmap(
    				(int) (view.getMeasuredWidth() / scaleFactor),
    				(int) (view.getMeasuredHeight() / scaleFactor),
    				Bitmap.Config.ARGB_8888);
    		Canvas canvas = new Canvas(overlay);
    		// canvas.translate(-view.getLeft() / scaleFactor, -view.getTop()/ scaleFactor);
    		canvas.scale(1 / scaleFactor, 1 / scaleFactor);
    		Paint paint = new Paint();
    		paint.setFlags(Paint.FILTER_BITMAP_FLAG);
    		canvas.drawBitmap(bkg, 0, 0, paint);
    		overlay = FastBlur.doBlur(overlay, (int) radius, true);
    		if (Build.VERSION.SDK_INT < 16) {// 16level以前使用这个方法，在16中被废弃
    			view.setBackgroundDrawable(new BitmapDrawable(getResources(), overlay));
    		} else {
    			view.setBackground(new BitmapDrawable(getResources(),overlay));
    		}
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void Intent2MyLocalMusic() {
		FragmentTransaction bt = fm.beginTransaction();
		bt.replace(R.id.main_replace, LocalMusicFragment.newInstance(),"LocalMusicFragment")
		  .commit();
	}

	@Override
	public void onServiceConnectComplete(IMediaService service) {
		Toast.makeText(this, "onServiceConnectComplete", 0).show();
	}
	
	private class MusicPlayBroadcast extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(BROADCAST_NAME)) {
				MusicInfo music = new MusicInfo();
				int playState = intent.getIntExtra(PLAY_STATE_NAME, MPS_NOFILE);
				int curPlayIndex = intent.getIntExtra(PLAY_MUSIC_INDEX, -1);
				Bundle bundle = intent.getBundleExtra(MusicInfo.KEY_MUSIC);
				if (bundle != null) {
					music = bundle.getParcelable(MusicInfo.KEY_MUSIC);
				}
				switch (playState) {
				case MPS_INVALID:// 考虑后面加上如果文件不可播放直接跳到下一首
					break;
				case MPS_PAUSE:
					break;
				case MPS_PLAYING:
					break;
				case MPS_PREPARE:
					break;
				}
			}
		}
		
	}
	@Override
	protected void onDestroy() {
		if(mPlayBroadcast != null){
			unregisterReceiver(mPlayBroadcast);
		}
		super.onDestroy();
	}
}
