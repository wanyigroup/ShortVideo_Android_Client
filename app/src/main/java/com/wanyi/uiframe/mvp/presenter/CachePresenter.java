package com.wanyi.uiframe.mvp.presenter;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.wanyi.uiframe.MyApp;
import com.wanyi.uiframe.dkplayer.util.cache.ProxyVideoCacheManager;
import com.wanyi.uiframe.persistence.entity.LocalMovieDataDo;
import com.wanyi.uiframe.utlis.StorageCleanUtils;
import java.io.File;
import java.util.List;

public class CachePresenter {


    static CachePresenter cachePresenter;

    public static CachePresenter getCachePresenter() {
        if(cachePresenter == null) {
            cachePresenter = new CachePresenter();
        }
        return cachePresenter;
    }

    private CachePresenter(){}

    private Context context;
    private File glideCache,videoCache;
    public void attach(Context context) {
        this.context = context;
        glideCache = Glide.getPhotoCacheDir(context);
        videoCache = ProxyVideoCacheManager.getProxy(context).getCacheRoot();
    }

    /**
     * 获取缓存大小
     * @return
     */
    public String getCacheSize() {
        Long m3u8Size = getM3U8Size();
        Long glideSize = StorageCleanUtils.getFolderSize(glideCache);
        Long videoSize = StorageCleanUtils.getFolderSize(videoCache);
        Long totalSize = m3u8Size + glideSize + videoSize;
        if(totalSize == 0)
            return "已清空";
        String totalFormat = StorageCleanUtils.getFormatSize(totalSize);
        return totalFormat;
    }

    /**
     * 清除缓存
     */
    public void clear(){
        StorageCleanUtils.deleteFolderFile(glideCache.getPath(),false);
        StorageCleanUtils.deleteFolderFile(videoCache.getPath(),false);
        clearM3U8Size();
    }

    /**
     * 获取m3u8文件大小
     * @return
     */
    private Long getM3U8Size() {
        List<LocalMovieDataDo> dataList = MyApp.getInstance().getDaoSession().getLocalMovieDataDoDao().loadAll();
        Long total = 0L;
        for(LocalMovieDataDo item:dataList) {
            long size = new File(item.getLocalUrl()).length();
            total += size;
        }

        return total;
    }

    /**
     * 清空m3u8文件大小
     */
    private void clearM3U8Size() {
        List<LocalMovieDataDo> dataList = MyApp.getInstance().getDaoSession().getLocalMovieDataDoDao().loadAll();
        for(LocalMovieDataDo item:dataList) {
            File file = new File(item.getLocalUrl());
            file.delete();
        }
        MyApp.getInstance().getDaoSession().getLocalMovieDataDoDao().deleteAll();
    }








}
