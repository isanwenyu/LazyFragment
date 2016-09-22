package com.isanwenyu.lazyfragment.interfaces;

/**
 * <pre>
 * 懒加载抽象
 * Created by isanwenyu on 2016/6/15.
 * Copyright (c) 2016 isanwenyu@163.com. All rights reserved.
 * </pre>
 */
public interface LazyFragmentControl {
    /**
     * 界面初始化
     */
    void initPrepare();

    /**
     * 第一次fragment可见（进行初始化工作）
     */
    void onFirstUserVisible();

    /**
     * fragment可见（切换回来或者onResume）
     */
    void onUserVisible();

    /**
     * 第一次fragment不可见（不建议在此处理事件）
     */
    void onFirstUserInvisible();

    /**
     * fragment不可见（切换掉或者onPause）
     */
    void onUserInvisible();

    /**
     * 懒加载数据
     * 在onFirstUserVisible之后
     */
    void lazyData();

    /**
     * 检查上次的显示时间并根据时间间隔自动刷新
     */
    void checkLastTime();

    /**
     * 根据时间{@link #setTimeInterval(long)}超时自动刷新
     */
    void onAutoRefresh();

    /**
     * 设置刷新时间间隔
     * <p>默认时间30秒
     *
     * @param mTimeInterval 单位milliseconds
     */
    void setTimeInterval(long mTimeInterval);
}
