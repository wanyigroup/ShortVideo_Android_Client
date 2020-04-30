package com.wanyi.uiframe.usercenter.abs.view;


import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;

public interface IMineView extends IView {

    /**
     * 设置用户结果
     * @param userResult 用户信息结果
     */
    void setUserResult(UserResultDTO userResult);

    /**
     * 展示错误提示
     * @param error 错误文本
     */
    void showError(String error);

    /**
     * 设置vip按钮上的文字
     * @param vipText
     */
    void setVipText(String vipText);

    /**
     * 停止刷新
     */
    void finishRefresh();

    /**
     * 设置vip样式
     */
    void setVipStyle();

    /**
     * 设置游客样式
     */
    void setVisitorStyle();

    /**
     * 展示登录的对话框
     */
    void showLoginDialog();

}
