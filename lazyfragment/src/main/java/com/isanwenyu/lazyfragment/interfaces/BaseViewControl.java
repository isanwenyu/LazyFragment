package com.isanwenyu.lazyfragment.interfaces;

import android.os.Bundle;

/**
 * <pre>
 * 基础布局相关接口
 * Created by isanwenyu on 2016/3/3.
 * Copyright (c) 2016 isanwenyu@163.com. All rights reserved.
 * </pre>
 */
public interface BaseViewControl {
    /**
     * 设置自定义布局之前调用 before  {@link #getLayoutId()}
     */
    void onBeforeSetContentLayout();

    /**
     * 初始化保存状态 after {@link #onBeforeSetContentLayout()}
     *
     * @param savedInstanceState
     */
    void init(Bundle savedInstanceState);

    /**
     * 初始化布局 after {@link #init(Bundle)}
     */
    void initView();

    /**
     * 初始化界面后初始化数据 after {@link #initView()}
     */
    void initData();

    /**
     * 子类实现获取自定义布局id
     * @return
     */
    int getLayoutId();
}
