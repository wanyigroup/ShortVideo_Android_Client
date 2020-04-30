package com.wanyi.uiframe.api.model.dto.vo;


import java.io.Serializable;

public interface IMovieUriVO extends Serializable {

    /**
     * 获取路径地址
     * @return
     */
    String getMovieUri();

    /**
     * 获取m3u8视频地址
      * @return
     */
    String getHlsurl();

    /**
     * 获取视频占位符路径
     * @return
     */
    String getPlaceImage();

}
