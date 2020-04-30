package com.wanyi.uiframe.api.model.dto.vo;

public interface IAllowVO {

    /**
     * 是否允许登录
     * @return
     */
    boolean isAllow();

    /**
     * 消息
     */
    String getMsg();

}
