package com.example.myttpod3.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.myttpod3.common.dao.FavoriteInfoDao;
import com.example.myttpod3.common.dao.MusicInfoDao;
import com.example.myttpod3.common.entity.MusicInfo;
import com.example.myttpod3.common.utils.StringHelper;
import com.example.myttpod3.service.ServiceManager;
import com.example.myttpod3.widget.LocalMusic.LocalMusicMyListItem;

public class LocalMusicMyListAdapter extends BaseLvAdapter<MusicInfo>{
	
	private ArrayList<MusicInfo> mMusicList;
	private ServiceManager mServiceManager;
	private FavoriteInfoDao mFavoriteDao;
	private MusicInfoDao mMusicDao;
	private int mPlayState;
	private int mCurPlayMusicIndex;

	public LocalMusicMyListAdapter() {
		super();
	}

	public LocalMusicMyListAdapter(Context ct, List<MusicInfo> lists) {
		super(ct, lists);
	}
	/**
	 * @param ct
	 * @param sm 服务
	 * @param lists 数据
	 * @param from 来源
	 */
	public LocalMusicMyListAdapter(Context ct,ServiceManager sm,List<MusicInfo> lists, int from){
		super(ct, lists);
		mMusicList = new ArrayList<MusicInfo>();
		mFavoriteDao = new FavoriteInfoDao(ct);
		mMusicDao = new MusicInfoDao(ct);
		mServiceManager = sm;
	}
	/**
	 * 当数据库中有数据的时候会调用该方法来更新列表
	 * 
	 * @param list
	 */
	public void changeData(List<MusicInfo> list) {
		mMusicList.clear();
		if (list != null && list.size() > 0) {
			mMusicList.addAll(list);
			// 为list排序
			Collections.sort(mMusicList, comparator);
			notifyDataSetChanged();
		}
	}
	/**
	 * 刷新服务里面的列表
	 */
	public void refreshPlayingList() {
		if(mMusicList.size() > 0) {
			mServiceManager.refreshMusicList(mMusicList);
		}
	}
	public List<MusicInfo> getData() {
		return mMusicList;
	}
	public void setPlayState(int playState, int playIndex) {
		mPlayState = playState;
		mCurPlayMusicIndex = playIndex;
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = new LocalMusicMyListItem(ct);
		}
		((LocalMusicMyListItem)convertView).setData(mMusicList.get(position),position);
		return convertView;
	}
	
	Comparator<MusicInfo> comparator = new Comparator<MusicInfo>() {

		char first_l, first_r;

		@Override
		public int compare(MusicInfo lhs, MusicInfo rhs) {
			first_l = lhs.musicName.charAt(0);
			first_r = rhs.musicName.charAt(0);
			if (StringHelper.checkType(first_l) == StringHelper.CharType.CHINESE) {
				first_l = StringHelper.getPinyinFirstLetter(first_l);
			}
			if (StringHelper.checkType(first_r) == StringHelper.CharType.CHINESE) {
				first_r = StringHelper.getPinyinFirstLetter(first_r);
			}
			if (first_l > first_r) {
				return 1;
			} else if (first_l < first_r) {
				return -1;
			} else {
				return 0;
			}
		}
	};

}
