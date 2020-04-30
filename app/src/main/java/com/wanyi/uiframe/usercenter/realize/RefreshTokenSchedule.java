package com.wanyi.uiframe.usercenter.realize;

import com.wanyi.uiframe.usercenter.api.UserCenterTokenFactory;
import com.wanyi.uiframe.usercenter.api.model.action.ITokenResult;
import com.wanyi.uiframe.usercenter.realize.model.LogoutModel;
import com.wanyi.uiframe.usercenter.realize.model.TokenRefreshModel;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RefreshTokenSchedule {

    static RefreshTokenSchedule schedule;

    public static RefreshTokenSchedule getSchedule() {
        if(schedule == null) {
         schedule = new RefreshTokenSchedule();
        }
        return schedule;
    }

    private RefreshTokenSchedule() {

    }

    ScheduledThreadPoolExecutor executor = null;
    /**
     * 执行
     */
    public synchronized void execute() {
        if(executor != null) {
           shutdown();
        }
        executor = new ScheduledThreadPoolExecutor(1);
        executor.scheduleAtFixedRate(() -> {
            TokenRefreshModel tokenRefreshModel = new TokenRefreshModel();
            tokenRefreshModel.getResult().subscribe(loadResult -> {
                if(loadResult.isSuccess()) {
                    ITokenResult tokenResult = (ITokenResult) loadResult;
                    String token = tokenResult.getToken();
                    UserCenterTokenFactory.setAuthToken(token);
                }
            });
        },1,1,TimeUnit.HOURS);
    }

    /**
     * 停止
     */
    public void shutdown() {
        if(executor != null) {
            executor.shutdown();
            LogoutModel logoutModel = new LogoutModel();
            logoutModel.getResult().subscribe(loadResult -> {});
        }
        executor = null;
    }


}
