package com.wanyi.uiframe.api.model.dto.advise;

import com.wanyi.uiframe.api.model.dto.vo.IBootAdvertisingVO;

import java.io.Serializable;

import lombok.Data;

@Data
public class AppBootDTO implements IBootAdvertisingVO, Serializable {

    /**
     * adv_id : 1
     * adv_type : img
     * adv_jump : http://www.baidu.com
     * adv_skiptime : 5
     * adv_status : 1
     */

    private int adv_id;
    private String adv_type;
    private String adv_img;
    private String adv_jump;
    private int adv_skiptime;
    private int adv_status;


    @Override
    public String getBootImage() {
        return adv_img;
    }

    @Override
    public String getAdvUrl() {
        return adv_jump;
    }

    @Override
    public Integer getSkipTime() {
        return adv_skiptime;
    }
}
