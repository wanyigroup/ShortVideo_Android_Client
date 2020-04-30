package com.wanyi.uiframe.usercenter.abs.presenter;

import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;

public interface IUserPresenter {

    /**
     * 事件是业务的起点，行为是对象的状态更新
    */

    /**
     * 存储登录信息
     * @param userResultDTO 用户信息
     */
    void saveLoadInfo(UserResultDTO userResultDTO);

    /**
     * 获取用户信息
     * @return 用户信息
     */
    UserResultDTO getUserInfo();

    /**
     * 用户登录
     */
    void userLoad();

    /**
     * 删除登录信息
     */
    void exitLoad();

    /**
     * 刷新用户信息，用在卡券升级上
     */
    void refreshEvent();


}
