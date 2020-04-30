package com.wanyi.uiframe.singleton;

import lombok.Data;

@Data
public class SingletonPreMovie {

    /**
     * 设置当前位置
     */
    EnumPreMovie enumPreMovie;

    static SingletonPreMovie singleTonPreMovie = null;

    public static SingletonPreMovie getInstance() {
        if(singleTonPreMovie == null) {
            singleTonPreMovie = new SingletonPreMovie();
        }
        return singleTonPreMovie;
    }

}
