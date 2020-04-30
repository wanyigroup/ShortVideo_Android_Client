package com.wanyi.uiframe.mvp.presenter;

import com.wanyi.uiframe.MyApp;
import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import com.wanyi.uiframe.mvp.presenter.callback.ILocalMovieReadCallback;
import com.wanyi.uiframe.persistence.LocalMovieDataDoDao;
import com.wanyi.uiframe.persistence.entity.LocalMovieDataDo;
import com.wanyi.uiframe.usercenter.realize.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class LocalMovieReadPresenter extends BasePresenter<ILocalMovieReadCallback> {

    /**
     * 本地核查业务
     */
    LocalMovieCheckPresenter checkPresenter;

    @Override
    public void attach(ILocalMovieReadCallback view) {
        super.attach(view);
        checkPresenter = new LocalMovieCheckPresenter();
        checkPresenter.attach(view.getActivity());
    }

    /**
     * 读取数据
     */
    public void readData() {
        try {
            checkPresenter.checkPermission(rst -> {
                if(rst) {
                   checkPresenter.synLocalRecord();
                   List<LocalMovieDataDo> dataSource = MyApp.getInstance().getDaoSession().getLocalMovieDataDoDao().loadAll();
                   if(dataSource.size() == 0) {
                       if(getView() != null) {
                           getView().empty();
                       }
                   }

                   List<IPreMovieVO> preMovieVOList = new ArrayList<>();
                   for(LocalMovieDataDo item:dataSource){
                       preMovieVOList.add(item);
                   }

                   if(getView() != null) {
                         getView().loadData(preMovieVOList);
                   }
                }

                if(!rst) {
                    if(getView()!=null) {
                        getView().noReadPermission();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }






    @Override
    public void detach() {
        checkPresenter.deatch();
        super.detach();
    }

}
