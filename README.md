# LazyFragment

[![Build Status](https://travis-ci.org/isanwenyu/LazyFragment.svg?branch=master)](https://travis-ci.org/isanwenyu/LazyFragment)
[ ![Download](https://api.bintray.com/packages/isanwenyu/maven/LazyFragment/images/download.svg) ](https://bintray.com/isanwenyu/maven/LazyFragment/_latestVersion)
[![Platform](http://img.shields.io/badge/platform-android-brightgreen.svg?style=flat)](http://developer.android.com/index.html)
[![License](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0)

Imitate WeChat lazy loading using fragments in Android. 安卓仿微信懒加载/延迟加载的实现

## Features 

- LazyData 懒加载/延迟加载
- Fragments inherited the abstract class BaseLazyFragment will be automatically refresh in a certain time interval (the default 30 seconds). 继承抽象类BaseLazyFragment的碎片会在一定时间间隔(默认30秒)自动刷新
- Use with ViewPager 和ViewPager一起使用

## Getting Started

- Gradle

```
compile 'com.isanwenyu.lazyfragment:lazyfragment:1.0.0'
```
- Maven

```
<dependency>
  <groupId>com.isanwenyu.lazyfragment</groupId>
  <artifactId>lazyfragment</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

## Quick Preview
![gif](gif/lazyfragment_demo.gif)

## Introduce

- Inherit the abstrat class BaseFragment that interface initialization process optimized
继承界面初始化过程优化后的超类BaseFragment

- Implement interface LazyFragentControl  实现LazyFragentControl接口


Main Method | Description | Must Override 
------- | ------- | -------
lazyData() | 懒加载数据 一般用于做请求网络数据 | true
onAutoRefresh() | 根据时间{@link #setTimeInterval(long)}超时自动刷新 | false

```

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
```

## Usage

1. Inherit the abstrat class BaseLazyFragment that you can use [butterknife](http://jakewharton.github.io/butterknife/) directly 继承BaseLazyFragment抽象类,也可直接使用butterknife
**A placeholder fragment containing a simple view.**

2. Custom your SectionsPagerAdapter inherited FragmentPagerAdapter 继承FragmentPagerAdapter实现自定义的ViewPager适配器

3. mViewPager.setOffscreenPageLimit(2);//缓存两页
        mViewPager.setAdapter(mSectionsPagerAdapter);
        为ViewPager设置适配器并设置缓存2页（实际带上当前页面缓存3页）

## Dependencies

* [BintrayUploadGradle](https://github.com/isanwenyu/BintrayUploadGradle) 上传repo到bintray以供添加到JCenter
* [butterknife](http://jakewharton.github.io/butterknife/) 视图绑定框架
* [AVLoadingIndicatorView](https://github.com/81813780/AVLoadingIndicatorView) 动画加载控件


License
-------
    Copyright (C) 2016 isanwenyu@163.com
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
