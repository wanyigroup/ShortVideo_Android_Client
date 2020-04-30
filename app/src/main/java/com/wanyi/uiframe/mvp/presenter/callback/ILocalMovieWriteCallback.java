package com.wanyi.uiframe.mvp.presenter.callback;
import com.wanyi.uiframe.usercenter.abs.view.IView;

public interface ILocalMovieWriteCallback extends IView,ILocalMovieCheckCallback {

    /**
     * 视频存在本地
     */
    void movieExist();


    /**
     * 开始下载
     */
    void downStart();

}
