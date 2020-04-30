package com.wanyi.uiframe.api.tool;
import java.text.SimpleDateFormat;

/**
 * 于php后端交互后的数据助手
 */
public class PhpHelper {


    /**
     * 时间转换
     * @param time 时间戳
     * @return 具体的值
     */
    public static String translateTime(Long time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr =  df.format(time * 1000);
        return dateStr;
    }



}
