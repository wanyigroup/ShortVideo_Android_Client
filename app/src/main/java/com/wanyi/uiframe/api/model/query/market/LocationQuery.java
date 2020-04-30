package com.wanyi.uiframe.api.model.query.market;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationQuery {


    /**
     * address : 中国安徽省芜湖市弋江区纬七路
     * assistInfo : {"altitude":4.9E-324,"city":"芜湖市","country":"中国","countryCode":"0","district":"弋江区","gpsAccuracyStatus":0,"operators":0,"satelliteNumber":-1,"speed":0,"street":"纬七路"}
     * eLocationType : 1
     * latitude : 31.285348
     * longitude : 118.36365
     */

    private String address;
    private AssistInfoBean assistInfo;
    private int eLocationType;
    private double latitude;
    private double longitude;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AssistInfoBean {
        /**
         * altitude : 4.9E-324
         * city : 芜湖市
         * country : 中国
         * countryCode : 0
         * district : 弋江区
         * gpsAccuracyStatus : 0
         * operators : 0
         * satelliteNumber : -1
         * speed : 0
         * street : 纬七路
         */

        private double altitude;
        private String city;
        private String country;
        private String countryCode;
        private String district;
        private int gpsAccuracyStatus;
        private int operators;
        private int satelliteNumber;
        private float speed;
        private String street;


    }


}
