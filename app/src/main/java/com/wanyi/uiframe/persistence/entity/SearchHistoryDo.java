package com.wanyi.uiframe.persistence.entity;

import com.wanyi.uiframe.api.model.dto.vo.ISearchHistoryVO;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

@Entity
public class SearchHistoryDo implements ISearchHistoryVO {

    @Id
    private Long id;

    @Unique
    private String keyWord;


    @Generated(hash = 1324222800)
    public SearchHistoryDo(Long id, String keyWord) {
        this.id = id;
        this.keyWord = keyWord;
    }


    @Generated(hash = 370916066)
    public SearchHistoryDo() {
    }


    @Override
    public String getTitle() {
        return keyWord;
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getKeyWord() {
        return this.keyWord;
    }


    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }



}
