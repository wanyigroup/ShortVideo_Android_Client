package com.wanyi.uiframe.api.model.dto.advise;
import lombok.Data;

@Data
public class AdImgDTO {

    /**
     * adv_id : 1
     * adv_type : img
     * adv_jump : http://www.baidu.com
     * adv_status : 1
     */
    private int adv_id;
    private String adv_type;
    private String adv_img;
    private String adv_jump;
    private int adv_status;


}
