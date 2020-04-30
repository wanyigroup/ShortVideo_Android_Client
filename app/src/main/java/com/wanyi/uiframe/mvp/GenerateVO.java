package com.wanyi.uiframe.mvp;

import java.util.ArrayList;
import java.util.List;

public class GenerateVO {

    /**
     * 无构造函数，属性且有默认值的VO对象，生成对应的值
     * @param cla 类对象
     * @param size List大小
     * @param <T> VO对象
     * @return List对象
     */
    public static<T> List<T> generateList(Class<? extends T> cla,Integer size) {
        List<T> ds = new ArrayList<>();
        for(int i = 0;i < size; i++) {
            try {
                ds.add(cla.newInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return ds;
    }


}
