package com.wanyi.uiframe.mvp.presenter.callback;

import com.wanyi.uiframe.api.model.dto.vo.ISearchHistoryVO;
import com.wanyi.uiframe.usercenter.abs.view.IView;

import java.util.List;

public interface ISearchHistoryCallback extends IView {

    /**
     * 展示历史记录
     * @param dataList
     */
    void loadHistory(List<ISearchHistoryVO> dataList);

    /**
     * 隐藏历史布局
     */
    void hideHistory();

}
