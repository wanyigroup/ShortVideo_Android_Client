package com.example.adhibition.model;

/**
 * 封装ap的基本信息
 * Created by 陈健宇 at 2018/9/29
 */
public class PMAppInfo {

    private String appName;//app名称
    private String pkgName;//所在包名
    private String versionName;//版本名称
    public PMAppInfo(String appName, String pkgName,String versionName) {
        this.appName = appName;
        this.pkgName = pkgName;
        this.versionName = versionName;
    }


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
