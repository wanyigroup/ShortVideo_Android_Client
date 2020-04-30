package com.wanyi.uiframe.dkplayer.activity.tiktok;

import android.os.Bundle;
import android.view.View;

import com.dueeeke.videoplayer.player.VideoViewManager;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.base.BaseActivity;
import com.wanyi.uiframe.dialog.impl.UserLoadDialog;
import com.wanyi.uiframe.dkplayer.fragment.TikTokListFragment;
import com.wanyi.uiframe.eventbus.ENotLoginEvent;
import com.wanyi.uiframe.eventbus.EUserUpgradeEvent;
import com.wanyi.uiframe.fragment.container.RegisterFragment1;
import com.wanyi.uiframe.fragment.container.ResetPswFragment1;
import com.wanyi.uiframe.fragment.container.SearchFragment;
import com.wanyi.uiframe.fragment.container.web.impl.CouponWebFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;


public class SearchTikTokActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_tiktok_search;
    }

    @OnClick({R.id.icon_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_back: {
                finish();
            }
            break;
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
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        EventBus.getDefault().register(this);
        super.onResume();
    }

    @Override
    public void finish() {
        setResult(SearchFragment.SEARCH_RESULT_CODE);
        super.finish();
    }

    @Override
    protected void onDestroy() {
        VideoViewManager.instance().releaseVideoPlayer();
        super.onDestroy();
    }



}
