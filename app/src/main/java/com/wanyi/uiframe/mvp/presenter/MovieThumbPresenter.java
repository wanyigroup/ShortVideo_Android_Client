package com.wanyi.uiframe.mvp.presenter;

import android.util.Log;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.api.model.dto.video.VideosDTO;
import com.wanyi.uiframe.api.service.IVideoService;
import com.wanyi.uiframe.dkplayer.adapter.TikTokListAdapter;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.singleton.EnumPreMovie;
import com.wanyi.uiframe.singleton.SingletonPreMovie;

import java.util.ArrayList;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieThumbPresenter implements OnRefreshLoadMoreListener {

    private List<IPreMovieVO> data;
    private TikTokListAdapter tikTokListAdapter;
    private int page = 0;

    public MovieThumbPresenter(List<IPreMovieVO> data, TikTokListAdapter tikTokListAdapter) {
          this.data = data;
          this.tikTokListAdapter = tikTokListAdapter;
    }


    /**
     * 初始化操作
     */
    public void init() {
       initDataSource().map(videosDTOObservable -> {
            List<IPreMovieVO> dataList =  new ArrayList<>();
            for(VideosDTO.DataBean item: videosDTOObservable.blockingFirst().getData()){
                dataList.add(item);
            }
            return dataList;
        }).doOnError(
                throwable -> {
                    Log.e(getClass().getName(),throwable.toString());}
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(proMovieList -> {
            data.addAll(proMovieList);
            tikTokListAdapter.notifyItemRangeChanged(0, data.size());
        });
    }

    /**
     * 初始化数据源
     */
    private  Observable<Observable<VideosDTO>> initDataSource(){
        Observable<IVideoService> serviceObservable = ApiFacade.createVideo();
        Observable<Observable<VideosDTO>> observable = null;
        EnumPreMovie enumPreMovie = SingletonPreMovie.getInstance().getEnumPreMovie();
        switch (enumPreMovie) {
            case follow:
                observable =  serviceObservable.map(iVideoService -> iVideoService.follow(page));
                break;
            case samecity:
                observable =  serviceObservable.map(iVideoService -> iVideoService.location(page));
                break;
            case mycache:
                observable = serviceObservable.map(iVideoService -> iVideoService.follow(page));
                break;
            case myrating:
                observable = serviceObservable.map(iVideoService -> iVideoService.feature(page));
                break;
            case history:
                observable = serviceObservable.map(iVideoService -> iVideoService.follow(page));
                break;
        }
        return observable;
    }




    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        page++;
        int size = data.size();
        initDataSource().map(videosDTOObservable -> {
            List<IPreMovieVO> dataList =  new ArrayList<>();
            for(VideosDTO.DataBean item: videosDTOObservable.blockingFirst().getData()){
                dataList.add(item);
            }
            return dataList;
        }).doOnError(
                throwable -> {
                    Log.e(getClass().getName(),throwable.toString());}
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(proMovieList -> {
            if(proMovieList.size() !=0 ) {
                data.addAll(proMovieList);
                tikTokListAdapter.notifyItemRangeChanged(size, data.size());
            } else {
                page--;
            }
            refreshLayout.finishLoadMore();
        });

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        page = 0;
        initDataSource().map(videosDTOObservable -> {
            List<IPreMovieVO> dataList =  new ArrayList<>();
            for(VideosDTO.DataBean item: videosDTOObservable.blockingFirst().getData()){
                dataList.add(item);
            }
            return dataList;
        }).doOnError(
                throwable -> {
                    Log.e(getClass().getName(),throwable.toString());}
        ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(proMovieList -> {
            data.clear();
            data.addAll(proMovieList);
            tikTokListAdapter.notifyItemRangeChanged(0, data.size());
            refreshLayout.finishRefresh();
        });
    }


}
