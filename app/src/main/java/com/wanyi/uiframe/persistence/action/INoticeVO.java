package com.wanyi.uiframe.persistence.action;

import com.wanyi.uiframe.api.model.dto.vo.IAnnouncementVO;

public interface INoticeVO extends IAnnouncementVO {

    /**
     * 是否已读
     * @return
     */
    boolean isRead();



}
