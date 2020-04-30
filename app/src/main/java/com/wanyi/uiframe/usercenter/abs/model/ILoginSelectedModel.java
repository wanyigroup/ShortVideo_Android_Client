package com.wanyi.uiframe.usercenter.abs.model;

public interface ILoginSelectedModel {

    //正常
    int NORMAL = 1;

    //需要验证码
    int VERIFY = 2;

    /**
     * 设置选择得页面
     * @param model
     */
    void setSelect(Integer model);

    /**
     * 获取选择得页面
     * @return
     */
    Integer getSelect();


}
