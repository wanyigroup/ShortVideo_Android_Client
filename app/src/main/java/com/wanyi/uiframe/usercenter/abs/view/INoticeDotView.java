package com.wanyi.uiframe.usercenter.abs.view;

public interface INoticeDotView extends IView {

    /**
     * 显示未读数量
     * @param num 数量
     */
    void displayNotRead(int num);

    /**
     * 隐藏数量
     */
    void hideDot();


}
