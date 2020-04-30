package com.wanyi.uiframe.mvp;

import com.wanyi.uiframe.comment.action.ICommentItem;
import com.wanyi.uiframe.api.model.dto.vo.IAnnouncementVO;
import com.wanyi.uiframe.api.model.dto.vo.ISearchHistoryVO;
import com.wanyi.uiframe.api.model.dto.vo.ISearchTendencyVO;

public class ExampleVO implements ISearchHistoryVO, ISearchTendencyVO, IAnnouncementVO, ICommentItem {

    /**
     * 图片地址
     */
    private String movieImageUri = "http://vimg3.ws.126.net/image/snapshot/2016/11/C/T/VC628QHCT.jpg";

    @Override
    public String getTitle() {
        return "历史记录";
    }

    @Override
    public Long getId() {
        return 1L;
    }

    @Override
    public String getUri() {
        return movieImageUri;
    }


    @Override
    public String getContent() {
        return "APP今天又更新了吗......";
    }

    @Override
    public String getDate() {
        return "2019-11-27 11:30:00";
    }


    @Override
    public String getReading() {
        return "10000";
    }


    @Override
    public String getNickName() {
        return "小可爱";
    }

    @Override
    public String getComment() {
        return "哈哈哈哈哈哈";
    }

    @Override
    public String getCreateTime() {
        return "2019-01-01";
    }
}
