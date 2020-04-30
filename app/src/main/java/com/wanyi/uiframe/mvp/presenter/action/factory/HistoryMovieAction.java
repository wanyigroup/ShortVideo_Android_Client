package com.wanyi.uiframe.mvp.presenter.action.factory;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.wanyi.uiframe.MyApp;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.persistence.HistoryMovieDataDoDao;
import com.wanyi.uiframe.persistence.entity.HistoryMovieDataDo;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;


public class HistoryMovieAction extends BaseMovieAction {


    @Override
    protected Observable<List<IPreMovieVO>> getPageData(Integer page) {
       HistoryMovieDataDoDao historyMovieDataDoDao  = MyApp.getInstance().getDaoSession().getHistoryMovieDataDoDao();
       List<HistoryMovieDataDo> dataList =  historyMovieDataDoDao.queryBuilder().orderDesc(HistoryMovieDataDoDao.Properties.Id).offset((page -1) * 20).limit(20).list();
       List<IPreMovieVO> preMovieVOList = new ArrayList<>();
       for(HistoryMovieDataDo item : dataList) {
           preMovieVOList.add(item);
       }
        return Observable.just(preMovieVOList);
    }


}
