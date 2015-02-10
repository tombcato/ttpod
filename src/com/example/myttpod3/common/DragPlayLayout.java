package com.example.myttpod3.common;


import java.lang.reflect.Field;

import com.example.myttpod3.R;
import com.example.myttpod3.R.id;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by Flavien Laurent (flavienlaurent.com) on 23/08/13.
 */
public class DragPlayLayout extends ViewGroup {

	private ViewDragHelper mDragHelper;

	private View mHeaderView;
    private View mDescView;

	private float mInitialMotionX;
	private float mInitialMotionY;

	private int mDragRange;
//    private int mTop = new ResolutionUtil(getContext()).px2dp2pxHeight(1200);
    private int mTop ;
	private float mDragOffset;

	private boolean isGone = true;

	private GestureDetector mGestureDetector;

	public boolean isEdgeDrag;

	private boolean isNeedTouch;
    public DragPlayLayout(Context context) {
		this(context, null);
		init();
	}

	public DragPlayLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		init();
	}

	public DragPlayLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
		
	}
	

    private void init() {
    	mDragHelper = ViewDragHelper.create(this, 1f, new DragHelperCallback());
		mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_BOTTOM);
		mDragHelper.EDGE_SIZE = 50;
		 mGestureDetector = new GestureDetector(getContext(),new YScrollDetector());  
		mTop = getScreenHeight(getContext()) - getStatusHeight(getContext());
	}
  //��ȡ��Ļ�ĸ߶� 
    public static int getScreenHeight(Context context) { 
        WindowManager manager = (WindowManager) context 
                .getSystemService(Context.WINDOW_SERVICE); 
        Display display = manager.getDefaultDisplay(); 
        return display.getHeight(); 
    }
    
    public static int getStatusHeight(Context ct){

		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
		    c = Class.forName("com.android.internal.R$dimen");
		    obj = c.newInstance();
		    field = c.getField("status_bar_height");
		    x = Integer.parseInt(field.get(obj).toString());
		    sbar = ct.getResources().getDimensionPixelSize(x);
		} catch(Exception e1) {
//		    loge("get status bar height fail");
		    e1.printStackTrace();
		}
		return sbar;
    }
    

	@Override
    protected void onFinishInflate() {
//        mHeaderView = findViewById(R.id.header);
//        mDescView = findViewById(R.id.desc);
		mDescView = findViewById(R.id.playerDrag);
    }
    private class DragHelperCallback extends ViewDragHelper.Callback {

		
		@Override
		public boolean tryCaptureView(View child, int pointerId) {
            return child == mDescView;
		}

        @Override
		public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
			

			mDragOffset = (float) top / mDragRange;
//			Log.e("msg", mDragOffset + ":");
			mTop = top;
//            mHeaderView.setPivotX(mHeaderView.getWidth());
//            mHeaderView.setPivotY(mHeaderView.getHeight());
//            mHeaderView.setScaleX(1 - mDragOffset / 2);
//            mHeaderView.setScaleY(1 - mDragOffset / 2);
//
//            mDescView.setAlpha(1 - mDragOffset);

            requestLayout();
		}

		@Override
		public void onViewReleased(View releasedChild,  float xvel, float yvel) {
			int top = getPaddingTop();
//			if(yvel >= 0 && mDragOffset > 0.5f){
//				top += mDragRange;
//				Toast.makeText(getContext(), "bottom", 0).show();
//			}
			if (yvel > 0 || (yvel == 0 && mDragOffset > 0.5f)) {
				top += mDragRange;
//				Toast.makeText(getContext(), "bottom", 0).show();
//				isGone = true;
			}
//			else if( yvel < -100 || (yvel == 0) && mDragOffset)
			else{
//				Toast.makeText(getContext(), "top", 0).show();
//				isGone = false;
			}
			mDragHelper.settleCapturedViewAt(releasedChild.getLeft(), top);
			invalidate();
		}

		@Override
		public int getViewVerticalDragRange(View child) {
			return mDragRange;
		}

		@Override
		public int clampViewPositionVertical(View child, int top, int dy) {
			final int topBound = getPaddingTop();
			final int bottomBound = getHeight();

			final int newTop = Math.min(Math.max(top, topBound), bottomBound);
			return newTop;
		}
		
		@Override
		public void onEdgeTouched(int edgeFlags, int pointerId) {
			super.onEdgeTouched(edgeFlags, pointerId);
//			Toast.makeText(getContext(), "edgeTouched", Toast.LENGTH_SHORT).show();
		}
		@Override
		public void onEdgeDragStarted(int edgeFlags, int pointerId) {
			mDragHelper.captureChildView(mDescView, pointerId);
			isEdgeDrag = true;
//			Toast.makeText(getContext(), "isEdgeDrag", 0).show();
		}
		

	}
    public void maximize() {
        smoothSlideTo(0f);
    }

    public void minimize() {
        smoothSlideTo(1f);
    }

    boolean smoothSlideTo(float slideOffset) {
        final int topBound = getPaddingTop();
        int y = (int) (topBound + slideOffset * mDragRange);

        if (mDragHelper.smoothSlideViewTo(mDescView, mDescView.getLeft(), y)) {
            ViewCompat.postInvalidateOnAnimation(this);
            return true;
        }
        return false;
    }
    @Override
	public void computeScroll() {
		if (mDragHelper.continueSettling(true)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}
    
    @SuppressLint("NewApi")
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    	measureChildren(widthMeasureSpec, heightMeasureSpec);
    	int maxWidth = MeasureSpec.getSize(widthMeasureSpec);
    	int maxHeight = MeasureSpec.getSize(heightMeasureSpec);
    	setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0), resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
    }

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		mDragRange = getHeight();
//		mTop = 100;
		mDescView.layout(0, mTop, r, mTop + getHeight());
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return mGestureDetector.onTouchEvent(ev);
//		final int action = MotionEventCompat.getActionMasked(ev);
//
//		if (( action != MotionEvent.ACTION_DOWN)) {
//			mDragHelper.cancel();
//			return super.onInterceptTouchEvent(ev);
//		}
//
//		if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//			mDragHelper.cancel();
//			return false;
//		}
//
//		final float x = ev.getX();
//		final float y = ev.getY();
//		boolean interceptTap = false;
//
//		switch (action) {
//			case MotionEvent.ACTION_DOWN: {
//				mInitialMotionX = x;
//				mInitialMotionY = y;
//                interceptTap = mDragHelper.isViewUnder(mDescView, (int) x, (int) y);
//				break;
//			}
//
//			case MotionEvent.ACTION_MOVE: {
//				final float adx = Math.abs(x - mInitialMotionX);
//				final float ady = Math.abs(y - mInitialMotionY);
//				final int slop = mDragHelper.getTouchSlop();
//                /*useless*/
//				if(adx > ady || ady< slop){
//					mDragHelper.cancel();
//					return false;
//				}else{
//					return true;
//				}
////				if (ady > slop || adx > ady) {
////					mDragHelper.cancel();
////					return false;
////				}
//			}
//		}
//		if(isGone){
//			return mDragHelper.shouldInterceptTouchEvent(ev);
//		}else{
//			return mDragHelper.shouldInterceptTouchEvent(ev) || interceptTap;
//		}
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		mDragHelper.processTouchEvent(ev);

		final int action = ev.getAction();
        final float x = ev.getX();
        final float y = ev.getY();
        final int slop = mDragHelper.getTouchSlop();

        boolean isHeaderViewUnder = false;
        switch (action & MotionEventCompat.ACTION_MASK) {
			case MotionEvent.ACTION_DOWN: {
				mInitialMotionX = x;
				mInitialMotionY = y;
				isHeaderViewUnder = mDragHelper.isViewUnder(mDescView, (int) x, (int) y);
				break;
			}
			case MotionEvent.ACTION_MOVE:{
				float moveX = x -mInitialMotionX;
				float moveY = y -mInitialMotionY;
				if(moveX > slop || moveY > slop ){
//					performClick();
//					return true ;s
				}
				
			}

			case MotionEvent.ACTION_UP: {
				final float dx = x - mInitialMotionX;
				final float dy = y - mInitialMotionY;
//				if(dx < slop && dy < slop ){
//					performClick();
//				}
				
//				if (dx * dx + dy * dy < slop * slop && isHeaderViewUnder) {
//					
////					if (mDragOffset == 0) {
////						smoothSlideTo(1f);
//////						Toast.makeText(getContext(), "bottom", 0).show();
////					} else {
////						smoothSlideTo(0f);
//////						Toast.makeText(getContext(), "top", 0).show();
////					}
//					performClick();
//					return false;
//					
//				}
//				else{
//					
//					return true;
//				}
//				break;
			}
		}
