package com.wanyi.uiframe.mvp.model.imp;

import com.wanyi.uiframe.mvp.ExampleVO;
import com.wanyi.uiframe.mvp.GenerateVO;
import com.wanyi.uiframe.mvp.model.ModelFactory;
import com.wanyi.uiframe.api.model.dto.vo.IAnnouncementVO;

import java.util.List;

public class MessageFactory extends ModelFactory<List<IAnnouncementVO>> {

    @Override
    public List<IAnnouncementVO> crateModel() {
        List<IAnnouncementVO> announcementVOS = GenerateVO.generateList(ExampleVO.class,10);
        return announcementVOS;
    }

}
