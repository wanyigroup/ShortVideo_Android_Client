package com.wanyi.uiframe.usercenter.abs.view;

public interface IVerifyView extends IView {

    /**
     * 验证码获取成功
     */
    void getVerifySuccess();

    /**
     * 验证码获取失败
     */
    void getVerifyFail();

}
