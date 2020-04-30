package com.wanyi.uiframe.mvp.presenter;

import com.wanyi.uiframe.mvp.presenter.action.IDynamicMovieAction;
import com.wanyi.uiframe.mvp.presenter.action.factory.CacheMovieAction;
import com.wanyi.uiframe.mvp.presenter.action.factory.FeatureMovieAction;
import com.wanyi.uiframe.mvp.presenter.action.factory.FollowMovieAction;
import com.wanyi.uiframe.mvp.presenter.action.factory.HistoryMovieAction;
import com.wanyi.uiframe.mvp.presenter.action.factory.SameCityMoveAction;
import com.wanyi.uiframe.mvp.presenter.callback.IMovieCallback;
import com.wanyi.uiframe.singleton.EnumPreMovie;
import java.util.HashMap;

public class DynamicMoviePresenter implements IDynamicMovieAction {

    /**
     * 总的缓存队列
     */
    HashMap<EnumPreMovie,IDynamicMovieAction> enumPreHash = new HashMap<>();

    /**
     * 动态action
     */
    IDynamicMovieAction iDynamicMovieAction;

    /**
     * 当前的枚举类型
     */
    EnumPreMovie enumPreMovie;

    /**
     * 静态单例
     */
    static DynamicMoviePresenter instance;

    public static DynamicMoviePresenter getInstance(){
        if(instance == null) {
            instance = new DynamicMoviePresenter();
        }
        return instance;
    }



    private DynamicMoviePresenter() {
        enumPreHash.put(EnumPreMovie.follow, new FollowMovieAction());
        enumPreHash.put(EnumPreMovie.samecity,new SameCityMoveAction());
        enumPreHash.put(EnumPreMovie.mycache,new CacheMovieAction());
        enumPreHash.put(EnumPreMovie.history,new HistoryMovieAction());
    }



    public void setDataSource(EnumPreMovie enumPreMovie){
        this.enumPreMovie = enumPreMovie;
        iDynamicMovieAction = enumPreHash.get(enumPreMovie);
    }

    public EnumPreMovie getDataSource() {
        return enumPreMovie;
    }

    public IDynamicMovieAction getDataSource(EnumPreMovie enumPreMovie) {

       return enumPreHash.get(enumPreMovie);
    }


    @Override
    public void registerCallBack(IMovieCallback callback) {
        if(iDynamicMovieAction != null) {
            iDynamicMovieAction.registerCallBack(callback);
        }
    }

    @Override
    public void refresh() {
        if(iDynamicMovieAction != null) {
            iDynamicMovieAction.refresh();
        }
    }

    @Override
    public void loadMore() {
        if(iDynamicMovieAction != null) {
            iDynamicMovieAction.loadMore();
        }
    }

    @Override
    public void record(int position) {
        if(iDynamicMovieAction != null) {
            iDynamicMovieAction.record(position);
        }
    }

    @Override
    public void synData() {
        if(iDynamicMovieAction != null) {
            iDynamicMovieAction.synData();
        }
    }

}
