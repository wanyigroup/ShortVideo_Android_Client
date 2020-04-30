package com.example.location.model;

import com.example.location.enums.ELocationType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationInfo {

    /**
     * 定位类型
     */
    ELocationType eLocationType;

    /**
     * 纬度
     */
    Double latitude;

    /**
     * 经度
     */
    Double longitude;

    /**
     * 地址信息
     */
    String address;

    /**
     * 辅助信息
     */
    AssistInfo assistInfo;

    public Integer getELocationType() {
        return eLocationType.getValue();
    }

    @Data
    @Builder
    public  static class  AssistInfo {
        /**
         * 国家码
         */
        String countryCode;
        /**
         * 国家
         */
        String country;
        /**
         * 城市
         */
        String city;
        /**
         * 区
         */
        String district;
        /**
         * 街道
         */
        String street;

        /**
         * 速度
         */
        Float speed;

        /**
         * 卫星数目
         */
        Integer satelliteNumber;

        /**
         * 海拔高度
         */
        Double altitude;

        /**
         * gps质量判断
         */
        Integer gpsAccuracyStatus;

        /**
         * 运营商信息
         */
        Integer operators;

    }





}
