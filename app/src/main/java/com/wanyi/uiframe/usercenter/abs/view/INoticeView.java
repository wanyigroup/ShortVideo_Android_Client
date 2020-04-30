package com.wanyi.uiframe.usercenter.abs.view;

import com.wanyi.uiframe.persistence.action.INoticeVO;

import java.util.List;

public interface INoticeView extends IView{

    /**
     * 加载完成
     */
    void finishRefresh();

    void loadData(List<INoticeVO> data);



}
