package com.example.myttpod3;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.myttpod3.common.utils.ResolutionUtil;
import com.example.myttpod3.service.ServiceManager;

public class APP extends Application {
	public static String APPID = "df470c0e32b549db2a41182701fde031";
	private static String rootPath = "/mymusic";
	public static String lrcPath = "/lrc";
	
	private static APP app;
	private RequestQueue queue;

	private ResolutionUtil resolution;
	public static ServiceManager mServiceManager;
	@Override
	public void onCreate() {
		super.onCreate();
//		Bmob.initialize(this, APPID);
		
		//锟斤拷锟斤拷Application
		app = this;
		//锟斤拷锟斤拷锟绞硷拷锟絍olley锟斤拷锟斤拷
		queue = Volley.newRequestQueue(getApplicationContext());
		
		//全锟斤拷锟斤拷锟斤拷ImageLoader
//        initImageLoader(getApplicationContext());
        
        resolution = new ResolutionUtil(getApplicationContext());
        
        //注锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
//        CrashHandler.getInstance().init(getApplicationContext());
        mServiceManager = new ServiceManager(this);
		initPath();
	}
	 /** 锟斤拷取Application */
    public static APP getApp() {
        return app;
    }
    /**
     * 锟斤拷取Volley实锟斤拷锟斤拷锟�
     */
    public RequestQueue getVolleyQueue() {
		return queue;
	}
    
    public ResolutionUtil getResolution(){
    	return resolution;
    }
	
    private void initPath() {
		String ROOT = "";
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			ROOT = Environment.getExternalStorageDirectory().getPath();
		}
		rootPath = ROOT + rootPath;
		lrcPath = rootPath + lrcPath;
		File lrcFile = new File(lrcPath);
		if(lrcFile.exists()) {
			lrcFile.mkdirs();
		}
	}
    
	
}
