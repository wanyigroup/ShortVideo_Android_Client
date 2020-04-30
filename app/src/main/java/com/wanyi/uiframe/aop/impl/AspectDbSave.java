package com.wanyi.uiframe.aop.impl;

import android.util.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class AspectDbSave {

    @Pointcut("execution(* saveLoadRecord(..))")
    public void tikTokDbSave() {

    }

//    @Pointcut("execution(* com.wanyi.uiframe.dkplayer.fragment.TikTokDetailFullFragment.saveLoadRecord(..))")
//    public void tikTokDetailDbSave() {
//
//    }


    @Around("tikTokDbSave()")
    public void handleThrowing(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        try {
            proceedingJoinPoint.proceed();
       }catch (Exception e) {
            Log.e(getClass().getName(),e.toString());
        }
    }





}
