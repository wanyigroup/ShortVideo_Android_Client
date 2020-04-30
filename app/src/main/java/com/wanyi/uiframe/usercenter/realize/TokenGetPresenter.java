package com.wanyi.uiframe.usercenter.realize;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.wanyi.uiframe.eventbus.EUserRefresh;
import com.wanyi.uiframe.usercenter.abs.view.IMineView;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TokenGetPresenter extends BasePresenter<IMineView> {


    @Override
    public void attach(IMineView view) {
        super.attach(view);
        EventBus.getDefault().register(this);
    }

    /**
     * 刷新登录界面视图
     */
    public void get() {
        UserPresenter.getInstance().refreshEvent();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void listenLoginEvent(EUserRefresh eUserRefresh) {
        if(getView() != null){
            getView().finishRefresh();
        }
        switch (eUserRefresh) {
            case notLogin:{
               if(getView() != null) {
                   getView().showLoginDialog();
               }
            }
            break;
            case refreshError:{
               if(getView() != null) {
                   getView().showError("获取用户信息失败,请重试");
               }
            }
            break;
            case refreshLogin:{
                 UserResultDTO userResultDTO = UserPresenter.getInstance().getUserInfo();
                 UserResultDTO.DataBean.UserinfoBean userinfoBean = userResultDTO.getData().getUserinfo();
                 Log.e(getClass().getName(), JSON.toJSONString(userinfoBean) + " isVip:" + (userinfoBean.getVip() == 1));
                 if(getView() != null) {
                     getView().setUserResult(userResultDTO);
                     if(userinfoBean.getVip() == 1) {
                         getView().setVipText(userinfoBean.getVipdate() + "到期");
                         getView().setVipStyle();
                     } else {
                         getView().setVisitorStyle();
                         getView().setVipText("立即加入获得更好的体验");
                     }
                 }
            }
            break;
        }

    }



    @Override
    public void detach() {
        EventBus.getDefault().unregister(this);
        super.detach();
    }






}
