package com.wanyi.uiframe.mvp.presenter.action;

public interface ISearchMovieAction extends IDynamicMovieAction {

    /**
     * 搜索关键词
     * @param keyword
     */
    void searchKeyword(String keyword);


}
