package com.wanyi.uiframe.api.model.dto.vo;


import java.io.Serializable;

public interface IPreMovieVO extends IMovieUriVO, Serializable,IVipVO {


    /**
     * 获取观看的次数
     * @return
     */
    String getWatchNum();

    /**
     * 获取喜欢的人数
     * @return
     */
    String getLoveNum();

    /**
     * 获取收藏次数
     * @return
     */
    String getCollectNum();

    /**
     * 获取宽度
     * @return
     */
    int getWidth();

    /**
     * 获取高度
     * @return
     */
    int getHeight();

    /**
     * 获取视频的描述信息
     * @return
     */
    String getMovieDesc();

    /**
     * 获取视频得key值
     * @return
     */
    String getVideoKey();


    /**
     * 获取评论数量
     * @return
     */
    String getVideo_commentnum();

    /**
     * 获取用户头像
     * @return
     */
    String getAvatar();

    /**
     * 获取作者
     * @return
     */
    String getAuthor();

    /**
     * 获取标题
     * @return
     */
    String getTitle();


}
