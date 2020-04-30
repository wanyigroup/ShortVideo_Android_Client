package com.wanyi.uiframe.fragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.adapter.pager.HomePageStateAdapter;
import com.wanyi.uiframe.base.BaseFragment;
import com.wanyi.uiframe.dkplayer.fragment.FeatureTikTokFullFragment;
import com.wanyi.uiframe.dkplayer.fragment.TikTokListFragment;
import com.wanyi.uiframe.dkplayer.fragment.factorylist.FollowTikTokListFragment;
import com.wanyi.uiframe.eventbus.EFullScreenEvent;
import com.wanyi.uiframe.fragment.container.SearchFragment;
import com.wanyi.uiframe.mvp.presenter.DynamicMoviePresenter;
import com.wanyi.uiframe.mvp.presenter.callback.IMovieCallback;
import com.wanyi.uiframe.singleton.EnumPreMovie;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

@Page(name = "首页",anim = CoreAnim.fade)
public class HomePageTabFragment extends BaseFragment implements ViewPager.OnPageChangeListener {


    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    HomePageStateAdapter homePageStateAdapter;

    @Override
    protected TitleBar initTitle() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_home;
    }

    @Override
    protected void initViews() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FollowTikTokListFragment());
        fragmentList.add(new FeatureTikTokFullFragment());
        homePageStateAdapter = new HomePageStateAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragmentList);
        viewPager.setAdapter(homePageStateAdapter);
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {
        if (position == 0) {
            DynamicMoviePresenter.getInstance().setDataSource(EnumPreMovie.follow);
            EventBus.getDefault().post(EFullScreenEvent.normalScreen);
        } else {
            EventBus.getDefault().post(EFullScreenEvent.fullScreen);
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @OnClick(R.id.bt_search)
    public void onClick() {
         openNewPage(SearchFragment.class);
    }



    @Override
    public void onResume() {


        super.onResume();
    }

    @Override
    public void onPause() {

        super.onPause();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
