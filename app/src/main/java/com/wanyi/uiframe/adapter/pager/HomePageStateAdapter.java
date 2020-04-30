package com.wanyi.uiframe.adapter.pager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;

public class HomePageStateAdapter  extends FragmentPagerAdapter {

    /**
     * 帧布局
     */
    List<Fragment> fragmentList;
    /**
     * 标题
     */
    String[] titles = new String[]{"关注","推荐"};

    public HomePageStateAdapter(@NonNull FragmentManager fm, int behavior,List<Fragment> fragmentList) {
        super(fm, behavior);
        this.fragmentList = fragmentList;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
