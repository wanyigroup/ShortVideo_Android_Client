package com.wanyi.uiframe.api.model.dto.video;

import lombok.Data;

@Data
public class VideoValueDTO {

    /**
     * status : 200
     * value : 10.0w
     * msg : 刷新成功
     */
    private int status;
    private String value;
    private String msg;

}
