package com.wanyi.uiframe.mvp.presenter.callback;

import com.wanyi.uiframe.usercenter.abs.view.IView;

public interface IPlayVipViewCallback extends IView {

    /**
     * vip用户播放
     */
    void videoStartPlay();

    /**
     * 展示登录的对话框
     */
    void showLoadingDialog();

    /**
     * 展示卡密的对话框
     */
    void showBuyCardDialog();

    /**
     * 隐藏下载功能
     */
    void hideDownFunction();

    /**
     * 展示下载功能
     */
    void showDownFunction();

    /**
     *  展示播放错误对话框
     */
     void showErrorTipDialog();


}
