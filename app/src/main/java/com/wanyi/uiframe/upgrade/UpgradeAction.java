package com.wanyi.uiframe.upgrade;



import android.content.Context;

import com.wanyi.uiframe.api.ApiFactory;
import com.wanyi.uiframe.api.consts.UriConst;
import com.wanyi.uiframe.upgrade.custom.XUpdateServiceParser;
import com.wanyi.uiframe.upgrade.enity.action.ItemUpdate;
import com.xuexiang.xupdate.XUpdate;

import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class UpgradeAction {





    /**
     * 展示升级对话框
     */
    public static void showUpgradeDialog(Context context,Boolean isBackground) {
        UpgradeAction.setItemUpdateAction(itemUpdate -> {
            if(Utils.compareAppVersion(itemUpdate.getLatestVersion(),context) > 0) {
                XUpdate.newBuild(context)
                        .isWifiOnly(false)
                        .isGet(true)
                        .updateUrl(UriConst.URI_DEFAULT)
                        .updateParser(new XUpdateServiceParser())
                        .update();
            }else {
                if(!isBackground) {
                    Toasty.info(context, "当前已是最新版本").show();
                }
            }

        });
    }



    /**
     * 数据消费者
     * @param consumer 消费者
     */
    private static void setItemUpdateAction(Consumer<ItemUpdate> consumer) {
        IUpdateService hdService = ApiFactory.createApi(UriConst.URI_DEFAULT,IUpdateService.class,null);
        hdService.app_detail().map(appEntity -> {
            ItemUpdate itemUpdate = appEntity;
            UpgradeConst.setUpdate(itemUpdate);
            return itemUpdate;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }




}
