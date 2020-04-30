package com.wanyi.uiframe.upgrade.enity.action;

public interface ItemUpdate {
    /**
     * 获取最新得版本
     * @return
     */
    String getLatestVersion();

    /**
     * 获取最新得apk
     * @return
     */
    String getLatestApk();

    /**
     * 是否为强制更新
     * @return
     */
    boolean isForce();

    /**
     * app更新名称
     * @return
     */
    String getAppName();

    /**
     * 获取app得描述信息
     * @return
     */
    String getAppDesc();

    /**
     * 获取版本码
     * @return
     */
    String getVersionCode();

}

