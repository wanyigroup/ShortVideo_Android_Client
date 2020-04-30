package com.wanyi.uiframe.mvp.presenter.callback;

import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.usercenter.abs.view.IView;

import java.util.List;

public interface ILocalMovieReadCallback extends IView,ILocalMovieCheckCallback {

    /**
     * 本地存储为空
     */
    void empty();


    /**
     * 加载本地视频
     * @param data
     */
    void loadData(List<IPreMovieVO> data);




}

