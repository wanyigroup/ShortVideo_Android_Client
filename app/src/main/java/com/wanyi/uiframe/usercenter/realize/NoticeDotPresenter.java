package com.wanyi.uiframe.usercenter.realize;

import android.util.Log;

import com.wanyi.uiframe.MyApp;
import com.wanyi.uiframe.api.model.dto.vo.IAnnouncementVO;
import com.wanyi.uiframe.persistence.NoticeDoDao;
import com.wanyi.uiframe.persistence.entity.NoticeDo;
import com.wanyi.uiframe.usercenter.abs.view.INoticeDotView;
import com.wanyi.uiframe.usercenter.api.model.NoticeResultDTO;
import com.wanyi.uiframe.usercenter.realize.model.SystemNoticeModel;
import java.util.List;

public class NoticeDotPresenter extends BasePresenter<INoticeDotView> {

    NoticeDoDao noticeDoDao = MyApp.getInstance().getDaoSession().getNoticeDoDao();

    /**
     * 从网络中，加载系统通知
     */
    SystemNoticeModel netNoticeModel = new SystemNoticeModel();

    /**
     * 展示未读
     */
    public void showNotRead() {
        netNoticeModel.getNotice().subscribe(noticeResultDTOS -> {
            List<NoticeResultDTO.DataBean> dataBeans = noticeResultDTOS.getData();
            for(IAnnouncementVO vo:dataBeans) {
                Long id = vo.getId();
                if(noticeDoDao.load(id) ==  null){
                    NoticeDo noticeDo = new NoticeDo();
                    noticeDo.setId(id);
                    noticeDo.setTitle(vo.getTitle());
                    noticeDo.setContent(vo.getContent());
                    noticeDo.setIsRead(false);
                    noticeDo.setDate(vo.getDate());
                    try {
                        noticeDoDao.insert(noticeDo);
                    }catch (Exception e){
                        Log.e(TAG,e.toString());
                    }
                }
                if(getView() != null) {
                    Long count = noticeDoDao.queryBuilder().where(NoticeDoDao.Properties.IsRead.eq(false)).count();
                    if(count != 0) {
                        getView().displayNotRead(count.intValue());
                    } else {
                        getView().hideDot();
                    }
                }
            }

        });
    }

}