//        return true && isHeaderViewUnder;
        if(mDragHelper.isEdgeTouched(ViewDragHelper.EDGE_BOTTOM)){
//        	if(isEdgeDrag){
//        		isEdgeDrag = false;
//        		return true;
//        	}else{
//        		performClick();
//        		return false;
//        	}
        	performClick();
        	return true;
//        	return true;
        }else{
        	 return true && isHeaderViewUnder;
        }
//       
//        return true && isHeaderViewUnder;
//        if(isGone){
//        	return true;
//        }else{
//        	return isHeaderViewUnder && isViewHit(mDescView, (int) x, (int) y) || isViewHit(mDescView, (int) x, (int) y);
//        }
		
//		return isHeaderViewUnder && isViewHit(mDescView, (int) x, (int) y) || isViewHit(mDescView, (int) x, (int) y);
	}
	
	private boolean isViewHit(View view, int x, int y) {
        int[] viewLocation = new int[2];
        view.getLocationOnScreen(viewLocation);
        int[] parentLocation = new int[2];
        this.getLocationOnScreen(parentLocation);
        int screenX = parentLocation[0] + x;
        int screenY = parentLocation[1] + y;
        return screenX >= viewLocation[0] && screenX < viewLocation[0] + view.getWidth() &&
                screenY >= viewLocation[1] && screenY < viewLocation[1] + view.getHeight();
    }
	
	class YScrollDetector extends SimpleOnGestureListener {  
        
		
		private boolean interceptTap;
		
		@Override
		public boolean onDown(MotionEvent e) {
			float x = e.getX();
			float y = e.getY();
			interceptTap = mDragHelper.isViewUnder(mDescView, (int) x, (int) y);
			return super.onDown(e);
		}

		@Override  
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {  
			
        	if(distanceY!=0&&distanceX!=0){  
             
        	}  
            if(Math.abs(distanceY) >= Math.abs(distanceX) ) {  
//            	isInter = true;
            	 Log.e("msg",  "viewpager no lanjie" );
//                return true && interceptTap;  
                return true;  
            }  
            if(mDragHelper.isEdgeTouched(ViewDragHelper.EDGE_BOTTOM)){
            	return true;
            }
            Log.e("msg",  "viewpager lanjie" );
//            isInter = false;
            mDragHelper.cancel();
            return false;  
        }  
	}
	
    
}
