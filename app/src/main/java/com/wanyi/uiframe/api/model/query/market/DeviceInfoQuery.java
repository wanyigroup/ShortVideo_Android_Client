package com.wanyi.uiframe.api.model.query.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceInfoQuery {


    /**
     * brand : Xiaomi
     * model : Mi8
     * sysver : MIOS 10
     * androidid : 918cc1a94984774c
     * imei : 862263033930497
     * phonenumber : 18010445784
     */

    private String brand;
    private String model;
    private String sysver;
    private String androidid;
    private String imei;
    private String phonenumber;


}
