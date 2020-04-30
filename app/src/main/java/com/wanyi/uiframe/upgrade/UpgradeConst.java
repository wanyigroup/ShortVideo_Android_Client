package com.wanyi.uiframe.upgrade;

import com.wanyi.uiframe.upgrade.enity.action.ItemUpdate;

public class UpgradeConst {

    /**
     * app更新
     */
    static ItemUpdate itemUpdate;
    public static void setUpdate(ItemUpdate itemUpdate) {
        UpgradeConst.itemUpdate = itemUpdate;
    }

    public static ItemUpdate getItemUpdate() {
        return itemUpdate;
    }

    //    //获取app路径的地址
//    public static String check_url = UriConst.URI_DEFAULT + "/update/check_version";
//    //app更新地址
//    public static String upgrade_url = "http://192.168.56.1:1111/update/apk/";
//    //app版本号
//    public static String upgrade_version = "1.0";
//    //app更新得模式
//    public static Integer upgrade_mode = 0;
//    //app更新名称
//    public static String upgrade_app_title = "";
//    //app更新内容
//    public static String upgade_app_content = "";




}
