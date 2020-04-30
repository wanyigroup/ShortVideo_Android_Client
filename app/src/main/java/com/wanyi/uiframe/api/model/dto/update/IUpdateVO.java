package com.wanyi.uiframe.api.model.dto.update;

import java.io.Serializable;

public interface IUpdateVO extends Serializable {

    /**
     * 获取APP名称
     * @return
     */
    String getAppName();

    /**
     * 获取APP描述功能
     * @return
     */
    String getAppDesc();

    /**
     * 是否是强制更新
     * @return
     */
    Boolean isForce();

    /**
     * 获取链接地址
     * @return
     */
    String getLink();

}
