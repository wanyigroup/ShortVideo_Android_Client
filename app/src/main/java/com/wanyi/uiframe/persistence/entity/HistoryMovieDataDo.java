package com.wanyi.uiframe.persistence.entity;

import com.wanyi.uiframe.api.model.dto.vo.IPreMovieVO;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class HistoryMovieDataDo implements IPreMovieVO {

    @Id
    Long id;
    String watchNum;
    String loveNum;
    String collectNum;
    Integer width;
    Integer height;
    String movideDesc;
    @Unique
    String videoKey;
    String video_commentnum;
    String avatar;
    String author;
    String title;
    String movieUri;
    String hlsurl;
    String placeImage;
    Boolean isVip;

    @Generated(hash = 1952699990)
    public HistoryMovieDataDo(Long id, String watchNum, String loveNum,
            String collectNum, Integer width, Integer height, String movideDesc,
            String videoKey, String video_commentnum, String avatar, String author,
            String title, String movieUri, String hlsurl, String placeImage,
            Boolean isVip) {
        this.id = id;
        this.watchNum = watchNum;
        this.loveNum = loveNum;
        this.collectNum = collectNum;
        this.width = width;
        this.height = height;
        this.movideDesc = movideDesc;
        this.videoKey = videoKey;
        this.video_commentnum = video_commentnum;
        this.avatar = avatar;
        this.author = author;
        this.title = title;
        this.movieUri = movieUri;
        this.hlsurl = hlsurl;
        this.placeImage = placeImage;
        this.isVip = isVip;
    }

    @Generated(hash = 914864079)
    public HistoryMovieDataDo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWatchNum(String watchNum) {
        this.watchNum = watchNum;
    }

    public void setLoveNum(String loveNum) {
        this.loveNum = loveNum;
    }

    public void setCollectNum(String collectNum) {
        this.collectNum = collectNum;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getMovideDesc() {
        return movideDesc;
    }

    public void setMovideDesc(String movideDesc) {
        this.movideDesc = movideDesc;
    }

    public void setVideoKey(String videoKey) {
        this.videoKey = videoKey;
    }

    public void setVideo_commentnum(String video_commentnum) {
        this.video_commentnum = video_commentnum;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMovieUri(String movieUri) {
        this.movieUri = movieUri;
    }

    public void setHlsurl(String hlsurl) {
        this.hlsurl = hlsurl;
    }

    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage;
    }

    @Override
    public String getWatchNum() {
        return watchNum;
    }

    @Override
    public String getLoveNum() {
        return loveNum;
    }

    @Override
    public String getCollectNum() {
        return collectNum;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public String getMovieDesc() {
        return movideDesc;
    }

    @Override
    public String getVideoKey() {
        return videoKey;
    }

    @Override
    public String getVideo_commentnum() {
        return video_commentnum;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public String getAuthor() {
        return author;
    }


    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getMovieUri() {
        return movieUri;
    }

    @Override
    public String getHlsurl() {
        return hlsurl;
    }

    @Override
    public String getPlaceImage() {
        return placeImage;
    }

    public void transform(IPreMovieVO preMovieVO) {
        watchNum = preMovieVO.getWatchNum();
        loveNum = preMovieVO.getLoveNum();
        collectNum = preMovieVO.getCollectNum();
        width = preMovieVO.getWidth();
        height = preMovieVO.getHeight();
        movideDesc = preMovieVO.getMovieDesc();
        videoKey = preMovieVO.getVideoKey();
        video_commentnum = preMovieVO.getVideo_commentnum();
        avatar = preMovieVO.getAvatar();
        author = preMovieVO.getAuthor();
        title = preMovieVO.getTitle();
        movieUri = preMovieVO.getMovieUri();
        hlsurl = preMovieVO.getHlsurl();
        placeImage = preMovieVO.getPlaceImage();
        isVip = preMovieVO.videoIsVip();
    }



    public Boolean getIsVip() {
        return this.isVip;
    }

    public void setIsVip(Boolean isVip) {
        this.isVip = isVip;
    }

    @Override
    public Boolean videoIsVip() {
        return null;
    }
}
