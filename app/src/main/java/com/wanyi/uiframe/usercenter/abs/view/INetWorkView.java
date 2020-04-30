package com.wanyi.uiframe.usercenter.abs.view;

import android.content.Context;

public interface INetWorkView extends IView {

    /**
     * 没有网络
     */
    void noNetWork();

    /**
     * 处于运营商网络
     */
    void mobileNetWork();

    /**
     * 存在wifi网络
     */
    void wifiNetWork();

    /**
     * 获取上下文，用来注册广播
     * @return 上下文
     */
    Context getContext();

}
