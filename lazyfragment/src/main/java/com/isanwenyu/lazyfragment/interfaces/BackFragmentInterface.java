package com.isanwenyu.lazyfragment.interfaces;

/**
 * <pre>
 * 按返回键接口
 * Created by isanwenyu on 16-4-14.
 * Copyright (c) 2016 isanwenyu@163.com. All rights reserved.
 * </pre>
 */
public interface BackFragmentInterface {
    /**
     * 响应附着activity的返回键事件
     *
     * @return true:fragment拦截事件 false:不拦截
     */
    boolean onBackPressed();
}
