package com.wanyi.uiframe.mvp.presenter.action.factory;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.api.model.dto.video.VideosDTO;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.mvp.presenter.DynamicMoviePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class FeatureMovieAction extends BaseMovieAction {
   /**
    * 获取单例对象
    * */
   static FeatureMovieAction featureMovieAction;
   public static FeatureMovieAction getInstance() {
       if(featureMovieAction == null) {
           featureMovieAction = new FeatureMovieAction();
       }
       return featureMovieAction;
   }

   private FeatureMovieAction() {

   }


    @Override
    protected Observable<List<IPreMovieVO>> getPageData(Integer page) {

        Observable<List<IPreMovieVO>> observable = ApiFacade.createVideo().map(iVideoService -> iVideoService.feature(page).blockingFirst())
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
