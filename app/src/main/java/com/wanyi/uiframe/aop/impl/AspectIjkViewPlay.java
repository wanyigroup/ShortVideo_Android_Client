package com.wanyi.uiframe.aop.impl;


import com.wanyi.uiframe.eventbus.EIJKPlayEvent;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.greenrobot.eventbus.EventBus;

@Aspect
public class AspectIjkViewPlay {

    @Pointcut("execution(* com.wanyi.uiframe.dkplayer.widget.component.TikTokView.*Start())")
    public void ijkPlay() {

    }

    @Around("ijkPlay()")
    public void handleThrowing(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        //Log.e(getClass().getName(),"ijkPlay");
        EventBus.getDefault().post(EIJKPlayEvent.play);
        //proceedingJoinPoint.proceed();
    }



}
