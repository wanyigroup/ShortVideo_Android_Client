package com.wanyi.uiframe.api.model.dto.advise;

import lombok.Data;

@Data
public class AdvTxtDTO {


    /**
     * adv_id : 1
     * adv_type : text
     * adv_txt : 震惊,是谁在半夜听墙角......
     * adv_jump : http://www.baidu.com
     * adv_status : 1
     */

    private int adv_id;
    private String adv_type;
    private String adv_img;
    private String adv_txt;
    private String adv_jump;
    private int adv_status;

}
