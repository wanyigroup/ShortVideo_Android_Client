package com.wanyi.uiframe.api.model.dto.vo;

public interface IAnnouncementVO {

    /**
     * 获取某id
     * @return
     */
    Long getId();

    /**
     * 获取图片路径
     * @return
     */
    String getUri();

    /**
     * 获取标题
     * @return
     */
    String getTitle();

    /**
     * 获取内容
     * @return
     */
    String getContent();

    /**
     * 获取时间
     * @return
     */
    String getDate();

}
