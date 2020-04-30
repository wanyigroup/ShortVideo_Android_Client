package com.wanyi.uiframe.usercenter.realize.subscribe;

import com.wanyi.uiframe.eventbus.ELoginResult;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.api.model.action.ITokenResult;
import com.wanyi.uiframe.usercenter.realize.UserPresenter;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.functions.Consumer;

public class UserResultSubscribe implements Consumer<UserResultDTO> {


    @Override
    public void accept(UserResultDTO userResultDTO) throws Exception {
        if(userResultDTO.isSuccess()) {
            successOperation(userResultDTO);
        } else {
            failOperation(userResultDTO);
        }
    }

    /**
     * 用户登录成功操作
     * @param userResultDTO 数据模型
     */
    public void successOperation(UserResultDTO userResultDTO) {
        UserPresenter.getInstance().saveLoadInfo(userResultDTO);
        sendSuccessEvent();
    }

    /**
     * 发送登录事件
     */
    public void sendSuccessEvent() {
        EventBus.getDefault().post(ELoginResult.SUCCESS);
    }

    /**
     * 用户登录失败的操作
     * @param userResultDTO 数据模型
     */
    public void failOperation(UserResultDTO userResultDTO) {

    }







}
