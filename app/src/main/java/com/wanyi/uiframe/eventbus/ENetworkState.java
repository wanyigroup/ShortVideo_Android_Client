package com.wanyi.uiframe.eventbus;

public enum ENetworkState {
    //无网络
    noNetWork(0),
    //运营商网络
    mobileNetWork(1),
    //wifi网络
    wifiNetWork(2);

    int value;
    ENetworkState(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

}
