package com.wanyi.uiframe.mvp.presenter.action.factory;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.api.model.dto.video.VideosDTO;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class SameCityMoveAction extends BaseMovieAction {


    @Override
    protected Observable<List<IPreMovieVO>> getPageData(Integer page) {
        Observable<List<IPreMovieVO>> observable = ApiFacade.createVideo().map(iVideoService -> iVideoService.location(page).blockingFirst())
        .map(videosDTO -> {
         List<IPreMovieVO> preMovieVOList = new ArrayList<>();
         for(VideosDTO.DataBean dataBean: videosDTO.getData()){
             preMovieVOList.add(dataBean);
         }
         return preMovieVOList;
         });
        return observable;
    }





}
