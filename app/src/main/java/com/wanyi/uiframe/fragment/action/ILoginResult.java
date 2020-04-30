package com.wanyi.uiframe.fragment.action;

public interface ILoginResult {

    /**
     * 登录失败
     */
    void loginError(String error);

    /**
     * 登录成功
     */
    void loginRight();

}
