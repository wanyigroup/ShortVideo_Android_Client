package com.wanyi.uiframe.mvp.presenter;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.wanyi.uiframe.MyApp;
import com.wanyi.uiframe.api.model.dto.vo.ISearchHistoryVO;
import com.wanyi.uiframe.mvp.presenter.callback.ISearchHistoryCallback;
import com.wanyi.uiframe.persistence.SearchHistoryDoDao;
import com.wanyi.uiframe.persistence.entity.SearchHistoryDo;
import com.wanyi.uiframe.usercenter.realize.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchHistoryPresenter extends BasePresenter<ISearchHistoryCallback> {
    /**
     * 历史展示的
     */
    SearchHistoryDoDao searchHistoryDoDao;
    @Override
    public void attach(ISearchHistoryCallback view) {
        super.attach(view);
        searchHistoryDoDao = MyApp.getInstance().getDaoSession().getSearchHistoryDoDao();
    }

    /**
     * 存储历史记录
     * @param data 历史数据
     */
    public  void saveHistoryVO(ISearchHistoryVO data){
        SearchHistoryDo searchHistoryDo = new SearchHistoryDo();
        searchHistoryDo.setKeyWord(data.getTitle());
        //这里要展示对应的搜索视图
        try {
            searchHistoryDoDao.save(searchHistoryDo);
        } catch (SQLiteConstraintException e) {
            Log.e(getClass().getName(),e.toString());
        }
    }
    /**
     * 存储历史记录
     * @param title 历史数据
     */
    public  void saveHistoryVO(String title){
        SearchHistoryDo searchHistoryDo = new SearchHistoryDo();
        searchHistoryDo.setKeyWord(title);
        //这里要展示对应的搜索视图
        try {
            searchHistoryDoDao.save(searchHistoryDo);
        } catch (SQLiteConstraintException e) {
            Log.e(getClass().getName(),e.toString());
        }
    }


    /**
     * 清除历史记录
     */
    public void clearHistoryVO() {
        searchHistoryDoDao.deleteAll();
        if(getView() !=null) {
            getView().hideHistory();
        }
    }

    /**
     * 加载历史记录
     */
    public void loadHistoryVo() {
        List<? extends ISearchHistoryVO> searchHistoryVOS = searchHistoryDoDao.queryBuilder().limit(8).orderDesc(SearchHistoryDoDao.Properties.Id).build().list();
        List<ISearchHistoryVO> dataList = new ArrayList<>();
        for(ISearchHistoryVO item:searchHistoryVOS) {
            dataList.add(item);
        }
        if(getView() != null) {
            if(dataList.size() != 0) {
                getView().loadHistory(dataList);
            } else {
                getView().hideHistory();
            }
        }
    }

    /**
     * 销毁对象
     */
    @Override
    public void detach() {
        searchHistoryDoDao = null;
        super.detach();
    }

}
