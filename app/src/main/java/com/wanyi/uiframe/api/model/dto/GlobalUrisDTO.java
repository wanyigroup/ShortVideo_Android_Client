package com.wanyi.uiframe.api.model.dto;
import com.google.gson.annotations.SerializedName;
import com.wanyi.uiframe.api.model.dto.action.IDomain;
import lombok.Data;

@Data
public class GlobalUrisDTO {


    /**
     * api_name : AppLoader
     * api_status : true
     * api_urls : {"default":"apiv1.afuny.com","apploader":"apploader.xiong.eu","advpublic":"advpublic.afuny.com","update":"update.afuny.com","video":"vapi.afuny.com","bigdata":"market.afuny.com"}
     * app_cachettl : 86400
     * app_gw_cachettl : 86400
     */

    private String api_name;
    private boolean api_status;
    private ApiUrlsBean api_urls;
    private String app_cachettl;
    private String app_gw_cachettl;


    @Data
    public static class ApiUrlsBean implements IDomain {
        /**
         * default : apiv1.afuny.com
         * apploader : apploader.xiong.eu
         * advpublic : advpublic.afuny.com
         * update : update.afuny.com
         * video : vapi.afuny.com
         * bigdata : market.afuny.com
         */
        /**
         * 默认网址
         */
        @SerializedName("default")
        private String defaultX;
        /**
         * 拉取启动页
         */
        private String apploader;
        /**
         * 市场
          */
        private String bigdata;

        @Override
        public String getAdvDomain() {
            return "http://" + defaultX;
        }

        @Override
        public String getBigDataDomain() {
            return "http://" + bigdata;
        }

        @Override
        public String getUpdateDomain() {
            return "http://" + defaultX;
        }

        @Override
        public String getVideoDomain() {
            return "http://" + defaultX;
        }

        @Override
        public String getDefaultDomain() {
            return "http://" + defaultX;
        }

    }



}
