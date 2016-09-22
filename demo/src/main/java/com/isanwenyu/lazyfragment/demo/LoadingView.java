package com.isanwenyu.lazyfragment.demo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.wang.avi.AVLoadingIndicatorView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 加载控制接口
 */
interface LoadingControl {
    /**
     * 隐藏加载布局
     */
    void hide();

    /**
     * 显示加载布局
     */
    void show();
}

/**
 * <pre>
 *  空布局/加载布局的简单实现
 * Created by isanwenyu on 2016/2/24.
 * Copyright (c) 2016 isanwenyu@163.com. All rights reserved.
 * </pre>
 */
public class LoadingView extends FrameLayout implements LoadingControl {
    @Bind(R.id.av_loading)
    AVLoadingIndicatorView mLoadingView;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //initView
        initView();
    }


    /**
     * 初始化布局
     */
    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.include_loading_view, this);
        ButterKnife.bind(this);
        setBackgroundResource(android.R.color.white);
    }

    @Override
    public void hide() {
        mLoadingView.hide();
        setVisibility(GONE);
    }

    @Override
    public void show() {
        setVisibility(VISIBLE);
        mLoadingView.show();
    }


}