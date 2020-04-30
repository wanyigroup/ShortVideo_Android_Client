package com.wanyi.uiframe.dkplayer.adapter.callback;

import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;

public interface TikTokCallBack {

    void callBack(String videoKey);
    void downLoad(IPreMovieVO iPreMovieVO);

}
