package com.isanwenyu.lazyfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.isanwenyu.lazyfragment.interfaces.BackFragmentInterface;
import com.isanwenyu.lazyfragment.interfaces.BaseViewControl;

import butterknife.ButterKnife;

/**
 * <pre>
 * 基础的碎片化界面
 * Created by isanwenyu on 2016/2/24.
 * Copyright (c) 2016 isanwenyu@163.com. All rights reserved.
 * </pre>
 */
public abstract class BaseFragment extends Fragment implements BaseViewControl,
        BackFragmentInterface {
    protected LayoutInflater mInflater; //视图填充器

    protected View mView;//用户设置的根布局

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * fragment的初始化方法
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        onBeforeSetContentLayout();
        init(savedInstanceState);
        if (mView == null) {
            this.mInflater = inflater;
            if (getLayoutId() != 0) {
                mView = mInflater.inflate(getLayoutId(), container, false);
            }
        }
        ButterKnife.bind(this, mView);
        initView();
        initData();
        //缓存的rootView需要判断是否已经被加过parent， 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }

        return mView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }


}
