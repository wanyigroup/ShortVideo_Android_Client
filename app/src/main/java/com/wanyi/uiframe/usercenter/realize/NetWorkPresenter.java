package com.wanyi.uiframe.usercenter.realize;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.wanyi.uiframe.eventbus.ENetworkState;
import com.wanyi.uiframe.usercenter.abs.view.INetWorkView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NetWorkPresenter extends BasePresenter<INetWorkView> {

    /**
     * 用户是否忽略是否为运营商网络
     */
    static boolean USER_IGNORE = false;

    /**
     * 网络广播
     */
    NetBroadCastReceiver receiver = new NetBroadCastReceiver();

    public static void setUserIgnore(boolean userIgnore) {
        USER_IGNORE = userIgnore;
    }


    public void netCheck() {
//        oldValue = -1;
//        if(getView() != null) {
//            receiver.publishWifiState(getView().getContext());
//        }
    }

    @Override
    public void attach(INetWorkView view) {
        super.attach(view);
        EventBus.getDefault().register(this);
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        view.getContext().registerReceiver(receiver,filter);
    }


    @Override
    public void detach() {
        if(getView() != null && getView().getContext()!=null) {
            getView().getContext().unregisterReceiver(receiver);
        }
        EventBus.getDefault().unregister(this);
        super.detach();
    }


    int oldValue = -1;
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void subscribeNetBroad(ENetworkState eNetworkState) {
         int newValue = eNetworkState.getValue();
         if(oldValue == newValue) {
             return;
         }
         oldValue = newValue;
         switch (eNetworkState) {
             case noNetWork:{
                   getView().noNetWork();
             }
             break;
             case mobileNetWork:{
                  if(!USER_IGNORE) {
                      getView().mobileNetWork();
                  }
             }
             break;
             case wifiNetWork:{
                  getView().wifiNetWork();
             }
             break;
         }

    }


    private class NetBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            publishWifiState(context);
        }

        /**
         * 发布状态通知
         * @param context 上下文
         */
        private void  publishWifiState(Context context) {
            ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isConnected()){
                if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
                    EventBus.getDefault().post(ENetworkState.wifiNetWork);
                }else if(networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                    EventBus.getDefault().post(ENetworkState.mobileNetWork);
                }
            }else {
                EventBus.getDefault().post(ENetworkState.noNetWork);
            }
        }
    }






}
