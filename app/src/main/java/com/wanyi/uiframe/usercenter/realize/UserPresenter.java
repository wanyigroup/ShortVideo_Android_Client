package com.wanyi.uiframe.usercenter.realize;

import android.content.SharedPreferences;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.eventbus.ELoginEvent;
import com.wanyi.uiframe.eventbus.ENotLoginEvent;
import com.wanyi.uiframe.eventbus.EUserRefresh;
import com.wanyi.uiframe.usercenter.abs.presenter.IUserPresenter;
import com.wanyi.uiframe.usercenter.api.UserCenterTokenFactory;
import com.wanyi.uiframe.usercenter.api.model.UserResultDTO;
import com.wanyi.uiframe.usercenter.api.model.action.ITokenResult;
import com.wanyi.uiframe.usercenter.realize.model.TokenRefreshModel;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;

public class UserPresenter implements IUserPresenter {

    /**
     * 用户业务接口
     */
    static UserPresenter userPresenter = null;

    public static  UserPresenter getInstance() {
        if(userPresenter == null) {
            userPresenter = new UserPresenter();
        }
        return userPresenter;
    }

    /**
     * 存储接口
     */
    private SharedPreferences sp;

    /**
     * 用户KEY
     */
    private String USER_KEY = "USER_INFO";

    public void setSp(SharedPreferences sp) {
        this.sp = sp;
    }

    @Override
    public void saveLoadInfo(UserResultDTO userResultDTO) {
        if(userResultDTO == null) {
            sp.edit().putString(USER_KEY,"").commit();
            return;
        }
        UserCenterTokenFactory.setAuthToken(userResultDTO.getToken());
        EventBus.getDefault().post(ELoginEvent.login);
        sp.edit().putString(USER_KEY, JSON.toJSONString(userResultDTO)).commit();
        autoRefreshEvent();
    }

    @Override
    public UserResultDTO getUserInfo() {
        String userInfo = sp.getString(USER_KEY,"");
        if(userInfo.isEmpty()) {
            return null;
        }
        return JSON.parseObject(userInfo,UserResultDTO.class);
    }

    @Override
    public void userLoad() {
       UserResultDTO userResultDTO = getUserInfo();
        Log.e(getClass().getName(),"userLoad:" + (userResultDTO == null));
       if(userResultDTO == null) {
           return;
       }
       ApiFacade.createUserService().token_user_info(userResultDTO.getToken())
               .doOnError(throwable -> {
               }).subscribeOn(Schedulers.io()).subscribe(entity -> {
                   if(entity.isSuccess()) {
                       saveLoadInfo(entity);
                   }
       });
    }

    @Override
    public void exitLoad() {
        UserCenterTokenFactory.setAuthToken(null);
        saveLoadInfo(null);
        EventBus.getDefault().post(ELoginEvent.logout);
        if(executor != null) {
            executor.shutdown();
        }
        executor = null;
    }

    @Override
    public void refreshEvent() {
        UserResultDTO userResultDTO = getUserInfo();
        if(userResultDTO == null) {
            EventBus.getDefault().post(EUserRefresh.notLogin);
            return;
        }
        ApiFacade.createUserService().token_user_info(userResultDTO.getToken())
                .doOnError(throwable -> {
                   EventBus.getDefault().post(EUserRefresh.refreshError);
                }).subscribeOn(Schedulers.io()).subscribe(entity -> {
            if(entity.isSuccess()) {
                EventBus.getDefault().post(EUserRefresh.refreshLogin);
                saveLoadInfo(entity);
            }
        });
    }

    ScheduledThreadPoolExecutor executor = null;
    private void autoRefreshEvent() {
        if(executor != null) {
            executor.shutdown();
        }
        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> {
            TokenRefreshModel tokenRefreshModel = new TokenRefreshModel();
            tokenRefreshModel.getResult().subscribe(loadResult -> {
                if(loadResult.isSuccess()) {
                    ITokenResult tokenResult = loadResult;
                    String token = tokenResult.getToken();
                    UserResultDTO resultDTO = getUserInfo();
                    if(resultDTO != null) {
                        resultDTO.getData().getUserinfo().setToken(token);
                        saveLoadInfo(resultDTO);
                    }
                    UserCenterTokenFactory.setAuthToken(token);
                }
            });
        },1,1, TimeUnit.HOURS);
    }



}
