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

import java.util.HashMap;

@Aspect
public class AspectInteverTime {

    HashMap<String,Long> timeCache = new HashMap<>();

    final String TAG = AspectUserLogin.class.getName();

    @Pointcut("execution(@ com.wanyi.uiframe.aop.IntervalTimeTouch * *(..))")
    public void intervalTouch() {

    }


    @Around("intervalTouch()")
    public void aroundTrace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
         String shortString = proceedingJoinPoint.getSignature().toShortString();
         if(timeCache.get(shortString) == null) {
             proceedingJoinPoint.proceed();
             timeCache.put(shortString,System.currentTimeMillis());
         } else {
             if(System.currentTimeMillis() - timeCache.get(shortString)> 5*100) {
                 proceedingJoinPoint.proceed();
                 timeCache.put(shortString,System.currentTimeMillis());
             }
         }
    }



}
