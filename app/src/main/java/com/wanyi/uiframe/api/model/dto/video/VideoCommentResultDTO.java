package com.wanyi.uiframe.api.model.dto.video;

import com.wanyi.uiframe.api.model.dto.video.action.IStatus;

/**
 * 视频评论结果
 */
public class VideoCommentResultDTO implements IStatus {

    /**
     * code : 200
     * msg : 评论成功
     */

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean isSuccess() {
        return code == 200;
    }
}
