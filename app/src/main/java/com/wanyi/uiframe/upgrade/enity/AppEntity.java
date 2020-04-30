package com.wanyi.uiframe.upgrade.enity;

import com.wanyi.uiframe.upgrade.enity.action.ItemUpdate;

public class AppEntity implements ItemUpdate {


    /**
     * id : 1
     * type : android
     * app_name : 短视频
     * app_desc : 短视频APP
     * change_log : 修复了一些bug
     * update_mode : 0
     * current_version : 1.0.1
     * latest_version : 1.0.2
     * download_url : http://update.afuny.com/index/latest_version
     * released_date : 2019-12-31
     * createtime : 0000-00-00 00:00:00
     * updatetime : 0000-00-00 00:00:00
     * deletetime : null
     * status : 1
     */

    private int id;
    private String type;
    private String app_name;
    private String app_desc;
    private String change_log;
    private String update_mode;
    private String current_version;
    private String latest_version;
    private String download_url;
    private String released_date;
    private String createtime;
    private String updatetime;
    private Object deletetime;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getApp_desc() {
        return app_desc;
    }

    public void setApp_desc(String app_desc) {
        this.app_desc = app_desc;
    }

    public String getChange_log() {
        return change_log;
    }

    public void setChange_log(String change_log) {
        this.change_log = change_log;
    }

    public String getUpdate_mode() {
        return update_mode;
    }

    public void setUpdate_mode(String update_mode) {
        this.update_mode = update_mode;
    }

    public String getCurrent_version() {
        return current_version;
    }

    public void setCurrent_version(String current_version) {
        this.current_version = current_version;
    }

    public String getLatest_version() {
        return latest_version;
    }

    public void setLatest_version(String latest_version) {
        this.latest_version = latest_version;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getReleased_date() {
        return released_date;
    }

    public void setReleased_date(String released_date) {
        this.released_date = released_date;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public Object getDeletetime() {
        return deletetime;
    }

    public void setDeletetime(Object deletetime) {
        this.deletetime = deletetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String getLatestVersion() {
        return latest_version;
    }

    @Override
    public String getLatestApk() {
        return download_url;
    }

    @Override
    public boolean isForce() {
        return "1".equals(update_mode);
    }

    @Override
    public String getAppName() {
        return app_name;
    }

    @Override
    public String getAppDesc() {
        String detail = change_log;
        return detail;
    }

    @Override
    public String getVersionCode() {
        return latest_version;
    }


}
