package com.isanwenyu.lazyfragment.demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.isanwenyu.lazyfragment.BaseLazyFragment;

import butterknife.Bind;

/**
 * <pre>
 * 主界面
 * 模板参考：AndroidStudio New -> Activity -> Tabbed Activity -> Navigation Style: Action Bar Tabs(with viewpager)
 * Created by isanwenyu on 2016/2/24.
 * Copyright (c) 2016 isanwenyu@163.com. All rights reserved.
 * </pre>
 */
public class MainActivity extends AppCompatActivity {

    public static final String HTTPS_GITHUB_COM_ISANWENYU = "https://github.com/isanwenyu";
    public static final String HTTP_ISANWENYU_GITHUB_IO = "http://isanwenyu.github.io";
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setOffscreenPageLimit(2);//缓存两页
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_github) {
            //跳转到我的github主页
            openBrowser(HTTPS_GITHUB_COM_ISANWENYU);

            return true;
        } else if (id == R.id.action_blog) {
            //跳转到我的blog主页
            openBrowser(HTTP_ISANWENYU_GITHUB_IO);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 打开浏览器
     *
     * @param url
     */
    private void openBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends BaseLazyFragment {

        public static final int DELAY_MILLIS = 3 * 1000;//模拟延迟时间
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        @Bind(R.id.section_label)
        TextView textView;
        @Bind(R.id.loading_view)
        LoadingView loadingView;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


        @Override
        public void onBeforeSetContentLayout() {

        }

        @Override
        public void init(Bundle savedInstanceState) {

        }

        @Override
        public void initView() {
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        }

        @Override
        public void initData() {
        }

        @Override
        public int getLayoutId() {
            return R.layout.fragment_main;
        }

        @Override
        public void lazyData() {
            requestData();
        }

        @Override
        public void onAutoRefresh() {
            requestData();
        }

        /**
         * 请求数据 一般有延迟时间
         */
        private void requestData() {
            //显示加载中
            loadingView.show();

            textView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //关闭加载中
                    if (loadingView != null) {
                        loadingView.hide();
                    }
                }
            }, DELAY_MILLIS);
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
