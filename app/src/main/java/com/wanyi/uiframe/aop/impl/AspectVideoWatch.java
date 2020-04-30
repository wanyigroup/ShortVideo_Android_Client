package com.wanyi.uiframe.aop.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.wanyi.uiframe.eventbus.ENotLoginEvent;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.greenrobot.eventbus.EventBus;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;


@Aspect
public class AspectVideoWatch {

    final static int maxCount = 9999;
    final String TAG = AspectVideoWatch.class.getName();
    //观看次数
    final static String WATCH_KEY = "watch";
    static SharedPreferences sp;

    //上下文得弱引用
    static WeakReference<Context> weakReference;
    public static void hold(Context context) {
        weakReference = new WeakReference<>(context);
        sp = context.getSharedPreferences(WATCH_KEY,Context.MODE_PRIVATE);
    }


    
    @Pointcut("execution(@ com.wanyi.uiframe.aop.VideoWatch * *(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public void aroundTrace(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Log.d(TAG,"num:" +getSurplusCount());
       if(AspectUserLogin.getToken() != null) {
           proceedingJoinPoint.proceed();
       } else {
           proceedingJoinPoint.proceed();
           if(getSurplusCount() > 0) {
               reduceDown();
           } else {
               if(weakReference.get() != null) {
                   MaterialDialog.Builder builder = new MaterialDialog.Builder(weakReference.get());
                   builder.title("温馨提示").content("系统检测到您还未登录奥，赶紧登录吧！").negativeText("知道了")
                           .positiveText("立即登录")
                           .onPositive((dialog, which) -> {
                               EventBus.getDefault().post(ENotLoginEvent.NO_LOGIN);
                           })
                           .show();
               }
           }
       }
    }

    /**
     * 获取剩余次数
     * @return
     */
    public static Integer getSurplusCount(){
         Integer surplus = sp.getInt(getKey(),maxCount);
         return surplus;
    }

    /**
     * 减少视频观看次数
     * @return
     */
    private void reduceDown() {
        Integer surplus =  sp.getInt(getKey(),maxCount);
        sp.edit().putInt(getKey(),surplus - 1).commit();
    }


    /**
     * 获取观看次数得key
     * @return
     */
    private static String getKey() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String key = df.format(new Date());
        return key;
    }












}
