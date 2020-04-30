package com.wanyi.uiframe.api.model.dto.update;

import lombok.Data;

@Data
public class UpdateDTO implements IUpdateVO{

    /**
     * apk_name : 短视频
     * apk_desc : 介绍
     * apk_logs : 本次更新修复了一些Bug
     * apk_update_mode : true
     * apk_current_version : 1.1
     * apk_latest_version : 1.2
     * apk_released_date : 20191212
     */
    private String apk_name;
    private String apk_desc;
    private String apk_logs;
    private String apk_update_mode;
    private String apk_current_version;
    private String apk_latest_version;
    private String apk_download_url;
    private String apk_released_date;


    @Override
    public String getAppName() {
        return apk_name;
    }

    @Override
    public String getAppDesc() {
        return apk_desc;
    }

    @Override
    public Boolean isForce() {
        return "true".equals(apk_update_mode);
    }

    @Override
    public String getLink() {
        return apk_download_url;
    }


}
