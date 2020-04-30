package com.wanyi.uiframe.usercenter.abs.view;

import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

public interface IPersonalView extends IView {

    /**
     * 设置个性签名
     * @param bio
     */
    void setBio(String bio);

    /**
     * 设置昵称
     * @param nickName
     */
    void setNickName(String nickName);

    /**
     * 设置手机号码
     * @param mobileNumber
     */
    void setMobile(String mobileNumber);

    /**
     * 显示交互对话框
     * @param title 标题
     * @param context 内容
     * @param callback 回调
     */
    void showDialog(String title, String context, MaterialDialog.SingleButtonCallback callback);
}
