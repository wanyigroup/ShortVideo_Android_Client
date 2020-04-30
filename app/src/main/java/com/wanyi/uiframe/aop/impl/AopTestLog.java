package com.wanyi.uiframe.aop.impl;

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopTestLog {

    @Pointcut("execution(* com.wanyi.uiframe.fragment.MineTabFragment.*(..))")
    public void fullResumeFragment() {

    }

    @Pointcut("execution(* com.wanyi.uiframe.dkplayer.fragment..*.onPause*(..))")
    public void fullPauseFragment() {

    }

    @Pointcut("execution(* com.wanyi.uiframe.dkplayer.fragment..*.onDetach*(..))")
    public void detachFragment() {

    }

    @Around("fullResumeFragment()")
    public void fullFragmentProxy(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Log.e(getClass().getName(),proceedingJoinPoint.getSignature().toLongString() );
        proceedingJoinPoint.proceed();
    }

   @Pointcut("execution(* com.wanyi.uiframe.fragment.*.*(..))")
   public void liftCycle() {

   }

   @Around("liftCycle()")
   public void liftCycleProxy(ProceedingJoinPoint joinPoint) throws  Throwable {
         Log.d(getClass().getName(),"lift:" + joinPoint.getSignature().toLongString());
        joinPoint.proceed();
   }












}
