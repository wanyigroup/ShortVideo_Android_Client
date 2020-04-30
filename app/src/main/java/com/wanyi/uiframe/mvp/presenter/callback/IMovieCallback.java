package com.wanyi.uiframe.mvp.presenter.callback;

import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.usercenter.abs.view.IView;
import java.util.List;

public interface IMovieCallback extends IView {

    /**
     * 刷新
     * */
    void onRefresh(List<IPreMovieVO> dataSource);

    /**
     * 服务端数据为空
     */
    void onEmpty();

    /**
     * 加载更多
     * @param dataSource 数据源
     */
    void onLoadMore(List<IPreMovieVO> dataSource);

    /**
     * 服务端数据全部加载
     */
    void onComplete();

    /**
     * 同步操作
     * @param dataSource
     * @param position
     */
    void onSyn(List<IPreMovieVO> dataSource,int position);

}
