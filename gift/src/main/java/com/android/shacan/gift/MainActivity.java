package com.android.shacan.gift;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.android.shacan.gift.fragment.OthersFragment;
import com.android.shacan.gift.fragment.WellChosenFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    //存放Frangment数据
    List<Fragment> fragments = new ArrayList<>();
    //存放TabLayout的数据
    List<String> mTitleDatas = new ArrayList<>();


    @BindView(R.id.guide_context_vp)
    ViewPager mViewPager;
    @BindView(R.id.guide_indext_tl)
    TabLayout mTabLayout;
    private MyViewPagerAdapter myViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //1、准备数据源
        setUpData();
        setupTitleDatas();
        //2、创建适配器
        myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        //3、关联适配器

        //ViewPager关联适配器
        mViewPager.setAdapter(myViewPagerAdapter);

        //TabLayout与ViewPager建立关联
        mTabLayout.setupWithViewPager(mViewPager);
    }

    /**
     * 向TabLayout的容器里面添加数据
     */
    private void setupTitleDatas() {
        mTitleDatas.add("精选");
        mTitleDatas.add("海淘");
        mTitleDatas.add("创意生活");
        mTitleDatas.add("送女票");
        mTitleDatas.add("科技范");
    }

    /**
     * 向Fragment的容器里面添加数据
     */
    private void setUpData() {
        fragments.add(WellChosenFragment.newInstance());
        fragments.add(OthersFragment.newInstance());
        fragments.add(OthersFragment.newInstance());
        fragments.add(OthersFragment.newInstance());
        fragments.add(OthersFragment.newInstance());
    }
    /**
     * 创建Fragment的适配器 TabLayout
     * getPageTitle()方法为TabLayout使用
     */
    class MyViewPagerAdapter extends FragmentPagerAdapter{
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 :fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleDatas.get(position);
        }
    }
}