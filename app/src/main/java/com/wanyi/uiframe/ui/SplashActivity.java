package com.wanyi.uiframe.ui;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.wanyi.uiframe.R;
import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.api.model.dto.advise.AppBootDTO;
import com.xuexiang.xui.widget.activity.BaseSplashActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseSplashActivity {

    static final String DATA_KEY = "SplashImage";

    final String TAG = getClass().getName();

    @Override
    protected long getSplashDurationMillis() {
        return 500;
    }

    @Override
    protected void onCreateActivity() {
        initSplashView(R.mipmap.bg_splash);
        ApiFacade.createAdvise().map(iAdviseService -> iAdviseService.appboot().blockingFirst())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> {
                    skipToHome();
                    //Log.e(TAG,throwable.toString());
                })
                .subscribe(item -> {
                    //Log.e(TAG,JSON.toJSONString(item));
                    Glide.with(this).load(item.getAdv_img()).into(new ImageView(this));
                    new Handler().postDelayed(()->{
                        skipToGuide(item);
                    },2*1000);
                });
//        MarketPresenter.getInstance().pushStart(this,aBoolean -> {
//                ApiFacade.createAdvise().map(iAdviseService -> iAdviseService.appboot().blockingFirst()).subscribeOn(Schedulers.io())
//                .doOnError(throwable -> {skipToHome();})
//                .subscribe(item -> { skipToGuide(item); });
//        });

        startSplash(false);
    }


    /**
     * 跳转至引导页面
     */
    private void skipToGuide(AppBootDTO item) {
        Glide.with(this).load(item.getAdv_img());
        Intent intent = new Intent(this, GuideActivity.class);
        intent.putExtra(DATA_KEY, item);
        startActivity(intent);
        finish();
    }


    /**
     * 跳转至主界面
     */
    private void skipToHome() {
        Intent intent = new Intent(this, HomePageActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onSplashFinished() {

    }




}
