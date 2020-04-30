package com.wanyi.uiframe.comment.action;

public interface ICommentItem {

    /**
     * 获取昵称
     * @return
     */
    String getNickName();

    /**
     * 获取评论
     * @return
     */
    String getComment();

    /**
     * 获取评论时间
     * @return
     */
    String getCreateTime();

}
