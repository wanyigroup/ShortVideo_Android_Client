package com.wanyi.uiframe.api.tool;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnectionHelper {


    public static Boolean isConnection(String url_s) {
        try{
            URL url = new URL(url_s);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            /**
             * public int getResponseCode()throws IOException
             * 从 HTTP 响应消息获取状态码。
             * 例如，就以下状态行来说：
             * HTTP/1.0 200 OK
             * HTTP/1.0 401 Unauthorized
             * 将分别返回 200 和 401。
             * 如果无法从响应中识别任何代码（即响应不是有效的 HTTP），则返回 -1。
             *
             * 返回 HTTP 状态码或 -1
             */
            int state = conn.getResponseCode();
            System.out.println(state);
            if(state == 200){
                return true;
            }
            else{
                return false;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



}
