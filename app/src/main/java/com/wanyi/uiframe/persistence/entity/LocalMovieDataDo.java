package com.wanyi.uiframe.persistence.entity;

import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class LocalMovieDataDo implements IPreMovieVO {

    @Id
    Long id;
    String videoKey;
    Integer width;
    Integer height;
    String coverImage;
    String localUrl;


    @Generated(hash = 1015471146)
    public LocalMovieDataDo(Long id, String videoKey, Integer width, Integer height,
            String coverImage, String localUrl) {
        this.id = id;
        this.videoKey = videoKey;
        this.width = width;
        this.height = height;
        this.coverImage = coverImage;
        this.localUrl = localUrl;
    }

    @Generated(hash = 1684363170)
    public LocalMovieDataDo() {
    }






    @Override
    public String getWatchNum() {
        return 0 + "";
    }

    @Override
    public String getLoveNum() {
        return 0 + "";
    }

    @Override
    public String getCollectNum() {
        return 0 + "";
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        if(height <= 0)
            return 1920;
        return height;
    }

    @Override
    public String getMovieDesc() {
        return "";
    }

    @Override
    public String getVideoKey() {
        return videoKey;
    }

    @Override
    public String getVideo_commentnum() {
        return "";
    }

    @Override
    public String getAvatar() {
        return "";
    }

    @Override
    public String getAuthor() {
        return "";
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getMovieUri() {
        return localUrl;
    }

    @Override
    public String getHlsurl() {
        return localUrl;
    }

    @Override
    public String getPlaceImage() {
        return coverImage;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getCoverImage() {
        return this.coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getLocalUrl() {
        return this.localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public void setVideoKey(String videoKey) {
        this.videoKey = videoKey;
    }

    @Override
    public Boolean videoIsVip() {
        return false;
    }
}
