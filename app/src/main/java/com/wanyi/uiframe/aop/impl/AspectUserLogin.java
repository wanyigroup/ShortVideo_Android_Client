package com.wanyi.uiframe.aop.impl;

import android.graphics.Paint;
import android.os.Handler;
import android.util.Log;

import com.wanyi.uiframe.eventbus.ENotLoginEvent;
import com.wanyi.uiframe.usercenter.api.UserCenterTokenFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.greenrobot.eventbus.EventBus;

@Aspect
public class AspectUserLogin {


    final String TAG = AspectUserLogin.class.getName();

    @Pointcut("execution(@ com.wanyi.uiframe.aop.AopUserLogin * *(..))")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before(Paint.Join point) {
        Log.d(TAG,"before");
    }

    @Around("pointcut()")
    public void aroundTrace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

         if(UserCenterTokenFactory.getAuthToken() == null) {
             new Handler().postDelayed(() -> {
                 EventBus.getDefault().post(ENotLoginEvent.NO_LOGIN);
             },500);
         } else {
             proceedingJoinPoint.proceed();
         }
    }




    public static String getToken() {
        return UserCenterTokenFactory.getAuthToken();
    }

}
