package com.wanyi.uiframe.mvp.presenter;

import com.wanyi.uiframe.api.model.dto.vo.IVipVO;
import com.wanyi.uiframe.mvp.presenter.callback.IPlayVipViewCallback;
import com.wanyi.uiframe.usercenter.api.UserCenterTokenFactory;
import com.wanyi.uiframe.usercenter.realize.BasePresenter;
import com.wanyi.uiframe.usercenter.realize.UserPresenter;


public class PlayVipPresenter extends BasePresenter<IPlayVipViewCallback> {


    public void vipPlay(IVipVO vipVO) {
        //如果用户token为空说明未登录
        String token = UserCenterTokenFactory.getAuthToken();
        //用户未登录，且视频为非
        if(token == null) {
            if(getView() != null) {
                getView().hideDownFunction();
            }
            if(vipVO.videoIsVip()) {
                if(getView() != null) {
                    getView().showLoadingDialog();
                }
            } else {
                if(getView() != null) {
                    getView().videoStartPlay();
                }
            }
            return;
        }

        if(token != null) {
            int vip = UserPresenter.getInstance().getUserInfo().getData().getUserinfo().getVip();
            if(vip == 1) {
                //如果是vip
               if(getView() != null){
                   getView().showDownFunction();
                   getView().videoStartPlay();
               }
            }else {
                //如果不是vip用户
                if(getView() != null) {
                    getView().hideDownFunction();
                    if (vipVO.videoIsVip()) {
                        //如果是vip视频
                        getView().showBuyCardDialog();
                    } else {
                        //如果不是vip视频
                        getView().videoStartPlay();
                    }
                }


            }
        }



    }









}
