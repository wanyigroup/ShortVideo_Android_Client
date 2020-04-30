package com.wanyi.uiframe.mvp.presenter.action.factory;

import android.util.Log;

import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.mvp.presenter.action.IDynamicMovieAction;
import com.wanyi.uiframe.mvp.presenter.callback.IMovieCallback;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseMovieAction implements IDynamicMovieAction {

    /**
     * 当前页数
     */
    protected volatile Integer page = 1;
    /**
     * 同步位置
     */
    protected volatile Integer position = 0;
    /**
     * 回调接口
     */
    protected IMovieCallback callback;

    List<IPreMovieVO> dataSource = new ArrayList<>();
    @Override
    public void registerCallBack(IMovieCallback callback) {
         this.callback = callback;
    }

    @Override
    public void refresh() {
        page = 1;

        Observable<List<IPreMovieVO>> observable = getPageData(page);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> { Log.e(getClass().getName(),throwable.toString()); } )
                .subscribe(data -> {
                if (callback != null) {
                 if(data.size() != 0) {
                     dataSource.clear();
                    dataSource.addAll(data);
                    callback.onRefresh(dataSource);
                    page++;
                 } else {
                    callback.onEmpty();
                 }
            }
        });
    }

    @Override
    public void loadMore() {
        Observable<List<IPreMovieVO>> observable = getPageData(page);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(data -> {
            if (callback != null) {
                if(data.size() != 0) {
                    callback.onLoadMore(data);
                    dataSource.addAll(data);
                    page++;
                } else  {
                    callback.onComplete();
                }
            }
        });
    }

    @Override
    public void record(int position) {
      this.position = position;
    }

    @Override
    public void synData() {
        if(callback != null) {
            if(dataSource.size() == 0) {
                refresh();
            }else {
                if(callback != null) {
                    callback.onSyn(dataSource, position);
                }
            }
        }
    }

    protected abstract Observable<List<IPreMovieVO>> getPageData(Integer page);


}
