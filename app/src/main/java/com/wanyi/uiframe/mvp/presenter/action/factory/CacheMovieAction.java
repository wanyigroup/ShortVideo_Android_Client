package com.wanyi.uiframe.mvp.presenter.action.factory;

import com.wanyi.uiframe.MyApp;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.persistence.LocalMovieDataDoDao;
import com.wanyi.uiframe.persistence.entity.LocalMovieDataDo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class CacheMovieAction extends BaseMovieAction {


    @Override
    protected Observable<List<IPreMovieVO>> getPageData(Integer page) {
        LocalMovieDataDoDao localMovieDataDoDao = MyApp.getInstance().getDaoSession().getLocalMovieDataDoDao();
        List<LocalMovieDataDo> dataList =  localMovieDataDoDao.queryBuilder().orderDesc(LocalMovieDataDoDao.Properties.Id).offset((page -1) * 20).limit(20).list();
        List<IPreMovieVO> preMovieVOList = new ArrayList<>();
        for(LocalMovieDataDo item : dataList) {
            preMovieVOList.add(item);
        }
        return Observable.just(preMovieVOList);
    }

}
