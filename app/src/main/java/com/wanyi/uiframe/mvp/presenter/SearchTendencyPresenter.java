package com.wanyi.uiframe.mvp.presenter;

import android.util.Log;

import com.wanyi.uiframe.api.ApiFacade;
import com.wanyi.uiframe.api.model.dto.video.VideoHostSearchDTO;
import com.wanyi.uiframe.api.model.dto.vo.ISearchTendencyVO;
import com.wanyi.uiframe.mvp.presenter.callback.ISearchTendencyCallback;
import com.wanyi.uiframe.usercenter.realize.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchTendencyPresenter extends BasePresenter<ISearchTendencyCallback> {


    /**
     * 展示热点记录
     */
    public void loadTendency() {
        ApiFacade.createVideo().map(iVideoService -> iVideoService.host_search().blockingFirst()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(videoHostSearchDTO -> {
                    List<VideoHostSearchDTO.DataBean> dataBeans =  videoHostSearchDTO.getData();
                    List<ISearchTendencyVO> dataList = new ArrayList<>();
                    for(VideoHostSearchDTO.DataBean item:dataBeans) {
                        dataList.add(item);
                    }

                    if(getView() != null) {
                        if(dataList.size() != 0) {
                            getView().loadTendency(dataList);
                        } else {
                            getView().hideTendency();
                        }
                    }
                });
    }


}
