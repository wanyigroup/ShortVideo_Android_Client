package com.wanyi.uiframe.mvp.presenter.action.factory;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.api.model.dto.video.VideosDTO;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.mvp.presenter.action.ISearchMovieAction;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class SearchMovieAction extends BaseMovieAction implements ISearchMovieAction {


   static SearchMovieAction instance = null;
    /**
     * 获取单例,同步数据操作
     */
   public synchronized static ISearchMovieAction getInstance(){
       if(instance == null) {
           instance = new SearchMovieAction();
       }
       return instance;
   }

   private SearchMovieAction() {

   }
    String keyword;
    @Override
    public void searchKeyword(String keyword) {
        this.keyword = keyword;
        refresh();
    }

    @Override
    protected Observable<List<IPreMovieVO>> getPageData(Integer page) {
       Observable<List<IPreMovieVO>> observable = ApiFacade.createVideo().map(iVideoService -> iVideoService.keyword_search(keyword,page)).map(videosDTOObservable -> videosDTOObservable.blockingFirst())
               .map(videosDTO -> {
                   List<IPreMovieVO> dataList = new ArrayList<>();
                   List<VideosDTO.DataBean> dataBeans = videosDTO.getData();
                   for(IPreMovieVO item:dataBeans) {
                       dataList.add(item);
                   }
                   return dataList;
               });
        return observable;
    }

    @Override
    public void refresh() {
        if(keyword == null) {
            callback.onEmpty();
            return;
        }
        super.refresh();
    }
}
