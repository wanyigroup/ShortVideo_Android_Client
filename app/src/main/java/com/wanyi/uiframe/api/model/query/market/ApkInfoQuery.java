package com.wanyi.uiframe.api.model.query.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ApkInfoQuery {

    /**
     * appName : AppManage
     * pkgName : com.miles.appmanage
     * versionName : 1.0
     */
    private String appName;
    private String pkgName;
    private String versionName;


}
