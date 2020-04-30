package com.wanyi.uiframe.usercenter.api.model.action;

public interface IUserResult {

    /**
     * 获取id
     * @return
     */
    Integer getId();

    /**
     * 获取用户名称
     * @return
     */
    String getNickName();

    /**
     * 获取手机号码
     * @return
     */
    String getMobile();

    /**
     * 获取头像
     * @return
     */
    String getAvatarStr();

    /**
     * 获取积分
     * @return
     */
    Integer getScore();

    /**
     * 获取用户id
     * @return
     */
    Integer getUserId();

    /**
     * 设置等级
     * @return
     */
    Integer getLevel();


    /**
     * 获取金额
     * @return
     */
    String getMoney();


    /**
     * 获取个性签名
     * @return
     */
    String getBio();

}
