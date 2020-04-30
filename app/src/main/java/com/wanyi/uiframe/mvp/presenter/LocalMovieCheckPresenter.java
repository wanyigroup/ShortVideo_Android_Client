package com.wanyi.uiframe.mvp.presenter;


import android.Manifest;

import androidx.fragment.app.FragmentActivity;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.wanyi.uiframe.MyApp;
import com.wanyi.uiframe.persistence.LocalMovieDataDoDao;
import com.wanyi.uiframe.persistence.entity.LocalMovieDataDo;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

import io.reactivex.functions.Consumer;


public class LocalMovieCheckPresenter {

   WeakReference<FragmentActivity> weakReference;

   public void attach(FragmentActivity fragmentActivity) {
       weakReference = new WeakReference<>(fragmentActivity);
   }

   public void deatch() {
       if(weakReference != null) {
           weakReference.clear();
       }
       weakReference = null;
   }


    /**
     * 检查权限，如果没有读写权限则不使用该功能
     */
    public void checkPermission(Consumer<Boolean> next) throws Exception {
        if(weakReference.get() == null)
            next.accept(false);
        RxPermissions rxPermissions = new RxPermissions(weakReference.get());
        rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(next);
    }


    /**
     * 同步本地记录
     */
    public void synLocalRecord() {

      LocalMovieDataDoDao localMovieDataDoDao = MyApp.getInstance().getDaoSession().getLocalMovieDataDoDao();
      List<LocalMovieDataDo> localSource = localMovieDataDoDao.loadAll();
      for(LocalMovieDataDo item: localSource) {
         String localUrl = item.getLocalUrl();
         File file = new File(localUrl);
         if(!file.exists()) {
             localMovieDataDoDao.delete(item);
         }
      }
    }













}
