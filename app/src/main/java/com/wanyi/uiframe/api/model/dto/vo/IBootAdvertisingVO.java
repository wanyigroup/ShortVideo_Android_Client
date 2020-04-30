package com.wanyi.uiframe.api.model.dto.vo;


public interface IBootAdvertisingVO {
    
    /**
     * 获取开屏动画
     * @return
     */
    String getBootImage();

    /**
     * 获取广告地址
     * @return
     */
    String getAdvUrl();

    /**
     * 跳转时间
     * @return
     */
    Integer getSkipTime();


}
