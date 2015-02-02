package com.example.myttpod3.widget.LocalMusic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myttpod3.R;
import com.example.myttpod3.common.entity.MusicInfo;

public class LocalMusicMyListItem extends RelativeLayout implements OnClickListener {

	private View view;
	private TextView tv_music_name;
	private TextView tv_music_singer;
	private TextView tv_music_index_num;
	private boolean isExpand = false;
	private LinearLayout ll_item_tools;

	public LocalMusicMyListItem(Context context) {
		super(context);
		init();
	}

	public LocalMusicMyListItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public LocalMusicMyListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	

	private void init() {
		LayoutInflater mInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = mInflater.inflate(R.layout.localmusic_music_listview_item, null);
		
		addView(view);
		initView();
		setListener();
	}

	private void initView() {
		RelativeLayout rl_item_main = (RelativeLayout) view.findViewById(R.id.rl_music_item_main);
		ll_item_tools = (LinearLayout) view.findViewById(R.id.ll_music_item_tools);
		
		tv_music_index_num = (TextView) view.findViewById(R.id.tv_music_index_num);
		tv_music_name = (TextView) view.findViewById(R.id.tv_music_name);
		tv_music_singer = (TextView) view.findViewById(R.id.tv_music_singer);
		
		ImageView iv_music_expand = (ImageView) view.findViewById(R.id.iv_music_expand);
		ImageView iv_music_collect = (ImageView) view.findViewById(R.id.iv_music_collect);
		ImageView iv_music_download = (ImageView) view.findViewById(R.id.iv_music_download);
		ImageView iv_music_share = (ImageView) view.findViewById(R.id.iv_music_share);
		ImageView iv_music_singer = (ImageView) view.findViewById(R.id.iv_music_singer);
		ImageView iv_music_more = (ImageView) view.findViewById(R.id.iv_music_more);
		
		ll_item_tools.setVisibility(View.GONE);
		
		iv_music_expand.setOnClickListener(this);
	}

	private void setListener() {
		
	}

	public void setData(MusicInfo musicInfo, int position) {
		tv_music_index_num.setText(position+"");
		tv_music_name.setText(musicInfo.musicName);
		tv_music_singer.setText(musicInfo.artist);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_music_expand:
			if(isExpand){
				ll_item_tools.setVisibility(View.GONE);
			}else{
				ll_item_tools.setVisibility(View.VISIBLE);
			}
			break;
		case R.id.iv_music_collect:
			
			break;
		case R.id.iv_music_download:
			
			break;
		case R.id.iv_music_share:
			
			break;
		case R.id.iv_music_singer:
			
			break;
		case R.id.iv_music_more:
			
			break;
		default:
			break;
		}
	}
	

}
