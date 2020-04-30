package com.wanyi.uiframe.ui;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.dueeeke.videoplayer.player.VideoViewManager;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.adapter.pager.HomeStateAdapter;
import com.wanyi.uiframe.base.BaseActivity;
import com.wanyi.uiframe.dialog.impl.UserLoadDialog;
import com.wanyi.uiframe.eventbus.EFirstTabDbEvent;
import com.wanyi.uiframe.eventbus.EMessageNotify;
import com.wanyi.uiframe.eventbus.ENotLoginEvent;
import com.wanyi.uiframe.eventbus.EUpdateFileSize;
import com.wanyi.uiframe.eventbus.EUserUpgradeEvent;
import com.wanyi.uiframe.fragment.HomePageTabFragment;
import com.wanyi.uiframe.fragment.MessageTabFragment;
import com.wanyi.uiframe.fragment.MineTabFragment;
import com.wanyi.uiframe.fragment.SameCityTabFragment;
import com.wanyi.uiframe.fragment.container.RegisterFragment1;
import com.wanyi.uiframe.fragment.container.ResetPswFragment1;
import com.wanyi.uiframe.fragment.container.web.impl.CouponWebFragment;
import com.wanyi.uiframe.mvp.presenter.DynamicMoviePresenter;
import com.wanyi.uiframe.singleton.EnumPreMovie;
import com.wanyi.uiframe.upgrade.UpgradeAction;
import com.wanyi.uiframe.usercenter.abs.view.IHPFullScreenView;
import com.wanyi.uiframe.usercenter.abs.view.INoticeDotView;
import com.wanyi.uiframe.usercenter.realize.NoticeDotPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;

public class HomePageActivity extends BaseActivity implements OnTabSelectListener, INoticeDotView, IHPFullScreenView {

    //响应的间隔事件
    final int INTERVAL_TIME = 3*1000;
    //加载系统通知
    NoticeDotPresenter noticeDotPresenter = new NoticeDotPresenter();
    @BindView(R.id.mTabLayout)
    CommonTabLayout mTabbar;
    @BindView(R.id.bottom_placeholder)
    View bottomPlaceHolder;
    //切换ViewPager
    @BindView(R.id.fragment_container)
    ViewPager bottomVp;

    //切换的帧布局名称
    final Class[] cla = new Class[]{HomePageTabFragment.class, SameCityTabFragment.class,  MessageTabFragment.class, MineTabFragment.class};
    //创建时间
    Long crateTime = System.currentTimeMillis();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomePageTabFragment());
        fragments.add(new SameCityTabFragment());
        fragments.add(new MessageTabFragment());
        fragments.add(new MineTabFragment());
        HomeStateAdapter homeStateAdapter = new HomeStateAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,fragments);
        bottomVp.setAdapter(homeStateAdapter);
        bottomVp.setOffscreenPageLimit(4);
        mTabbar.setTabData(new TabItem().getTabItems());
        mTabbar.setOnTabSelectListener(this);
        mTabbar.setCurrentTab(0);
        onTabSelect(0);
        UpgradeAction.showUpgradeDialog(this,true);
        noticeDotPresenter.attach(this);
        noticeDotPresenter.showNotRead();
       // DynamicMoviePresenter.getInstance().setDataSource(EnumPreMovie.follow);
    }



    @Override
    protected int getLayoutId() {
        return R.layout.activity_homepage;
    }

    @Override
    public void onTabSelect(int index) {
        VideoViewManager.instance().pause();
        bottomVp.setCurrentItem(index);
        EventBus.getDefault().post(EUpdateFileSize.change);
    }

    @Override
    public void onTabReselect(int index) {
        if(index == 0) {
            EventBus.getDefault().post(EFirstTabDbEvent.dbClick);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void skipLogin(ENotLoginEvent eLoginEvent) {
        UserLoadDialog userLoadDialog = new UserLoadDialog();
        userLoadDialog.show(getSupportFragmentManager(),userLoadDialog.getFmTag());
        userLoadDialog.setCallBack(new UserLoadDialog.CallBack() {
            @Override
            public void skipRegister() {
                openNewPage(RegisterFragment1.class);
            }

            @Override
            public void skipFind() {
                openNewPage(ResetPswFragment1.class);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void skipUpgrade(EUserUpgradeEvent event) {
        openNewPage(CouponWebFragment.class);
    }


    @Override
    protected void onPause() {
        super.onPause();
        VideoViewManager.instance().pause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(EUpdateFileSize.change);
    }

    @Override
    public void displayNotRead(int num) {
        mTabbar.showDot(2);
        //mTabbar.showMsg(2,num);
    }

    @Override
    public void hideDot() {
        mTabbar.hideMsg(2);
    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(System.currentTimeMillis() - crateTime < INTERVAL_TIME) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageNotify(EMessageNotify eMessageNotify) {
         noticeDotPresenter.showNotRead();
    }


    @Override
    public void fullScreen() {
        //mTabbar.setBackgroundColor(Color.TRANSPARENT);
        //bottomPlaceHolder.setVisibility(View.VISIBLE);
        //TintBar.makeStatusBarTransparent(this);

    }

    @Override
    public void normalScreen() {
        //bottomPlaceHolder.setVisibility(View.VISIBLE);
        //mTabbar.setBackgroundColor(Color.BLACK);
        //TintBar.clearStatusBarTransparent(this,getAttrColor(R.attr.xui_actionbar_color));
    }


    /**
     * 获取属性色值
     * @param attr 属性
     * @return
     */
    private int getAttrColor(int attr) {
        int defaultColor = 0xFF000000;
        int[] attrsArray = {attr};
        TypedArray typedArray = obtainStyledAttributes(attrsArray);
        int resourceId = typedArray.getColor(0,defaultColor);
        typedArray.recycle();
        return resourceId;
    }


    /**
     * 子选项
     */
    private  class TabItem implements CustomTabEntity {

        public  final int[] mTitles = {R.string.tab3, R.string.tab2,  R.string.tab4,R.string.tab5};
        public  final int[] mSelectIcons = {R.mipmap.main_tab_homepage_selected, R.mipmap.main_tab_samecity_selected,  R.mipmap.main_tab_message_selected,R.mipmap.main_tab_mine_selected};
        public  final int[] mNormalIcons = { R.mipmap.main_tab_homepage_un_selected, R.mipmap.main_tab_samecity_un_selected, R.mipmap.main_tab_message_un_selected,R.mipmap.main_tab_mine_un_selected};

        public  ArrayList<CustomTabEntity> getTabItems(){
            ArrayList<CustomTabEntity> list = new ArrayList<>();
            for(int i = 0; i < mTitles.length; i++) {
                TabItem  tabItem = new TabItem(getString(mTitles[i]),mSelectIcons[i],mNormalIcons[i]);
                list.add(tabItem);
            }
            return list;
        }



        private String title;
        private int selectIcon;
        private int unSelectIcon;

        public TabItem() {

        }

        public TabItem(String title,int selectIcon,int unSelectIcon) {
                this.title = title;
                this.selectIcon = selectIcon;
                this.unSelectIcon = unSelectIcon;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectIcon;
        }

    }



}
