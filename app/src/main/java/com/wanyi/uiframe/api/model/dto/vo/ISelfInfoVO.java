package com.wanyi.uiframe.api.model.dto.vo;


/**
 * 个人信息的数据对象
 */
public interface ISelfInfoVO extends IAllowVO {

    /**
     * 获取用户信息
     * @return
     */
    String getUser_nickname();

    /**
     * 获取用户手机号码
     * @return
     */
    String getUser_phone();

    /**
     * 获取用户照片
     * @return
     */
    String getUser_photo();

    /**
     * 用户是否为vip
      * @return
     */
    Boolean user_is_vip();

    /**
     * 用户的级别
     * @return
     */
    Integer getUser_level();

    /**
     * 用户的余额
     * @return
     */
    Integer getUser_balance();

    /**
     * 获取用户id
     * @return
     */
    String getUser_id();


}
