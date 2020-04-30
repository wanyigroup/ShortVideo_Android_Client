package com.example.location.enums;

public enum  ELocationType {
    /**
     * Gps定位
     */
    Gps(0),
    /**
     * 网络定位
     */
    Net(1),
    /**
     * 离线定位
     */
    Offline(2),
    /**
     * 百度定位服务报错
     */
    ServerError(3),
    /**
     * 网络不通，导致定位失败
     */
    NetError(4),
    /**
     * 无法获取有效定位依据导致定位失败(一般手机处于飞行模式)
     */
    CriteriaException(5),
    /**
     * 未知异常
     */
    Unknow(-1);

    Integer value;
    ELocationType(int i) {
        this.value = i;
    }

    public Integer getValue() {
        return value;
    }

}
