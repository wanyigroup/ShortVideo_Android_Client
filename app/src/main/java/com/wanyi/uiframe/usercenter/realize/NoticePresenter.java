package com.wanyi.uiframe.usercenter.realize;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.wanyi.uiframe.MyApp;
import com.wanyi.uiframe.api.model.dto.vo.IAnnouncementVO;
import com.wanyi.uiframe.eventbus.EMessageNotify;
import com.wanyi.uiframe.persistence.NoticeDoDao;
import com.wanyi.uiframe.persistence.action.INoticeVO;
import com.wanyi.uiframe.persistence.entity.NoticeDo;
import com.wanyi.uiframe.usercenter.abs.view.INoticeView;
import com.wanyi.uiframe.usercenter.api.model.NoticeResultDTO;
import com.wanyi.uiframe.usercenter.realize.model.SystemNoticeModel;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class NoticePresenter extends BasePresenter<INoticeView> {

    final String TAG = getClass().getName();

    /**
     * 从网络中，加载系统通知
     */
    SystemNoticeModel netNoticeModel = new SystemNoticeModel();

    /**
     * 本地加载数据
     */
    NoticeDoDao noticeDoDao = MyApp.getInstance().getDaoSession().getNoticeDoDao();


    /**
     * 本地加载
     */
    public void localLoad() {
          List<NoticeDo> noticeDos = noticeDoDao.loadAll();
          if(noticeDos != null) {
              if(getView() != null) {
                  List<INoticeVO> iNoticeVOS = new ArrayList<>();
                  iNoticeVOS.addAll(noticeDos);
                  getView().loadData(iNoticeVOS);
              }
          }
    }



    /**
     * 刷新加载
     */
    public void refreshLoad() {
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
                 EventBus.getDefault().post(EMessageNotify.notify);
             }
             List<NoticeDo> noticeDos = noticeDoDao.loadAll();
             List<INoticeVO> noticeVOS = new ArrayList<>();
             noticeVOS.addAll(noticeDos);
             if(getView() != null) {
                 getView().loadData(noticeVOS);
                 getView().finishRefresh();
             }

        });
    }


    /**
     * 刷新某个数据
     */
    public void clickMessage(INoticeVO iNoticeVO) {
        NoticeDo noticeDo = (NoticeDo) iNoticeVO;
        noticeDo.setIsRead(true);
        noticeDoDao.update(noticeDo);
        EventBus.getDefault().post(EMessageNotify.notify);
    }















}
