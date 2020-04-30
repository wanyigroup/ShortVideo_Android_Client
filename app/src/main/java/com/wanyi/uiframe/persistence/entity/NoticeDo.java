package com.wanyi.uiframe.persistence.entity;

import com.wanyi.uiframe.persistence.action.INoticeVO;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class NoticeDo implements INoticeVO {

    @Id
    Long id;
    String title;
    String content;
    String date;

    boolean isRead;



    @Generated(hash = 1518024034)
    public NoticeDo(Long id, String title, String content, String date,
            boolean isRead) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.isRead = isRead;
    }

    @Generated(hash = 303286046)
    public NoticeDo() {
    }



    @Override
    public boolean isRead() {
        return isRead;
    }

    @Override
    public String getUri() {
        return null;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getDate() {
        return date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean getIsRead() {
        return this.isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }



}
