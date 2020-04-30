package com.wanyi.uiframe.mvp.presenter.callback;

import com.wanyi.uiframe.api.model.dto.vo.ISearchTendencyVO;
import com.wanyi.uiframe.usercenter.abs.view.IView;

import java.util.List;

public interface ISearchTendencyCallback extends IView {

    /**
     * 登录热点列表
     * @param dataList
     */
    void loadTendency(List<ISearchTendencyVO> dataList);

    /**
     * 隐藏热点列表布局
     */
    void hideTendency();


}
