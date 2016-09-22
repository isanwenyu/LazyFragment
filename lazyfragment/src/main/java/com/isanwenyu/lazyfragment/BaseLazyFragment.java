package com.isanwenyu.lazyfragment;

import android.os.Bundle;

import com.isanwenyu.lazyfragment.interfaces.LazyFragmentControl;

/**
 * <pre>
 * 懒加载基础碎片界面
 * Created by isanwenyu on 2016/4/28.
 * Copyright (c) 2016 isanwenyu@163.com. All rights reserved.
 * </pre>
 */
public abstract class BaseLazyFragment extends BaseFragment implements LazyFragmentControl {

    private static final String TAG = BaseLazyFragment.class.getSimpleName();
    private static final long DEFAULT_TIME_INTERVAL = 30 * 1000;//默认间隔时间30秒
    private boolean isPrepared;
    /**
     * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
     */
    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;

    private long mLastVisibleTime = System.currentTimeMillis();//上一次显示的时间
    private long mTimeInterval = DEFAULT_TIME_INTERVAL;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                checkLastTime();
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                //界面不显示 设置上次显示时间
                mLastVisibleTime = System.currentTimeMillis();
                onUserInvisible();
            }
        }
    }

    @Override
    public void checkLastTime() {
        if (System.currentTimeMillis() - mLastVisibleTime > mTimeInterval) {
            onAutoRefresh();
        }
    }

    @Override
    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
            lazyData();
        } else {
            isPrepared = true;
        }
    }


    @Override
    public void onFirstUserVisible() {
//        LogUtil.i(this.getClass().getSimpleName(), "onFirstUserVisible:mLastVisibleTime" + mLastVisibleTime);
    }

    @Override
    public void onUserVisible() {
//        LogUtil.i(this.getClass().getSimpleName(), "onUserVisible:mLastVisibleTime" + mLastVisibleTime);
    }

    @Override
    public void onFirstUserInvisible() {
//        LogUtil.i(this.getClass().getSimpleName(), "onFirstUserInvisible:mLastVisibleTime" + mLastVisibleTime);
    }


    @Override
    public void onUserInvisible() {
//        LogUtil.i(this.getClass().getSimpleName(), "onUserInvisible:mLastVisibleTime" + mLastVisibleTime);
    }

    @Override
    public void onAutoRefresh() {
//        LogUtil.i(this.getClass().getSimpleName(), "onAutoRefresh:mLastVisibleTime" + mLastVisibleTime);
    }

    @Override
    public void setTimeInterval(long mTimeInterval) {
        this.mTimeInterval = mTimeInterval;
    }
}
